package com.example.app2.MyDB

import android.provider.BaseColumns

object userDB{
    class userTable : BaseColumns {
        companion object{
            val TABLE_USER = "tbl_User"
            val COLUMN_ID = "User_Id"
            val COLUMN_LOCATION = "LOCATION"

        }
    }
}