package com.example.judes_darwinchandra

import androidx.room.Database
import androidx.room.RoomDatabase

//Menuliskan semua entitas/table yang akan dibentuk pada database
@Database(entities = arrayOf(User::class), version = 1)
abstract class MyDBRoomHelper : RoomDatabase() {
    //membuat function untuk class dbhelper dengan tipe data class yang telah dibuat
    abstract fun userDao() : UserDAO

    //membuat query jalan pada transaction untuk database room agaar lebih optimal
    //transaction untuk add user
    fun addUserTransaction(user :User) {
        runInTransaction {
            userDao().addNewDataUser(user._id,user.nama,user.email,user.password)
        }
    }
    //transaction untuk updatepassword user
    fun updatePasswordTransaction(email:String,newpass:String) {
        runInTransaction {
            userDao().updatepass(email,newpass)
        }
    }
    //transaction select data pada table user untuk mengetahui validitas dari email dan password sewaktu login
    fun selectValidTransaction(email: String,pass:String):List<User> {
        // membuat variabel penampung list dari user yang di get
        var data=listOf<User>()
        runInTransaction {
            data = userDao().validateEmailPass(email,pass)
        }
        // return variabel data, yang nantinya akan di cek sizenya apabila >0 maka valid
        return data
    }

}