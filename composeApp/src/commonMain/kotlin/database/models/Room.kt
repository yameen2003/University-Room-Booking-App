package database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class Room(
    val roomNumber: String,
    val computerCount: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
