package com.example.judes_darwinchandra.Interface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.judes_darwinchandra.Data.User
import com.example.judes_darwinchandra.Data.jobScheduleData

@Dao
interface jobScheduleDAO {
    @Query("Select * From jobScheduleData")
    fun getAllData() : List<jobScheduleData>

    //memasukkan data kedalam tabel user
    @Insert
    fun insertAll(vararg perusahaan: jobScheduleData)
}