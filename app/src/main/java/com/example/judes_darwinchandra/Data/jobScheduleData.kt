package com.example.judes_darwinchandra.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity menandakan bahwa nama class merupakan nama table yang akan dibentuk
//di dalam database
@Entity/*(primaryKeys= arrayOf("id_user","id_lowongan"))*/
// membuat class menjadi data class
data class jobScheduleData(
    //primary key menandakan bawa kolom tersebut merupakan primary key
    @PrimaryKey val _id : Int,
    //column info untuk menentukan nama colom di dalam table database
    @ColumnInfo(name = "COLUMN_NAMA_PERUSAHAAN") var nama : String = "",
    @ColumnInfo(name = "COLUMN_JABATAN") var jabatan : String = "",
    @ColumnInfo(name = "COLUMN_LOKASI") var lokasi : String = "",
    @ColumnInfo(name = "COLUMN_GAJI") var gaji : String = "",
    @ColumnInfo(name = "COLUMN_JADWAL") var jadwal : String = "",
    @ColumnInfo(name = "COLUMN_URL") var imgurl: String = ""
)