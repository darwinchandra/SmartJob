package com.example.judes_darwinchandra

import androidx.room.Database
import androidx.room.RoomDatabase

//Menuliskan semua entitas/table yang akan dibentuk pada database
@Database(entities = arrayOf(User::class), version = 1)
abstract class MyDBRoomHelper : RoomDatabase() {
    //membuat function untuk class dbhelper dengan tipe data class yang telah dibuat
    abstract fun userDao() : UserDAO
    fun addUserTransaction(user :User) {
        runInTransaction {
            userDao().PreloadData(user._id,user.email,user.password)
        }
    }
}