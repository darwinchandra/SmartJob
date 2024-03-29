package com.example.judes_darwinchandra.Interface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.judes_darwinchandra.Data.User

//DAO merupakan komponen utama Room yang menyertakan metode yang menawarkan akses
//abstrak ke database aplikasi
@Dao
interface UserDAO {
    //membca semua data pada tabel User
    @Query("Select * From User")
    fun getAllData() : List<User>

    //memasukkan data kedalam tabel user
    @Insert
    fun insertAll(vararg user: User)

    @Query("SELECT COUNT(*) FROM  User")
    fun count(): Int

    // Query untuk insert data pada table user
    @Query("INSERT INTO User VALUES (:id,:nama,:email,:pass)")
    fun addNewDataUser(id:Int,nama: String,email: String,pass: String)


    //QUERY UNTUK UPDATE PASSWORD
    @Query("UPDATE User SET COLUMN_PASSWORD = :newpass WHERE COLUMN_EMAIL= :email")
    fun updatepass(email:String,newpass:String)


    //QUERY UNTUK TAMPILKAN DATA DENGAN EMAIL DAN PASSWORD YANG COCOK dan di buat dalam bentuk list
    @Query("SELECT * FROM User WHERE COLUMN_EMAIL= :email AND COLUMN_PASSWORD= :pass")
    fun validateEmailPass(email:String,pass:String) : List<User>

    //melakukan validasi email dengan yang ada pada di database
    @Query("SELECT * FROM User WHERE COLUMN_EMAIL= :email ")
    fun validateEmailRegis(email:String) : List<User>
}








