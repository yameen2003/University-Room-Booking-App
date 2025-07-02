import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getBookingDatabase

fun MainViewController() = ComposeUIViewController {
    val database = remember {
        getBookingDatabase()
    }
    App(database)
}