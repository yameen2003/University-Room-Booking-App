package database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val name: String,
    val email: String,
    val password: String,
    val contactDetails: String = "",
    val isAdmin: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
