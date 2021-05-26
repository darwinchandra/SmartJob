package com.example.judes_darwinchandra

import androidx.room.Database
import androidx.room.RoomDatabase
//pembuatan database
@Database(entities = arrayOf(User::class), version = 1)
abstract class MyDBRoomHelper : RoomDatabase() {
    //membuat function untuk class dbhelper dengan tipe data class yang telah dibuat
    abstract fun userDao() : UserDAO
}