package com.example.judes_darwinchandra

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Query("Select * From User")
    fun getAllData() : List<User>

    @Insert
    fun insertAll(vararg user: User)

    @Query("UPDATE User SET COLUMN_PASSWORD = :newpass WHERE COLUMN_EMAIL= :email")
    fun updatepass(email:String,newpass:String)

    @Query("SELECT * FROM User WHERE COLUMN_EMAIL= :email AND COLUMN_PASSWORD= :pass")
    fun validateEmailPass(email:String,pass:String) : List<User>

    @Query("SELECT * FROM User WHERE COLUMN_EMAIL= :email ")
    fun validateEmailRegis(email:String) : List<User>
}