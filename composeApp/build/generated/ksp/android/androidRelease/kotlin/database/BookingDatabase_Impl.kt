package database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import database.dao.BookingDao
import database.dao.BookingDao_Impl
import database.dao.ComputerDao
import database.dao.ComputerDao_Impl
import database.dao.RoomDao
import database.dao.RoomDao_Impl
import database.dao.UserDao
import database.dao.UserDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Any
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class BookingDatabase_Impl : BookingDatabase() {
  private val _userDao: Lazy<UserDao> = lazy {
    UserDao_Impl(this)
  }


  private val _roomDao: Lazy<RoomDao> = lazy {
    RoomDao_Impl(this)
  }


  private val _computerDao: Lazy<ComputerDao> = lazy {
    ComputerDao_Impl(this)
  }


  private val _bookingDao: Lazy<BookingDao> = lazy {
    BookingDao_Impl(this)
  }


  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "c1f7d416c816dd429903015489f8ce7e", "8ba41e6e5cb7d5277d81b4f4f5dfc95f") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `User` (`name` TEXT NOT NULL, `email` TEXT NOT NULL, `password` TEXT NOT NULL, `contactDetails` TEXT NOT NULL, `isAdmin` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `rooms` (`roomNumber` TEXT NOT NULL, `computerCount` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Computer` (`roomId` INTEGER NOT NULL, `computerNumber` INTEGER NOT NULL, `globalId` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Booking` (`userId` INTEGER NOT NULL, `computerId` INTEGER NOT NULL, `roomId` INTEGER NOT NULL, `dayOfWeek` TEXT NOT NULL, `timeSlot` TEXT NOT NULL, `bookingDate` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c1f7d416c816dd429903015489f8ce7e')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `User`")
        connection.execSQL("DROP TABLE IF EXISTS `rooms`")
        connection.execSQL("DROP TABLE IF EXISTS `Computer`")
        connection.execSQL("DROP TABLE IF EXISTS `Booking`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsUser: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUser.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUser.put("email", TableInfo.Column("email", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUser.put("password", TableInfo.Column("password", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUser.put("contactDetails", TableInfo.Column("contactDetails", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUser.put("isAdmin", TableInfo.Column("isAdmin", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUser.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUser: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUser: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUser: TableInfo = TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser)
        val _existingUser: TableInfo = read(connection, "User")
        if (!_infoUser.equals(_existingUser)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |User(database.models.User).
              | Expected:
              |""".trimMargin() + _infoUser + """
              |
              | Found:
              |""".trimMargin() + _existingUser)
        }
        val _columnsRooms: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRooms.put("roomNumber", TableInfo.Column("roomNumber", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRooms.put("computerCount", TableInfo.Column("computerCount", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRooms.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRooms: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRooms: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRooms: TableInfo = TableInfo("rooms", _columnsRooms, _foreignKeysRooms,
            _indicesRooms)
        val _existingRooms: TableInfo = read(connection, "rooms")
        if (!_infoRooms.equals(_existingRooms)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |rooms(database.models.Room).
              | Expected:
              |""".trimMargin() + _infoRooms + """
              |
              | Found:
              |""".trimMargin() + _existingRooms)
        }
        val _columnsComputer: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsComputer.put("roomId", TableInfo.Column("roomId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsComputer.put("computerNumber", TableInfo.Column("computerNumber", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsComputer.put("globalId", TableInfo.Column("globalId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsComputer.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysComputer: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesComputer: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoComputer: TableInfo = TableInfo("Computer", _columnsComputer, _foreignKeysComputer,
            _indicesComputer)
        val _existingComputer: TableInfo = read(connection, "Computer")
        if (!_infoComputer.equals(_existingComputer)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Computer(database.models.Computer).
              | Expected:
              |""".trimMargin() + _infoComputer + """
              |
              | Found:
              |""".trimMargin() + _existingComputer)
        }
        val _columnsBooking: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBooking.put("userId", TableInfo.Column("userId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooking.put("computerId", TableInfo.Column("computerId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooking.put("roomId", TableInfo.Column("roomId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooking.put("dayOfWeek", TableInfo.Column("dayOfWeek", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooking.put("timeSlot", TableInfo.Column("timeSlot", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooking.put("bookingDate", TableInfo.Column("bookingDate", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooking.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBooking: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBooking: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBooking: TableInfo = TableInfo("Booking", _columnsBooking, _foreignKeysBooking,
            _indicesBooking)
        val _existingBooking: TableInfo = read(connection, "Booking")
        if (!_infoBooking.equals(_existingBooking)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Booking(database.models.Booking).
              | Expected:
              |""".trimMargin() + _infoBooking + """
              |
              | Found:
              |""".trimMargin() + _existingBooking)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "User", "rooms", "Computer",
        "Booking")
  }

  public override fun clearAllTables() {
    super.performClear(false, "User", "rooms", "Computer", "Booking")
  }

  protected override fun getRequiredTypeConverterClasses():
      Map<KClass<out Any>, List<KClass<out Any>>> {
    val _typeConvertersMap: MutableMap<KClass<out Any>, List<KClass<out Any>>> = mutableMapOf()
    _typeConvertersMap.put(UserDao::class, UserDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RoomDao::class, RoomDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ComputerDao::class, ComputerDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(BookingDao::class, BookingDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun userDao(): UserDao = _userDao.value

  public override fun roomDao(): RoomDao = _roomDao.value

  public override fun computerDao(): ComputerDao = _computerDao.value

  public override fun bookingDao(): BookingDao = _bookingDao.value
}
