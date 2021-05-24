package com.example.judes_darwinchandra

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
abstract class MyDBRoomHelper : RoomDatabase() {
    abstract fun userDao() : UserDAO
}