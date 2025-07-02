package database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Booking(
    val userId: Int,
    val computerId: Int,
    val roomId: Int,
    val dayOfWeek: String, // "Monday", "Tuesday", etc.
    val timeSlot: String, // "09:00-10:00", "10:00-11:00", etc.
    val bookingDate: String, // ISO date string
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
