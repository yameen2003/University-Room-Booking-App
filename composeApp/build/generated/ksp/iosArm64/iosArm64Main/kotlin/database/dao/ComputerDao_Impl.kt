package database.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import database.models.Computer
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
public class ComputerDao_Impl(
  __db: RoomDatabase,
) : ComputerDao {
  private val __db: RoomDatabase

  private val __deleteAdapterOfComputer: EntityDeleteOrUpdateAdapter<Computer>

  private val __upsertAdapterOfComputer: EntityUpsertAdapter<Computer>
  init {
    this.__db = __db
    this.__deleteAdapterOfComputer = object : EntityDeleteOrUpdateAdapter<Computer>() {
      protected override fun createQuery(): String = "DELETE FROM `Computer` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Computer) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__upsertAdapterOfComputer = EntityUpsertAdapter<Computer>(object :
        EntityInsertAdapter<Computer>() {
      protected override fun createQuery(): String =
          "INSERT INTO `Computer` (`roomId`,`computerNumber`,`globalId`,`id`) VALUES (?,?,?,nullif(?, 0))"

      protected override fun bind(statement: SQLiteStatement, entity: Computer) {
        statement.bindLong(1, entity.roomId.toLong())
        statement.bindLong(2, entity.computerNumber.toLong())
        statement.bindText(3, entity.globalId)
        statement.bindLong(4, entity.id.toLong())
      }
    }, object : EntityDeleteOrUpdateAdapter<Computer>() {
      protected override fun createQuery(): String =
          "UPDATE `Computer` SET `roomId` = ?,`computerNumber` = ?,`globalId` = ?,`id` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Computer) {
        statement.bindLong(1, entity.roomId.toLong())
        statement.bindLong(2, entity.computerNumber.toLong())
        statement.bindText(3, entity.globalId)
        statement.bindLong(4, entity.id.toLong())
        statement.bindLong(5, entity.id.toLong())
      }
    })
  }

  public override suspend fun delete(computer: Computer): Unit = performSuspending(__db, false,
      true) { _connection ->
    __deleteAdapterOfComputer.handle(_connection, computer)
  }

  public override suspend fun upsert(computer: Computer): Unit = performSuspending(__db, false,
      true) { _connection ->
    __upsertAdapterOfComputer.upsert(_connection, computer)
  }

  public override fun getComputersByRoom(roomId: Int): Flow<List<Computer>> {
    val _sql: String = "SELECT * FROM computer WHERE roomId = ?"
    return createFlow(__db, false, arrayOf("computer")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, roomId.toLong())
        val _cursorIndexOfRoomId: Int = getColumnIndexOrThrow(_stmt, "roomId")
        val _cursorIndexOfComputerNumber: Int = getColumnIndexOrThrow(_stmt, "computerNumber")
        val _cursorIndexOfGlobalId: Int = getColumnIndexOrThrow(_stmt, "globalId")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: MutableList<Computer> = mutableListOf()
        while (_stmt.step()) {
          val _item: Computer
          val _tmpRoomId: Int
          _tmpRoomId = _stmt.getLong(_cursorIndexOfRoomId).toInt()
          val _tmpComputerNumber: Int
          _tmpComputerNumber = _stmt.getLong(_cursorIndexOfComputerNumber).toInt()
          val _tmpGlobalId: String
          _tmpGlobalId = _stmt.getText(_cursorIndexOfGlobalId)
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _item = Computer(_tmpRoomId,_tmpComputerNumber,_tmpGlobalId,_tmpId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getComputerById(computerId: Int): Computer? {
    val _sql: String = "SELECT * FROM computer WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, computerId.toLong())
        val _cursorIndexOfRoomId: Int = getColumnIndexOrThrow(_stmt, "roomId")
        val _cursorIndexOfComputerNumber: Int = getColumnIndexOrThrow(_stmt, "computerNumber")
        val _cursorIndexOfGlobalId: Int = getColumnIndexOrThrow(_stmt, "globalId")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: Computer?
        if (_stmt.step()) {
          val _tmpRoomId: Int
          _tmpRoomId = _stmt.getLong(_cursorIndexOfRoomId).toInt()
          val _tmpComputerNumber: Int
          _tmpComputerNumber = _stmt.getLong(_cursorIndexOfComputerNumber).toInt()
          val _tmpGlobalId: String
          _tmpGlobalId = _stmt.getText(_cursorIndexOfGlobalId)
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _result = Computer(_tmpRoomId,_tmpComputerNumber,_tmpGlobalId,_tmpId)
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
