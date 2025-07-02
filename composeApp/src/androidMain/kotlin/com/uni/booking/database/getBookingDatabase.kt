package com.uni.booking.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.BookingDatabase

fun getBookingDatabase(context: Context): BookingDatabase {
    val dbFile = context.getDatabasePath("booking.db")
    return Room.databaseBuilder<BookingDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}