package com.example.judes_darwinchandra

import android.net.Uri
import android.provider.BaseColumns

// membuat objek dari location yang dimana terbuat tabel dan terdiri dari tabel user id dan location
object locationDB {
        class userTable : BaseColumns {
            companion object{
                val TABLE_USER = "tbl_User"
                val COLUMN_ID = "User_Id"
                val COLUMN_LOCATION = "LOCATION"

            }
        }
    }
// membuat class content provider untuk mendapatkan akses dari app 2
    class myContentProviderURI{
        companion object{
            val AUTHORITY = "com.example.app2.provider.provider.ContentProvider"
            private val USER_TABLE = locationDB.userTable.TABLE_USER
            val CONTENT_URI : Uri = Uri.parse("content://$AUTHORITY/$USER_TABLE")
        }
    }
