package com.example.judes_darwinchandra

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("Select * From User")
    fun getAllData() : List<User>

    @Insert
    fun insertAll(vararg user: User)

}