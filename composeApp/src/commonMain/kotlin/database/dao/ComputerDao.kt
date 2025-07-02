package database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import database.models.Computer
import kotlinx.coroutines.flow.Flow

@Dao
interface ComputerDao {

    @Upsert
    suspend fun upsert(computer: Computer)

    @Delete
    suspend fun delete(computer: Computer)

    @Query("SELECT * FROM computer WHERE roomId = :roomId")
    fun getComputersByRoom(roomId: Int): Flow<List<Computer>>

    @Query("SELECT * FROM computer WHERE id = :computerId")
    suspend fun getComputerById(computerId: Int): Computer?
}
