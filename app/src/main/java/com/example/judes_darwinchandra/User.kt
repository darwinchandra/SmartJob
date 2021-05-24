package com.example.judes_darwinchandra

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val _id : Int,
    @ColumnInfo(name = "COLUMN_NAME") var nama : String = "",
    @ColumnInfo(name = "COLUMN_Email") var email : String = ""
)