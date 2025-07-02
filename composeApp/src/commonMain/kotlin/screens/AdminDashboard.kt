package screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import database.BookingDatabase
import database.models.Booking
import database.models.Room
import database.models.Computer
import database.models.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import components.FilterChip

@Composable
fun AdminDashboard(
    database: BookingDatabase,
    onBack: () -> Unit
) {
    var currentTab by remember { mutableStateOf("rooms") }
    var rooms by remember { mutableStateOf<List<Room>>(emptyList()) }
    val scope = rememberCoroutineScope()

    // Load rooms when screen opens
    LaunchedEffect(Unit) {
        database.roomDao().getAllRooms().collect { roomList ->
            rooms = roomList
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
                text = "Admin Dashboard",
                style = MaterialTheme.typography.h5
            )
            TextButton(onClick = onBack) {
                Text("Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tab Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabButton(
                text = "Rooms",
                selected = currentTab == "rooms",
                onClick = { currentTab = "rooms" },
                modifier = Modifier.weight(1f)
            )
            TabButton(
                text = "Users",
                selected = currentTab == "users",
                onClick = { currentTab = "users" },
                modifier = Modifier.weight(1f)
            )
            TabButton(
                text = "Bookings",
                selected = currentTab == "bookings",
                onClick = { currentTab = "bookings" },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Content based on selected tab
        when (currentTab) {
            "rooms" -> RoomManagementTab(
                database = database,
                rooms = rooms,
                onRoomAdded = {
                    // Refresh rooms list
                    scope.launch {
                        rooms = database.roomDao().getAllRooms().first()
                    }
                }
            )
            "users" -> UserManagementTab(database = database)
            "bookings" -> BookingsTab(database = database)
        }
    }
}

@Composable
fun TabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            contentColor = if (selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
        ),
        modifier = modifier.padding(horizontal = 2.dp)
    ) {
        Text(
            text = text,
            maxLines = 1,
            style = MaterialTheme.typography.button.copy(fontSize = 12.sp)
        )
    }
}

@Composable
fun RoomManagementTab(
    database: BookingDatabase,
    rooms: List<Room>,
    onRoomAdded: () -> Unit
) {
    var showAddRoomDialog by remember { mutableStateOf(false) }

    Column {
        // Add Room Button
        Button(
            onClick = { showAddRoomDialog = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        ) {
            Text("Add New Room")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Rooms List
        Text(
            text = "Existing Rooms (${rooms.size})",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(rooms) { room ->
                RoomCard(room = room, database = database)
            }
        }
    }

    // Add Room Dialog
    if (showAddRoomDialog) {
        AddRoomDialog(
            database = database,
            onDismiss = { showAddRoomDialog = false },
            onRoomAdded = {
                showAddRoomDialog = false
                onRoomAdded()
            }
        )
    }
}

@Composable
fun RoomCard(
    room: Room,
    database: BookingDatabase
) {
    var computerCount by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    // Load computer count for this room
    LaunchedEffect(room.id) {
        val computers = database.computerDao().getComputersByRoom(room.id).first()
        computerCount = computers.size
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Room: ${room.roomNumber}",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Expected Computers: ${room.computerCount}",
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "Actual Computers: $computerCount",
                style = MaterialTheme.typography.body2,
                color = if (computerCount == room.computerCount) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.error
                }
            )

            if (computerCount != room.computerCount) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        scope.launch {
                            setupComputersForRoom(database, room)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                ) {
                    Text("Setup Computers")
                }
            }
        }
    }
}

@Composable
fun AddRoomDialog(
    database: BookingDatabase,
    onDismiss: () -> Unit,
    onRoomAdded: () -> Unit
) {
    var roomNumber by remember { mutableStateOf("") }
    var computerCountText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Room") },
        text = {
            Column {
                OutlinedTextField(
                    value = roomNumber,
                    onValueChange = { roomNumber = it },
                    label = { Text("Room Number (e.g., JM606)") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = computerCountText,
                    onValueChange = { computerCountText = it },
                    label = { Text("Number of Computers (multiple of 5)") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading,
                    isError = computerCountText.isNotEmpty() &&
                            (computerCountText.toIntOrNull()?.let { it % 5 != 0 } ?: true)
                )

                if (computerCountText.isNotEmpty() &&
                    (computerCountText.toIntOrNull()?.let { it % 5 != 0 } ?: true)) {
                    Text(
                        text = "Number of computers must be a multiple of 5",
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val computerCount = computerCountText.toIntOrNull()
                    when {
                        roomNumber.isBlank() -> {
                            errorMessage = "Room number cannot be empty"
                        }
                        computerCount == null || computerCount <= 0 -> {
                            errorMessage = "Please enter a valid number of computers"
                        }
                        computerCount % 5 != 0 -> {
                            errorMessage = "Number of computers must be a multiple of 5"
                        }
                        else -> {
                            scope.launch {
                                isLoading = true
                                errorMessage = ""
                                try {
                                    // Check if room already exists
                                    val existingRooms = database.roomDao().getAllRooms().first()
                                    val roomExists = existingRooms.any {
                                        it.roomNumber.equals(roomNumber, ignoreCase = true)
                                    }

                                    if (roomExists) {
                                        errorMessage = "Room with this number already exists"
                                    } else {
                                        // Create room
                                        val newRoom = Room(
                                            roomNumber = roomNumber.uppercase(),
                                            computerCount = computerCount
                                        )
                                        database.roomDao().upsert(newRoom)

                                        // Get the created room to setup computers
                                        val createdRooms = database.roomDao().getAllRooms().first()
                                        val createdRoom = createdRooms.find {
                                            it.roomNumber == roomNumber.uppercase()
                                        }

                                        if (createdRoom != null) {
                                            setupComputersForRoom(database, createdRoom)
                                        }

                                        onRoomAdded()
                                    }
                                } catch (e: Exception) {
                                    errorMessage = "Failed to create room: ${e.message}"
                                } finally {
                                    isLoading = false
                                }
                            }
                        }
                    }
                },
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = MaterialTheme.colors.onPrimary
                    )
                } else {
                    Text("Add Room")
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isLoading
            ) {
                Text("Cancel")
            }
        }
    )
}

// Helper function to setup computers for a room
private suspend fun setupComputersForRoom(database: BookingDatabase, room: Room) {
    try {
        // Remove existing computers for this room
        val existingComputers = database.computerDao().getComputersByRoom(room.id).first()
        existingComputers.forEach { computer ->
            database.computerDao().delete(computer)
        }

        // Create new computers
        for (i in 1..room.computerCount) {
            val computer = Computer(
                roomId = room.id,
                computerNumber = i,
                globalId = "${room.roomNumber}-$i"
            )
            database.computerDao().upsert(computer)
        }
    } catch (e: Exception) {
        // Handle error silently for now
        println("Error setting up computers: ${e.message}")
    }
}

@Composable
fun UserManagementTab(database: BookingDatabase) {
    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var showEditUserDialog by remember { mutableStateOf(false) }
    var selectedUser by remember { mutableStateOf<User?>(null) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var userToDelete by remember { mutableStateOf<User?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    // Load users when tab opens
    LaunchedEffect(Unit) {
        database.userDao().getAllUsers().collect { userList ->
            users = userList
        }
    }

    Column {
        Text(
            text = "User Management",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

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
                    modifier = Modifier.padding(8.dp),
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
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }

        Text(
            text = "All Users (${users.size})",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(users) { user ->
                UserCard(
                    user = user,
                    onEditClick = {
                        selectedUser = user
                        showEditUserDialog = true
                        errorMessage = ""
                        successMessage = ""
                    },
                    onDeleteClick = {
                        userToDelete = user
                        showDeleteConfirmation = true
                        errorMessage = ""
                        successMessage = ""
                    }
                )
            }
        }
    }

    // Edit User Dialog
    if (showEditUserDialog && selectedUser != null) {
        EditUserDialog(
            user = selectedUser!!,
            database = database,
            onDismiss = {
                showEditUserDialog = false
                selectedUser = null
            },
            onUserUpdated = {
                showEditUserDialog = false
                selectedUser = null
                successMessage = "User updated successfully"
                // Refresh users list
                scope.launch {
                    users = database.userDao().getAllUsers().first()
                }
            },
            onError = { error ->
                errorMessage = error
            }
        )
    }

    // Delete Confirmation Dialog
    if (showDeleteConfirmation && userToDelete != null) {
        DeleteUserConfirmationDialog(
            user = userToDelete!!,
            database = database,
            onDismiss = {
                showDeleteConfirmation = false
                userToDelete = null
            },
            onUserDeleted = {
                showDeleteConfirmation = false
                userToDelete = null
                successMessage = "User deleted successfully"
                // Refresh users list
                scope.launch {
                    users = database.userDao().getAllUsers().first()
                }
            },
            onError = { error ->
                errorMessage = error
            }
        )
    }
}

@Composable
fun UserCard(
    user: User,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    if (user.contactDetails.isNotEmpty()) {
                        Text(
                            text = "Contact: ${user.contactDetails}",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        )
                    }

                    // Admin badge
                    if (user.isAdmin) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            color = MaterialTheme.colors.secondary,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = "ADMIN",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onSecondary
                            )
                        }
                    }
                }

                Column {
                    Button(
                        onClick = onEditClick,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text("Edit", style = MaterialTheme.typography.caption)
                    }

                    // Don't allow deleting admin users (to prevent lockout)
                    if (!user.isAdmin) {
                        Button(
                            onClick = onDeleteClick,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.error
                            )
                        ) {
                            Text("Delete", style = MaterialTheme.typography.caption)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EditUserDialog(
    user: User,
    database: BookingDatabase,
    onDismiss: () -> Unit,
    onUserUpdated: () -> Unit,
    onError: (String) -> Unit
) {
    var name by remember { mutableStateOf(user.name) }
    var email by remember { mutableStateOf(user.email) }
    var contactDetails by remember { mutableStateOf(user.contactDetails) }
    var isAdmin by remember { mutableStateOf(user.isAdmin) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit User") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = contactDetails,
                    onValueChange = { contactDetails = it },
                    label = { Text("Contact Details") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isAdmin,
                        onCheckedChange = { isAdmin = it },
                        enabled = !isLoading
                    )
                    Text(
                        text = "Admin User",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    when {
                        name.isBlank() -> onError("Name cannot be empty")
                        email.isBlank() -> onError("Email cannot be empty")
                        !email.contains("@") -> onError("Please enter a valid email")
                        else -> {
                            scope.launch {
                                isLoading = true
                                try {
                                    // Check if email is already taken by another user
                                    val existingUser = database.userDao().getUserByEmail(email)
                                    if (existingUser != null && existingUser.id != user.id) {
                                        onError("Email is already taken by another user")
                                    } else {
                                        val updatedUser = user.copy(
                                            name = name.trim(),
                                            email = email.trim().lowercase(),
                                            contactDetails = contactDetails.trim(),
                                            isAdmin = isAdmin
                                        )
                                        database.userDao().upsert(updatedUser)
                                        onUserUpdated()
                                    }
                                } catch (e: Exception) {
                                    onError("Failed to update user: ${e.message}")
                                } finally {
                                    isLoading = false
                                }
                            }
                        }
                    }
                },
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = MaterialTheme.colors.onPrimary
                    )
                } else {
                    Text("Save Changes")
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isLoading
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun DeleteUserConfirmationDialog(
    user: User,
    database: BookingDatabase,
    onDismiss: () -> Unit,
    onUserDeleted: () -> Unit,
    onError: (String) -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete User") },
        text = {
            Column {
                Text("Are you sure you want to delete this user?")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Name: ${user.name}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                Text(
                    text = "Email: ${user.email}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "This action cannot be undone. All bookings made by this user will also be deleted.",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.error
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        try {
                            // First, delete all bookings made by this user
                            val userBookings = database.bookingDao().getBookingsByUser(user.id).first()
                            userBookings.forEach { booking ->
                                database.bookingDao().delete(booking)
                            }

                            // Then delete the user
                            database.userDao().delete(user)
                            onUserDeleted()
                        } catch (e: Exception) {
                            onError("Failed to delete user: ${e.message}")
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
                    Text("Delete User")
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isLoading
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun BookingsTab(database: BookingDatabase) {
    var bookings by remember { mutableStateOf<List<BookingWithDetails>>(emptyList()) }
    var rooms by remember { mutableStateOf<List<Room>>(emptyList()) }
    var selectedRoomId by remember { mutableStateOf<Int?>(null) }
    var selectedDay by remember { mutableStateOf("All Days") }
    var showCancelConfirmation by remember { mutableStateOf(false) }
    var bookingToCancel by remember { mutableStateOf<BookingWithDetails?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val daysOfWeek = listOf("All Days", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    // Load initial data
    LaunchedEffect(Unit) {
        // Load rooms
        database.roomDao().getAllRooms().collect { roomList ->
            rooms = roomList
        }
    }

    // Load bookings when filters change
    LaunchedEffect(selectedRoomId, selectedDay) {
        isLoading = true
        try {
            bookings = loadBookingsWithDetails(database, selectedRoomId, selectedDay)
        } catch (e: Exception) {
            errorMessage = "Failed to load bookings: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Column {
        Text(
            text = "Booking Management",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

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
                    modifier = Modifier.padding(8.dp),
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
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }

        // Filters Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = 2.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Filters",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Room Filter
                Text("Filter by Room:", style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        FilterChip(
                            selected = selectedRoomId == null,
                            onClick = { selectedRoomId = null },
                            content = { Text("All Rooms") }
                        )
                    }
                    items(rooms) { room ->
                        FilterChip(
                            selected = selectedRoomId == room.id,
                            onClick = { selectedRoomId = room.id },
                            content = { Text(room.roomNumber) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Day Filter
                Text("Filter by Day:", style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.height(4.dp))
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
            }
        }

        // Bookings List
        Text(
            text = "Bookings (${bookings.size})",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (bookings.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 1.dp
            ) {
                Text(
                    text = "No bookings found with the current filters.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(bookings) { booking ->
                    BookingCard(
                        booking = booking,
                        onCancelClick = {
                            bookingToCancel = booking
                            showCancelConfirmation = true
                            errorMessage = ""
                            successMessage = ""
                        }
                    )
                }
            }
        }
    }

    // Cancel Booking Confirmation Dialog
    if (showCancelConfirmation && bookingToCancel != null) {
        CancelBookingConfirmationDialog(
            booking = bookingToCancel!!,
            database = database,
            onDismiss = {
                showCancelConfirmation = false
                bookingToCancel = null
            },
            onBookingCanceled = {
                showCancelConfirmation = false
                bookingToCancel = null
                successMessage = "Booking canceled successfully"
                // Refresh bookings
                scope.launch {
                    bookings = loadBookingsWithDetails(database, selectedRoomId, selectedDay)
                }
            },
            onError = { error ->
                errorMessage = error
            }
        )
    }
}

// Data class to hold booking with related details
data class BookingWithDetails(
    val booking: Booking,
    val userName: String,
    val userEmail: String,
    val roomNumber: String,
    val computerGlobalId: String
)

private suspend fun loadBookingsWithDetails(
    database: BookingDatabase,
    roomId: Int?,
    day: String
): List<BookingWithDetails> {
    return try {
        // Get all bookings first
        val allBookings = mutableListOf<Booking>()

        if (roomId != null && day != "All Days") {
            // Filter by both room and day
            allBookings.addAll(database.bookingDao().getBookingsByRoomAndDay(roomId, day).first())
        } else if (roomId != null) {
            // Filter by room only - get all days for this room
            val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            for (dayOfWeek in daysOfWeek) {
                allBookings.addAll(database.bookingDao().getBookingsByRoomAndDay(roomId, dayOfWeek).first())
            }
        } else if (day != "All Days") {
            // Filter by day only - get all rooms for this day
            val rooms = database.roomDao().getAllRooms().first()
            for (room in rooms) {
                allBookings.addAll(database.bookingDao().getBookingsByRoomAndDay(room.id, day).first())
            }
        } else {
            // Get all bookings
            val rooms = database.roomDao().getAllRooms().first()
            val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            for (room in rooms) {
                for (dayOfWeek in daysOfWeek) {
                    allBookings.addAll(database.bookingDao().getBookingsByRoomAndDay(room.id, dayOfWeek).first())
                }
            }
        }

        // Convert to BookingWithDetails
        allBookings.mapNotNull { booking ->
            try {
                val user = database.userDao().getUserById(booking.userId)
                val room = database.roomDao().getRoomById(booking.roomId)
                val computer = database.computerDao().getComputerById(booking.computerId)

                if (user != null && room != null && computer != null) {
                    BookingWithDetails(
                        booking = booking,
                        userName = user.name,
                        userEmail = user.email,
                        roomNumber = room.roomNumber,
                        computerGlobalId = computer.globalId
                    )
                } else null
            } catch (e: Exception) {
                null
            }
        }.sortedWith(
            compareBy<BookingWithDetails> { booking ->
                // Sort by day of week
                when (booking.booking.dayOfWeek) {
                    "Monday" -> 1
                    "Tuesday" -> 2
                    "Wednesday" -> 3
                    "Thursday" -> 4
                    "Friday" -> 5
                    "Saturday" -> 6
                    "Sunday" -> 7
                    else -> 8
                }
            }.thenBy { it.booking.timeSlot }
                .thenBy { it.roomNumber }
                .thenBy { it.computerGlobalId }
        )
    } catch (e: Exception) {
        emptyList()
    }
}

@Composable
fun BookingCard(
    booking: BookingWithDetails,
    onCancelClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Computer and Room Info
                    Text(
                        text = "Computer: ${booking.computerGlobalId}",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = "Room: ${booking.roomNumber}",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Time Info
                    Text(
                        text = "${booking.booking.dayOfWeek} • ${booking.booking.timeSlot}",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // User Info
                    Text(
                        text = "Booked by: ${booking.userName}",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "Email: ${booking.userEmail}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )

                    // Booking Date
                    Text(
                        text = "Booking ID: ${booking.booking.id}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Button(
                    onClick = onCancelClick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.error
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Cancel", style = MaterialTheme.typography.caption)
                }
            }
        }
    }
}

@Composable
fun CancelBookingConfirmationDialog(
    booking: BookingWithDetails,
    database: BookingDatabase,
    onDismiss: () -> Unit,
    onBookingCanceled: () -> Unit,
    onError: (String) -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cancel Booking") },
        text = {
            Column {
                Text("Are you sure you want to cancel this booking?")
                Spacer(modifier = Modifier.height(12.dp))

                // Booking Details
                Card(
                    backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Computer: ${booking.computerGlobalId}",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = "Room: ${booking.roomNumber}",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "Time: ${booking.booking.dayOfWeek} at ${booking.booking.timeSlot}",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "User: ${booking.userName} (${booking.userEmail})",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "This action cannot be undone.",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.error
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        try {
                            database.bookingDao().delete(booking.booking)
                            onBookingCanceled()
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
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isLoading
            ) {
                Text("Keep Booking")
            }
        }
    )
}
