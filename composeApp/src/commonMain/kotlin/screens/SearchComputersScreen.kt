package screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import database.BookingDatabase
import database.models.User
import database.models.Computer
import database.models.Booking
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import components.FilterChip

@Composable
fun SearchComputersScreen(
    database: BookingDatabase,
    currentUser: User,
    onBack: () -> Unit
) {
    var selectedDay by remember { mutableStateOf("Monday") }
    var selectedTimeSlot by remember { mutableStateOf("09:00-10:00") }
    var availableComputers by remember { mutableStateOf<List<Computer>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val timeSlots = listOf(
        "09:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00",
        "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00"
    )

    // Function to refresh available computers
    fun refreshComputers() {
        scope.launch {
            searchAvailableComputers(database, selectedDay, selectedTimeSlot) { computers ->
                availableComputers = computers
            }
        }
    }

    LaunchedEffect(selectedDay, selectedTimeSlot) {
        searchAvailableComputers(database, selectedDay, selectedTimeSlot) { computers ->
            availableComputers = computers
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Search Computers",
                style = MaterialTheme.typography.h5
            )
            TextButton(onClick = onBack) {
                Text("Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Day selection
        Text("Select Day:", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(daysOfWeek) { day ->
                FilterChip(
                    selected = selectedDay == day,
                    onClick = { selectedDay = day },
                    content = { Text(day) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Time slot selection
        Text("Select Time Slot:", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(timeSlots) { timeSlot ->
                FilterChip(
                    selected = selectedTimeSlot == timeSlot,
                    onClick = { selectedTimeSlot = timeSlot },
                    content = { Text(timeSlot) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (successMessage.isNotEmpty()) {
            Card(
                backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = successMessage,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (errorMessage.isNotEmpty()) {
            Card(
                backgroundColor = MaterialTheme.colors.error.copy(alpha = 0.1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Text(
            text = "Available Computers (${availableComputers.size})",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(availableComputers) { computer ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                bookComputer(
                                    database = database,
                                    currentUser = currentUser,
                                    computer = computer,
                                    day = selectedDay,
                                    timeSlot = selectedTimeSlot,
                                    onSuccess = { globalId ->
                                        successMessage = "Successfully booked computer $globalId for $selectedDay at $selectedTimeSlot"
                                        errorMessage = ""
                                        // Refresh computers after successful booking
                                        refreshComputers()
                                    },
                                    onError = { error ->
                                        errorMessage = error
                                        successMessage = ""
                                    }
                                )
                            }
                        },
                    elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Computer: ${computer.globalId}",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            text = "Room: ${computer.globalId.split("-")[0]}",
                            style = MaterialTheme.typography.body2
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Click to book",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}

private suspend fun searchAvailableComputers(
    database: BookingDatabase,
    day: String,
    timeSlot: String,
    onResult: (List<Computer>) -> Unit
) {
    try {
        // Get all existing bookings for the selected day and time
        val existingBookings = database.bookingDao().getBookingsByDayAndTime(day, timeSlot)
        val bookedComputerIds = existingBookings.map { it.computerId }.toSet()

        // Get all rooms - use first() to get current value instead of collect
        val allRooms = database.roomDao().getAllRooms().first()
        val availableComputers = mutableListOf<Computer>()

        for (room in allRooms) {
            // Get computers for each room - use first() instead of collect
            val roomComputers = database.computerDao().getComputersByRoom(room.id).first()
            val availableInRoom = roomComputers.filter { it.id !in bookedComputerIds }
            availableComputers.addAll(availableInRoom)
        }

        onResult(availableComputers)
    } catch (e: Exception) {
        onResult(emptyList())
    }
}

private suspend fun bookComputer(
    database: BookingDatabase,
    currentUser: User,
    computer: Computer,
    day: String,
    timeSlot: String,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    try {
        // Check if computer is still available
        val existingBooking = database.bookingDao().getBookingByComputerDayTime(
            computer.id, day, timeSlot
        )

        if (existingBooking != null) {
            onError("Computer is no longer available")
            return
        }

        // Create booking
        val booking = Booking(
            userId = currentUser.id,
            computerId = computer.id,
            roomId = computer.roomId,
            dayOfWeek = day,
            timeSlot = timeSlot,
            bookingDate = Clock.System.now().toString()
        )

        database.bookingDao().upsert(booking)
        onSuccess(computer.globalId)

    } catch (e: Exception) {
        onError("Failed to book computer: ${e.message}")
    }
}