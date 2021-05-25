package com.example.judes_darwinchandra

import androidx.room.Database
import androidx.room.RoomDatabase

//Menuliskan semua entitas/table yang akan dibentuk pada database
@Database(entities = arrayOf(User::class), version = 1)
abstract class MyDBRoomHelper : RoomDatabase() {
    abstract fun userDao() : UserDAO
}