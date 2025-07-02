package database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import database.models.Booking
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

    @Upsert
    suspend fun upsert(booking: Booking)

    @Delete
    suspend fun delete(booking: Booking)

    @Query("SELECT * FROM booking WHERE roomId = :roomId AND dayOfWeek = :dayOfWeek")
    fun getBookingsByRoomAndDay(roomId: Int, dayOfWeek: String): Flow<List<Booking>>

    @Query("SELECT * FROM booking WHERE userId = :userId")
    fun getBookingsByUser(userId: Int): Flow<List<Booking>>

    @Query("SELECT * FROM booking WHERE dayOfWeek = :dayOfWeek AND timeSlot = :timeSlot")
    suspend fun getBookingsByDayAndTime(dayOfWeek: String, timeSlot: String): List<Booking>

    @Query("SELECT * FROM booking WHERE computerId = :computerId AND dayOfWeek = :dayOfWeek AND timeSlot = :timeSlot")
    suspend fun getBookingByComputerDayTime(computerId: Int, dayOfWeek: String, timeSlot: String): Booking?
}
