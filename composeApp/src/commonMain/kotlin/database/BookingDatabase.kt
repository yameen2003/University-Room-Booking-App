package database

import androidx.room.Database
import androidx.room.RoomDatabase
import database.dao.BookingDao
import database.dao.ComputerDao
import database.dao.RoomDao
import database.dao.UserDao
import database.models.Booking
import database.models.Computer
import database.models.Room
import database.models.User

@Database(
    entities = [User::class, Room::class, Computer::class, Booking::class],
    version = 1
)
abstract class BookingDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun roomDao(): RoomDao
    abstract fun computerDao(): ComputerDao
    abstract fun bookingDao(): BookingDao

}