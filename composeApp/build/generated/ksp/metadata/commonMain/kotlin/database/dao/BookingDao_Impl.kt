package database.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import database.models.Booking
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
public class BookingDao_Impl(
  __db: RoomDatabase,
) : BookingDao {
  private val __db: RoomDatabase

  private val __deleteAdapterOfBooking: EntityDeleteOrUpdateAdapter<Booking>

  private val __upsertAdapterOfBooking: EntityUpsertAdapter<Booking>
  init {
    this.__db = __db
    this.__deleteAdapterOfBooking = object : EntityDeleteOrUpdateAdapter<Booking>() {
      protected override fun createQuery(): String = "DELETE FROM `Booking` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Booking) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__upsertAdapterOfBooking = EntityUpsertAdapter<Booking>(object :
        EntityInsertAdapter<Booking>() {
      protected override fun createQuery(): String =
          "INSERT INTO `Booking` (`userId`,`computerId`,`roomId`,`dayOfWeek`,`timeSlot`,`bookingDate`,`id`) VALUES (?,?,?,?,?,?,nullif(?, 0))"

      protected override fun bind(statement: SQLiteStatement, entity: Booking) {
        statement.bindLong(1, entity.userId.toLong())
        statement.bindLong(2, entity.computerId.toLong())
        statement.bindLong(3, entity.roomId.toLong())
        statement.bindText(4, entity.dayOfWeek)
        statement.bindText(5, entity.timeSlot)
        statement.bindText(6, entity.bookingDate)
        statement.bindLong(7, entity.id.toLong())
      }
    }, object : EntityDeleteOrUpdateAdapter<Booking>() {
      protected override fun createQuery(): String =
          "UPDATE `Booking` SET `userId` = ?,`computerId` = ?,`roomId` = ?,`dayOfWeek` = ?,`timeSlot` = ?,`bookingDate` = ?,`id` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Booking) {
        statement.bindLong(1, entity.userId.toLong())
        statement.bindLong(2, entity.computerId.toLong())
        statement.bindLong(3, entity.roomId.toLong())
        statement.bindText(4, entity.dayOfWeek)
        statement.bindText(5, entity.timeSlot)
        statement.bindText(6, entity.bookingDate)
        statement.bindLong(7, entity.id.toLong())
        statement.bindLong(8, entity.id.toLong())
      }
    })
  }

  public override suspend fun delete(booking: Booking): Unit = performSuspending(__db, false, true)
      { _connection ->
    __deleteAdapterOfBooking.handle(_connection, booking)
  }

  public override suspend fun upsert(booking: Booking): Unit = performSuspending(__db, false, true)
      { _connection ->
    __upsertAdapterOfBooking.upsert(_connection, booking)
  }

  public override fun getBookingsByRoomAndDay(roomId: Int, dayOfWeek: String): Flow<List<Booking>> {
    val _sql: String = "SELECT * FROM booking WHERE roomId = ? AND dayOfWeek = ?"
    return createFlow(__db, false, arrayOf("booking")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, roomId.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, dayOfWeek)
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _cursorIndexOfComputerId: Int = getColumnIndexOrThrow(_stmt, "computerId")
        val _cursorIndexOfRoomId: Int = getColumnIndexOrThrow(_stmt, "roomId")
        val _cursorIndexOfDayOfWeek: Int = getColumnIndexOrThrow(_stmt, "dayOfWeek")
        val _cursorIndexOfTimeSlot: Int = getColumnIndexOrThrow(_stmt, "timeSlot")
        val _cursorIndexOfBookingDate: Int = getColumnIndexOrThrow(_stmt, "bookingDate")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: MutableList<Booking> = mutableListOf()
        while (_stmt.step()) {
          val _item: Booking
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          val _tmpComputerId: Int
          _tmpComputerId = _stmt.getLong(_cursorIndexOfComputerId).toInt()
          val _tmpRoomId: Int
          _tmpRoomId = _stmt.getLong(_cursorIndexOfRoomId).toInt()
          val _tmpDayOfWeek: String
          _tmpDayOfWeek = _stmt.getText(_cursorIndexOfDayOfWeek)
          val _tmpTimeSlot: String
          _tmpTimeSlot = _stmt.getText(_cursorIndexOfTimeSlot)
          val _tmpBookingDate: String
          _tmpBookingDate = _stmt.getText(_cursorIndexOfBookingDate)
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _item =
              Booking(_tmpUserId,_tmpComputerId,_tmpRoomId,_tmpDayOfWeek,_tmpTimeSlot,_tmpBookingDate,_tmpId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getBookingsByUser(userId: Int): Flow<List<Booking>> {
    val _sql: String = "SELECT * FROM booking WHERE userId = ?"
    return createFlow(__db, false, arrayOf("booking")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _cursorIndexOfComputerId: Int = getColumnIndexOrThrow(_stmt, "computerId")
        val _cursorIndexOfRoomId: Int = getColumnIndexOrThrow(_stmt, "roomId")
        val _cursorIndexOfDayOfWeek: Int = getColumnIndexOrThrow(_stmt, "dayOfWeek")
        val _cursorIndexOfTimeSlot: Int = getColumnIndexOrThrow(_stmt, "timeSlot")
        val _cursorIndexOfBookingDate: Int = getColumnIndexOrThrow(_stmt, "bookingDate")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: MutableList<Booking> = mutableListOf()
        while (_stmt.step()) {
          val _item: Booking
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          val _tmpComputerId: Int
          _tmpComputerId = _stmt.getLong(_cursorIndexOfComputerId).toInt()
          val _tmpRoomId: Int
          _tmpRoomId = _stmt.getLong(_cursorIndexOfRoomId).toInt()
          val _tmpDayOfWeek: String
          _tmpDayOfWeek = _stmt.getText(_cursorIndexOfDayOfWeek)
          val _tmpTimeSlot: String
          _tmpTimeSlot = _stmt.getText(_cursorIndexOfTimeSlot)
          val _tmpBookingDate: String
          _tmpBookingDate = _stmt.getText(_cursorIndexOfBookingDate)
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _item =
              Booking(_tmpUserId,_tmpComputerId,_tmpRoomId,_tmpDayOfWeek,_tmpTimeSlot,_tmpBookingDate,_tmpId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBookingsByDayAndTime(dayOfWeek: String, timeSlot: String):
      List<Booking> {
    val _sql: String = "SELECT * FROM booking WHERE dayOfWeek = ? AND timeSlot = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, dayOfWeek)
        _argIndex = 2
        _stmt.bindText(_argIndex, timeSlot)
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _cursorIndexOfComputerId: Int = getColumnIndexOrThrow(_stmt, "computerId")
        val _cursorIndexOfRoomId: Int = getColumnIndexOrThrow(_stmt, "roomId")
        val _cursorIndexOfDayOfWeek: Int = getColumnIndexOrThrow(_stmt, "dayOfWeek")
        val _cursorIndexOfTimeSlot: Int = getColumnIndexOrThrow(_stmt, "timeSlot")
        val _cursorIndexOfBookingDate: Int = getColumnIndexOrThrow(_stmt, "bookingDate")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: MutableList<Booking> = mutableListOf()
        while (_stmt.step()) {
          val _item: Booking
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          val _tmpComputerId: Int
          _tmpComputerId = _stmt.getLong(_cursorIndexOfComputerId).toInt()
          val _tmpRoomId: Int
          _tmpRoomId = _stmt.getLong(_cursorIndexOfRoomId).toInt()
          val _tmpDayOfWeek: String
          _tmpDayOfWeek = _stmt.getText(_cursorIndexOfDayOfWeek)
          val _tmpTimeSlot: String
          _tmpTimeSlot = _stmt.getText(_cursorIndexOfTimeSlot)
          val _tmpBookingDate: String
          _tmpBookingDate = _stmt.getText(_cursorIndexOfBookingDate)
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _item =
              Booking(_tmpUserId,_tmpComputerId,_tmpRoomId,_tmpDayOfWeek,_tmpTimeSlot,_tmpBookingDate,_tmpId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBookingByComputerDayTime(
    computerId: Int,
    dayOfWeek: String,
    timeSlot: String,
  ): Booking? {
    val _sql: String =
        "SELECT * FROM booking WHERE computerId = ? AND dayOfWeek = ? AND timeSlot = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, computerId.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, dayOfWeek)
        _argIndex = 3
        _stmt.bindText(_argIndex, timeSlot)
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _cursorIndexOfComputerId: Int = getColumnIndexOrThrow(_stmt, "computerId")
        val _cursorIndexOfRoomId: Int = getColumnIndexOrThrow(_stmt, "roomId")
        val _cursorIndexOfDayOfWeek: Int = getColumnIndexOrThrow(_stmt, "dayOfWeek")
        val _cursorIndexOfTimeSlot: Int = getColumnIndexOrThrow(_stmt, "timeSlot")
        val _cursorIndexOfBookingDate: Int = getColumnIndexOrThrow(_stmt, "bookingDate")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: Booking?
        if (_stmt.step()) {
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          val _tmpComputerId: Int
          _tmpComputerId = _stmt.getLong(_cursorIndexOfComputerId).toInt()
          val _tmpRoomId: Int
          _tmpRoomId = _stmt.getLong(_cursorIndexOfRoomId).toInt()
          val _tmpDayOfWeek: String
          _tmpDayOfWeek = _stmt.getText(_cursorIndexOfDayOfWeek)
          val _tmpTimeSlot: String
          _tmpTimeSlot = _stmt.getText(_cursorIndexOfTimeSlot)
          val _tmpBookingDate: String
          _tmpBookingDate = _stmt.getText(_cursorIndexOfBookingDate)
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _result =
              Booking(_tmpUserId,_tmpComputerId,_tmpRoomId,_tmpDayOfWeek,_tmpTimeSlot,_tmpBookingDate,_tmpId)
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
