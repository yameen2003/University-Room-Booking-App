package database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
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
public class PeopleDao_Impl(
  __db: RoomDatabase,
) : PeopleDao {
  private val __db: RoomDatabase

  private val __deleteAdapterOfPerson: EntityDeleteOrUpdateAdapter<Person>

  private val __upsertAdapterOfPerson: EntityUpsertAdapter<Person>
  init {
    this.__db = __db
    this.__deleteAdapterOfPerson = object : EntityDeleteOrUpdateAdapter<Person>() {
      protected override fun createQuery(): String = "DELETE FROM `Person` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Person) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__upsertAdapterOfPerson = EntityUpsertAdapter<Person>(object :
        EntityInsertAdapter<Person>() {
      protected override fun createQuery(): String =
          "INSERT INTO `Person` (`name`,`id`) VALUES (?,nullif(?, 0))"

      protected override fun bind(statement: SQLiteStatement, entity: Person) {
        statement.bindText(1, entity.name)
        statement.bindLong(2, entity.id.toLong())
      }
    }, object : EntityDeleteOrUpdateAdapter<Person>() {
      protected override fun createQuery(): String =
          "UPDATE `Person` SET `name` = ?,`id` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Person) {
        statement.bindText(1, entity.name)
        statement.bindLong(2, entity.id.toLong())
        statement.bindLong(3, entity.id.toLong())
      }
    })
  }

  public override suspend fun delete(person: Person): Unit = performSuspending(__db, false, true) {
      _connection ->
    __deleteAdapterOfPerson.handle(_connection, person)
  }

  public override suspend fun upsert(person: Person): Unit = performSuspending(__db, false, true) {
      _connection ->
    __upsertAdapterOfPerson.upsert(_connection, person)
  }

  public override fun getAllPeople(): Flow<List<Person>> {
    val _sql: String = "SELECT * FROM person"
    return createFlow(__db, false, arrayOf("person")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _result: MutableList<Person> = mutableListOf()
        while (_stmt.step()) {
          val _item: Person
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          _item = Person(_tmpName,_tmpId)
          _result.add(_item)
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
