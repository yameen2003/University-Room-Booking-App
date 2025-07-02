package database.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import database.models.User
import kotlin.Boolean
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
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __deleteAdapterOfUser: EntityDeleteOrUpdateAdapter<User>

  private val __upsertAdapterOfUser: EntityUpsertAdapter<User>
  init {
    this.__db = __db
    this.__deleteAdapterOfUser = object : EntityDeleteOrUpdateAdapter<User>() {
      protected override fun createQuery(): String = "DELETE FROM `User` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__upsertAdapterOfUser = EntityUpsertAdapter<User>(object : EntityInsertAdapter<User>() {
      protected override fun createQuery(): String =
          "INSERT INTO `User` (`name`,`email`,`password`,`contactDetails`,`isAdmin`,`id`) VALUES (?,?,?,?,?,nullif(?, 0))"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindText(1, entity.name)
        statement.bindText(2, entity.email)
        statement.bindText(3, entity.password)
        statement.bindText(4, entity.contactDetails)
        val _tmp: Int = if (entity.isAdmin) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        statement.bindLong(6, entity.id.toLong())
      }
    }, object : EntityDeleteOrUpdateAdapter<User>() {
      protected override fun createQuery(): String =
          "UPDATE `User` SET `name` = ?,`email` = ?,`password` = ?,`contactDetails` = ?,`isAdmin` = ?,`id` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindText(1, entity.name)
        statement.bindText(2, entity.email)
        statement.bindText(3, entity.password)
        statement.bindText(4, entity.contactDetails)
        val _tmp: Int = if (entity.isAdmin) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        statement.bindLong(6, entity.id.toLong())
        statement.bindLong(7, entity.id.toLong())
      }
    })
  }

  public override suspend fun delete(user: User): Unit = performSuspending(__db, false, true) {
      _connection ->
    __deleteAdapterOfUser.handle(_connection, user)
  }

  public override suspend fun upsert(user: User): Unit = performSuspending(__db, false, true) {
      _connection ->
    __upsertAdapterOfUser.upsert(_connection, user)
  }

  public override suspend fun login(email: String, password: String): User? {
    val _sql: String = "SELECT * FROM user WHERE email = ? AND password = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        _argIndex = 2
        _stmt.bindText(_argIndex, password)
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _cursorIndexOfContactDetails: Int = getColumnIndexOrThrow(_stmt, "contactDetails")
        val _cursorIndexOfIsAdmin: Int = getColumnIndexOrThrow(_stmt, "isAdmin")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: User?
        if (_stmt.step()) {
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_cursorIndexOfPassword)
          val _tmpContactDetails: String
          _tmpContactDetails = _stmt.getText(_cursorIndexOfContactDetails)
          val _tmpIsAdmin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsAdmin).toInt()
          _tmpIsAdmin = _tmp != 0
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _result = User(_tmpName,_tmpEmail,_tmpPassword,_tmpContactDetails,_tmpIsAdmin,_tmpId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserByEmail(email: String): User? {
    val _sql: String = "SELECT * FROM user WHERE email = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _cursorIndexOfContactDetails: Int = getColumnIndexOrThrow(_stmt, "contactDetails")
        val _cursorIndexOfIsAdmin: Int = getColumnIndexOrThrow(_stmt, "isAdmin")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: User?
        if (_stmt.step()) {
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_cursorIndexOfPassword)
          val _tmpContactDetails: String
          _tmpContactDetails = _stmt.getText(_cursorIndexOfContactDetails)
          val _tmpIsAdmin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsAdmin).toInt()
          _tmpIsAdmin = _tmp != 0
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _result = User(_tmpName,_tmpEmail,_tmpPassword,_tmpContactDetails,_tmpIsAdmin,_tmpId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllUsers(): Flow<List<User>> {
    val _sql: String = "SELECT * FROM user"
    return createFlow(__db, false, arrayOf("user")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _cursorIndexOfContactDetails: Int = getColumnIndexOrThrow(_stmt, "contactDetails")
        val _cursorIndexOfIsAdmin: Int = getColumnIndexOrThrow(_stmt, "isAdmin")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: MutableList<User> = mutableListOf()
        while (_stmt.step()) {
          val _item: User
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_cursorIndexOfPassword)
          val _tmpContactDetails: String
          _tmpContactDetails = _stmt.getText(_cursorIndexOfContactDetails)
          val _tmpIsAdmin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsAdmin).toInt()
          _tmpIsAdmin = _tmp != 0
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _item = User(_tmpName,_tmpEmail,_tmpPassword,_tmpContactDetails,_tmpIsAdmin,_tmpId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserById(userId: Int): User? {
    val _sql: String = "SELECT * FROM user WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _cursorIndexOfContactDetails: Int = getColumnIndexOrThrow(_stmt, "contactDetails")
        val _cursorIndexOfIsAdmin: Int = getColumnIndexOrThrow(_stmt, "isAdmin")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: User?
        if (_stmt.step()) {
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_cursorIndexOfPassword)
          val _tmpContactDetails: String
          _tmpContactDetails = _stmt.getText(_cursorIndexOfContactDetails)
          val _tmpIsAdmin: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsAdmin).toInt()
          _tmpIsAdmin = _tmp != 0
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _result = User(_tmpName,_tmpEmail,_tmpPassword,_tmpContactDetails,_tmpIsAdmin,_tmpId)
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
