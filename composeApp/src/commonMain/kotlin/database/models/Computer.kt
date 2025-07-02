package database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Computer(
    val roomId: Int,
    val computerNumber: Int,
    val globalId: String, // e.g., "JM606-12"
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
