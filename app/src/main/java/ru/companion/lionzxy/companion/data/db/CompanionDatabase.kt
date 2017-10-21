package ru.companion.lionzxy.companion.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

//TODO @Database(entities = arrayOf(UserDao::class), version = 1)
abstract class CompanionDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}