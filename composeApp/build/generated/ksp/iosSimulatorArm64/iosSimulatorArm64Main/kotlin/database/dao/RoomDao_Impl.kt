package database.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import database.models.Room
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RoomDao_Impl(
  __db: RoomDatabase,
) : RoomDao {
  private val __db: RoomDatabase

  private val __deleteAdapterOfRoom: EntityDeleteOrUpdateAdapter<Room>

  private val __upsertAdapterOfRoom: EntityUpsertAdapter<Room>
  init {
    this.__db = __db
    this.__deleteAdapterOfRoom = object : EntityDeleteOrUpdateAdapter<Room>() {
      protected override fun createQuery(): String = "DELETE FROM `rooms` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Room) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__upsertAdapterOfRoom = EntityUpsertAdapter<Room>(object : EntityInsertAdapter<Room>() {
      protected override fun createQuery(): String =
          "INSERT INTO `rooms` (`roomNumber`,`computerCount`,`id`) VALUES (?,?,nullif(?, 0))"

      protected override fun bind(statement: SQLiteStatement, entity: Room) {
        statement.bindText(1, entity.roomNumber)
        statement.bindLong(2, entity.computerCount.toLong())
        statement.bindLong(3, entity.id.toLong())
      }
    }, object : EntityDeleteOrUpdateAdapter<Room>() {
      protected override fun createQuery(): String =
          "UPDATE `rooms` SET `roomNumber` = ?,`computerCount` = ?,`id` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Room) {
        statement.bindText(1, entity.roomNumber)
        statement.bindLong(2, entity.computerCount.toLong())
        statement.bindLong(3, entity.id.toLong())
        statement.bindLong(4, entity.id.toLong())
      }
    })
  }

  public override suspend fun delete(room: Room): Unit = performSuspending(__db, false, true) {
      _connection ->
    __deleteAdapterOfRoom.handle(_connection, room)
  }

  public override suspend fun upsert(room: Room): Unit = performSuspending(__db, false, true) {
      _connection ->
    __upsertAdapterOfRoom.upsert(_connection, room)
  }

  public override fun getAllRooms(): Flow<List<Room>> {
    val _sql: String = "SELECT * FROM rooms"
    return createFlow(__db, false, arrayOf("rooms")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfRoomNumber: Int = getColumnIndexOrThrow(_stmt, "roomNumber")
        val _cursorIndexOfComputerCount: Int = getColumnIndexOrThrow(_stmt, "computerCount")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: MutableList<Room> = mutableListOf()
        while (_stmt.step()) {
          val _item: Room
          val _tmpRoomNumber: String
          _tmpRoomNumber = _stmt.getText(_cursorIndexOfRoomNumber)
          val _tmpComputerCount: Int
          _tmpComputerCount = _stmt.getLong(_cursorIndexOfComputerCount).toInt()
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _item = Room(_tmpRoomNumber,_tmpComputerCount,_tmpId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRoomById(roomId: Int): Room? {
    val _sql: String = "SELECT * FROM rooms WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, roomId.toLong())
        val _cursorIndexOfRoomNumber: Int = getColumnIndexOrThrow(_stmt, "roomNumber")
        val _cursorIndexOfComputerCount: Int = getColumnIndexOrThrow(_stmt, "computerCount")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: Room?
        if (_stmt.step()) {
          val _tmpRoomNumber: String
          _tmpRoomNumber = _stmt.getText(_cursorIndexOfRoomNumber)
          val _tmpComputerCount: Int
          _tmpComputerCount = _stmt.getLong(_cursorIndexOfComputerCount).toInt()
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _result = Room(_tmpRoomNumber,_tmpComputerCount,_tmpId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
