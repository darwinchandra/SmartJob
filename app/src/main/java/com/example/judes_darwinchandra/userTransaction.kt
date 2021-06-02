package com.example.judes_darwinchandra

import android.content.Context
import com.example.judes_darwinchandra.locationDB.userTable.Companion.COLUMN_ID
import com.example.judes_darwinchandra.locationDB.userTable.Companion.COLUMN_LOCATION


class userTransaction(context: Context) {
    private val myContentResolver = context.contentResolver

    fun viewAllName() : List<String>{
        var myNameList = ArrayList<String>()
        var mProjection = arrayOf(COLUMN_ID, COLUMN_LOCATION)
        var cursor = myContentResolver.query(myContentProviderURI.CONTENT_URI,mProjection,null,null,null)
        if(cursor!=null){
            var userName: String = ""
            if (cursor.moveToFirst()) {
                do {
                    userName = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION))
                    myNameList.add(userName)
                } while (cursor.moveToNext())
            }
        }
        return myNameList
    }
}