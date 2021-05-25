package com.example.judes_darwinchandra

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity menandakan bahwa nama class merupakan nama table yang akan dibentuk
//di dalam database
@Entity
data class User(
    //primary key menandakan bawa kolom tersebut merupakan primary key
    @PrimaryKey val _id : Int,
    //column info untuk menentukan nama colom di dalam table database
    @ColumnInfo(name = "COLUMN_NAME") var nama : String = "",
    @ColumnInfo(name = "COLUMN_EMAIL") var email : String = "",
    @ColumnInfo(name = "COLUMN_PASSWORD") var password : String = ""
)