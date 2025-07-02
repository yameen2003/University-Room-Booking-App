package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getBookingDatabase(): BookingDatabase {
    val dbFile = NSHomeDirectory() + "/booking.db"
    return Room.databaseBuilder<BookingDatabase>(
        name = dbFile,
        factory = { BookingDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}