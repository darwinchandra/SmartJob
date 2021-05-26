package com.example.judes_darwinchandra

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    // query untuk menampilkan semua database dan disimpan dalam bentuk list
    @Query("Select * From User")
    fun getAllData() : List<User>

    // perintah untuk insert
    @Insert
    fun insertAll(vararg user: User)


    //QUERY UNTUK UPDATE PASSWORD
    @Query("UPDATE User SET COLUMN_PASSWORD = :newpass WHERE COLUMN_EMAIL= :email")
    fun updatepass(email:String,newpass:String)


    //QUERY UNTUK TAMPILKAN DATA DENGAN EMAIL DAN PASSWORD YANG COCOK dan di buat dalam bentuk list
    @Query("SELECT * FROM User WHERE COLUMN_EMAIL= :email AND COLUMN_PASSWORD= :pass")
    fun validateEmailPass(email:String,pass:String) : List<User>
}
