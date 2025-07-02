import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import auth.AuthState
import database.BookingDatabase
import database.models.Computer
import database.models.Room
import database.models.User
import kotlinx.coroutines.flow.first
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.AdminDashboard
import screens.HomeScreen
import screens.LoginScreen
import screens.RoomViewScreen
import screens.SearchComputersScreen
import screens.SignupScreen

@Composable
@Preview
fun App(database: BookingDatabase) {
    MaterialTheme {
        var authState by remember { mutableStateOf(AuthState()) }
        var currentScreen by remember { mutableStateOf("login") }

        // Initialize admin user if not exists
        LaunchedEffect(Unit) {
            val adminUser = database.userDao().getUserByEmail("admin@test.com")
            if (adminUser == null) {
                database.userDao().upsert(
                    User(
                        name = "Administrator",
                        email = "admin@test.com",
                        password = "admin123",
                        isAdmin = true
                    )
                )
            }
        }

        LaunchedEffect("initialize_sample_data") {
            // Initialize sample rooms and computers if they don't exist
            val existingRooms = database.roomDao().getAllRooms().first()
            if (existingRooms.isEmpty()) {
                // Create sample rooms
                val sampleRooms = listOf(
                    Room(roomNumber = "LAB 1", computerCount = 5),
                    Room(roomNumber = "LAB 2", computerCount = 5)
                )

                sampleRooms.forEach { room ->
                    database.roomDao().upsert(room)
                }

                // Setup computers for each room
                val createdRooms = database.roomDao().getAllRooms().first()
                createdRooms.forEach { room ->
                    for (i in 1..room.computerCount) {
                        val computer = Computer(
                            roomId = room.id,
                            computerNumber = i,
                            globalId = "${room.roomNumber}-$i"
                        )
                        database.computerDao().upsert(computer)
                    }
                }
            }
        }

        when {
            !authState.isLoggedIn -> {
                when (currentScreen) {
                    "login" -> LoginScreen(
                        database = database,
                        onLoginSuccess = { user ->
                            authState = authState.copy(isLoggedIn = true, currentUser = user)
                            currentScreen = "home"
                        },
                        onNavigateToSignup = { currentScreen = "signup" }
                    )
                    "signup" -> SignupScreen(
                        database = database,
                        onSignupSuccess = { user ->
                            authState = authState.copy(isLoggedIn = true, currentUser = user)
                            currentScreen = "home"
                        },
                        onNavigateToLogin = { currentScreen = "login" }
                    )
                }
            }
            else -> {
                when (currentScreen) {
                    "home" -> HomeScreen(
                        database = database,
                        currentUser = authState.currentUser!!,
                        onLogout = {
                            authState = AuthState()
                            currentScreen = "login"
                        },
                        onNavigateToSearch = { currentScreen = "search" },
                        onNavigateToAdmin = { currentScreen = "admin" },
                        onNavigateToRoomView = { currentScreen = "room_view" }
                    )
                    "search" -> SearchComputersScreen(
                        database = database,
                        currentUser = authState.currentUser!!,
                        onBack = { currentScreen = "home" }
                    )
                    "admin" -> {
                        if (authState.currentUser?.isAdmin == true) {
                            AdminDashboard(
                                database = database,
                                onBack = { currentScreen = "home" }
                            )
                        } else {
                            currentScreen = "home"
                        }
                    }
                    "room_view" -> RoomViewScreen(
                        database = database,
                        currentUser = authState.currentUser!!,
                        onBack = { currentScreen = "home" }
                    )
                }
            }
        }
    }
}
