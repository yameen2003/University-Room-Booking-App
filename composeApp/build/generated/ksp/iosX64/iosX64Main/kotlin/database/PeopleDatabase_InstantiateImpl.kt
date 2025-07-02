package database

import kotlin.reflect.KClass

internal fun KClass<PeopleDatabase>.instantiateImpl(): PeopleDatabase =
    database.PeopleDatabase_Impl()
