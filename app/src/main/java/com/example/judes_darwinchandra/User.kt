package com.example.judes_darwinchandra

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//add @Entity
@Entity
// membuat class menjadi data class
data class User(
    //PRIMARY KEY BERUPA INT
    @PrimaryKey val _id : Int,
    //3 KOLOM UNTUK DATA USER DENGAN TIPE Data string
    @ColumnInfo(name = "COLUMN_NAME") var nama : String = "",
    @ColumnInfo(name = "COLUMN_EMAIL") var email : String = "",
    @ColumnInfo(name = "COLUMN_PASSWORD") var password : String = ""
)