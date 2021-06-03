package com.example.judes_darwinchandra

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import com.example.judes_darwinchandra.locationDB.userTable.Companion.COLUMN_ID
import com.example.judes_darwinchandra.locationDB.userTable.Companion.COLUMN_LOCATION


class locationTransaction(context: Context) {
    // membuat val yang menampung content yang di ambil dari database app2
    private val myContentResolver = context.contentResolver

    // membuat fungsi
    fun viewAllLocation() : List<String>{
        // membuat var menampung array list
        var myNameList = ArrayList<String>()
        // membuat var yang menampung column id dan column location database
        var mProjection = arrayOf(COLUMN_ID, COLUMN_LOCATION)
        // membuat cursor dimana contentresolver memanggil data dari app2
        var cursor = myContentResolver.query(myContentProviderURI.CONTENT_URI,mProjection,null,null,null)
        // jika cursor / data tidak kosong maka jalan program dibawah
        if(cursor!=null){
            // membuat var username untuk menampung
            var userName: String = ""
            // jika cursor berpindah pada klik pertama
            if (cursor.moveToFirst()) {
                // maka lakukan
                do {
                    // user mengambil data dari cursor menggunakan columnindex yang berada di column location
                    userName = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION))
                    // username ditambah pada mynamelist
                    myNameList.add(userName)
                    //sampai klik selanjutnya
                } while (cursor.moveToNext())
            }
        }
        // hasil
        return myNameList
    }

    fun insertDataLocation(location : Location){
        var contentValue= ContentValues()

        var uri = myContentResolver.insert(myContentProviderURI.CONTENT_URI,contentValue)
    }
}