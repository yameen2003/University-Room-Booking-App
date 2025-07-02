package database

import kotlin.reflect.KClass

internal fun KClass<BookingDatabase>.instantiateImpl(): BookingDatabase =
    database.BookingDatabase_Impl()
