package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import database.BookingDatabase
import database.models.User
import database.models.Room
import database.models.Computer
import database.models.Booking
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import components.FilterChip

@Composable
fun RoomViewScreen(
    database: BookingDatabase,
    currentUser: User,
    onBack: () -> Unit
) {
    var rooms by remember { mutableStateOf<List<Room>>(emptyList()) }
    var selectedRoom by remember { mutableStateOf<Room?>(null) }
    var selectedDay by remember { mutableStateOf("Monday") }
    var selectedTimeSlot by remember { mutableStateOf("09:00-10:00") }
    var computers by remember { mutableStateOf<List<Computer>>(emptyList()) }
    var bookings by remember { mutableStateOf<List<RoomBookingInfo>>(emptyList()) }
    var showBookingDialog by remember { mutableStateOf(false) }
    var selectedComputer by remember { mutableStateOf<Computer?>(null) }
    var selectedBookingInfo by remember { mutableStateOf<RoomBookingInfo?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val timeSlots = listOf(
        "09:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00",
        "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00"
    )

    // Load rooms when screen opens
    LaunchedEffect(Unit) {
        database.roomDao().getAllRooms().collect { roomList ->
            rooms = roomList
            if (selectedRoom == null && roomList.isNotEmpty()) {
                selectedRoom = roomList.first()
            }
        }
    }

    // Load computers and bookings when room/day/time changes
    LaunchedEffect(selectedRoom, selectedDay, selectedTimeSlot) {
        selectedRoom?.let { room ->
            isLoading = true
            try {
                // Load computers for the selected room
                computers = database.computerDao().getComputersByRoom(room.id).first()

                // Load booking information
                bookings = loadRoomBookingInfo(database, room.id, selectedDay, selectedTimeSlot)
            } catch (e: Exception) {
                errorMessage = "Failed to load room data: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Room Layout",
                style = MaterialTheme.typography.h5
            )
            TextButton(onClick = onBack) {
                Text("Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Success/Error Messages
        if (successMessage.isNotEmpty()) {
            Card(
                backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = successMessage,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }

        if (errorMessage.isNotEmpty()) {
            Card(
                backgroundColor = MaterialTheme.colors.error.copy(alpha = 0.1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }

        // Selection Controls
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = 2.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Room Selection
                Text("Select Room:", style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(rooms) { room ->
                        FilterChip(
                            selected = selectedRoom?.id == room.id,
                            onClick = { selectedRoom = room },
                            content = { Text(room.roomNumber) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Day Selection
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

                // Time Slot Selection
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
            }
        }

        // Legend
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                LegendItem(color = Color.Green, text = "Available")
                LegendItem(color = Color.Red, text = "Booked")
                LegendItem(color = Color.Magenta, text = "Your Booking")
            }
        }

        // Room Layout
        selectedRoom?.let { room ->
            Text(
                text = "${room.roomNumber} - ${selectedDay} ${selectedTimeSlot}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                RoomLayoutGrid(
                    computers = computers,
                    bookings = bookings,
                    currentUser = currentUser,
                    onComputerClick = { computer, bookingInfo ->
                        selectedComputer = computer
                        selectedBookingInfo = bookingInfo
                        showBookingDialog = true
                        errorMessage = ""
                        successMessage = ""
                    }
                )
            }
        }
    }

    // Computer Action Dialog
    if (showBookingDialog && selectedComputer != null) {
        ComputerActionDialog(
            computer = selectedComputer!!,
            bookingInfo = selectedBookingInfo,
            currentUser = currentUser,
            database = database,
            day = selectedDay,
            timeSlot = selectedTimeSlot,
            onDismiss = {
                showBookingDialog = false
                selectedComputer = null
                selectedBookingInfo = null
            },
            onSuccess = { message ->
                showBookingDialog = false
                selectedComputer = null
                selectedBookingInfo = null
                successMessage = message
                // Refresh bookings
                scope.launch {
                    selectedRoom?.let { room ->
                        bookings = loadRoomBookingInfo(database, room.id, selectedDay, selectedTimeSlot)
                    }
                }
            },
            onError = { error ->
                errorMessage = error
            }
        )
    }
}

// Data class to hold booking information for room view
data class RoomBookingInfo(
    val computer: Computer,
    val booking: Booking?,
    val userName: String?,
    val userEmail: String?
)

@Composable
private fun RoomLayoutGrid(
    computers: List<Computer>,
    bookings: List<RoomBookingInfo>,
    currentUser: User,
    onComputerClick: (Computer, RoomBookingInfo?) -> Unit
) {
    // Sort computers by computer number
    val sortedComputers = computers.sortedBy { it.computerNumber }

    // Group computers into rows of 5
    val computerRows = sortedComputers.chunked(5)

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(computerRows.withIndex().toList()) { (rowIndex, row) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { computer ->
                    val bookingInfo = bookings.find { it.computer.id == computer.id }
                    val booking = bookingInfo?.booking

                    val computerColor = when {
                        booking?.userId == currentUser.id -> Color.Magenta // User's booking
                        booking != null -> Color.Red // Booked by someone else
                        else -> Color.Green // Available
                    }

                    ComputerBox(
                        computer = computer,
                        color = computerColor,
                        onClick = { onComputerClick(computer, bookingInfo) }
                    )
                }

                // Fill remaining spaces in the row if needed
                repeat(5 - row.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ComputerBox(
    computer: Computer,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(
                color = color.copy(alpha = 0.8f),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = computer.computerNumber.toString(),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LegendItem(color: Color, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(
                    color = color.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.dp,
                    color = color,
                    shape = RoundedCornerShape(4.dp)
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
private fun ComputerActionDialog(
    computer: Computer,
    bookingInfo: RoomBookingInfo?,
    currentUser: User,
    database: BookingDatabase,
    day: String,
    timeSlot: String,
    onDismiss: () -> Unit,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val booking = bookingInfo?.booking

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Computer ${computer.globalId}") },
        text = {
            Column {
                Text("Room: ${computer.globalId.split("-")[0]}")
                Text("Computer Number: ${computer.computerNumber}")
                Text("Time: $day at $timeSlot")

                Spacer(modifier = Modifier.height(12.dp))

                when {
                    booking == null -> {
                        // Available computer
                        Text(
                            text = "This computer is available for booking.",
                            color = MaterialTheme.colors.primary
                        )
                    }
                    booking.userId == currentUser.id -> {
                        // User's own booking
                        Text(
                            text = "You have booked this computer.",
                            color = Color.Magenta
                        )
                    }
                    else -> {
                        // Booked by someone else
                        Text(
                            text = "This computer is booked by:",
                            color = MaterialTheme.colors.error
                        )
                        bookingInfo?.let { info ->
                            Spacer(modifier = Modifier.height(8.dp))
                            Card(
                                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.5f)
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp)
                                ) {
                                    Text(
                                        text = "Name: ${info.userName ?: "Unknown"}",
                                        style = MaterialTheme.typography.body2
                                    )
                                    Text(
                                        text = "Email: ${info.userEmail ?: "Unknown"}",
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            when {
                booking == null -> {
                    // Available - show book button
                    Button(
                        onClick = {
                            scope.launch {
                                isLoading = true
                                try {
                                    val newBooking = Booking(
                                        userId = currentUser.id,
                                        computerId = computer.id,
                                        roomId = computer.roomId,
                                        dayOfWeek = day,
                                        timeSlot = timeSlot,
                                        bookingDate = Clock.System.now().toString()
                                    )
                                    database.bookingDao().upsert(newBooking)
                                    onSuccess("Successfully booked ${computer.globalId}")
                                } catch (e: Exception) {
                                    onError("Failed to book computer: ${e.message}")
                                } finally {
                                    isLoading = false
                                }
                            }
                        },
                        enabled = !isLoading,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                color = MaterialTheme.colors.onPrimary
                            )
                        } else {
                            Text("Book Computer")
                        }
                    }
                }
                booking?.userId == currentUser.id -> {
                    // User's booking - show cancel button
                    Button(
                        onClick = {
                            scope.launch {
                                isLoading = true
                                try {
                                    database.bookingDao().delete(booking)
                                    onSuccess("Successfully canceled booking for ${computer.globalId}")
                                } catch (e: Exception) {
                                    onError("Failed to cancel booking: ${e.message}")
                                } finally {
                                    isLoading = false
                                }
                            }
                        },
                        enabled = !isLoading,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.error
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                color = MaterialTheme.colors.onPrimary
                            )
                        } else {
                            Text("Cancel Booking")
                        }
                    }
                }
                currentUser.isAdmin -> {
                    // Admin viewing someone else's booking - show admin cancel button
                    Button(
                        onClick = {
                            scope.launch {
                                isLoading = true
                                try {
                                    booking?.let { database.bookingDao().delete(it) }
                                    onSuccess("Successfully canceled booking for ${computer.globalId}")
                                } catch (e: Exception) {
                                    onError("Failed to cancel booking: ${e.message}")
                                } finally {
                                    isLoading = false
                                }
                            }
                        },
                        enabled = !isLoading,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.error
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                color = MaterialTheme.colors.onPrimary
                            )
                        } else {
                            Text("Cancel Booking (Admin)")
                        }
                    }
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isLoading
            ) {
                Text("Close")
            }
        }
    )
}

// Helper function to load booking information for room view
private suspend fun loadRoomBookingInfo(
    database: BookingDatabase,
    roomId: Int,
    day: String,
    timeSlot: String
): List<RoomBookingInfo> {
    return try {
        val computers = database.computerDao().getComputersByRoom(roomId).first()
        val bookings = database.bookingDao().getBookingsByRoomAndDay(roomId, day).first()
            .filter { it.timeSlot == timeSlot }

        computers.map { computer ->
            val booking = bookings.find { it.computerId == computer.id }
            val user = booking?.let { database.userDao().getUserById(it.userId) }

            RoomBookingInfo(
                computer = computer,
                booking = booking,
                userName = user?.name,
                userEmail = user?.email
            )
        }
    } catch (e: Exception) {
        emptyList()
    }
}