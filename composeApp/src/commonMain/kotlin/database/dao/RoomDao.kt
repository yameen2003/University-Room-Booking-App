package database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import database.models.Room
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Upsert
    suspend fun upsert(room: Room)

    @Delete
    suspend fun delete(room: Room)

    @Query("SELECT * FROM rooms")
    fun getAllRooms(): Flow<List<Room>>

    @Query("SELECT * FROM rooms WHERE id = :roomId")
    suspend fun getRoomById(roomId: Int): Room?
}
