package com.example.app2.provider


import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.example.app2.MyDB.userDB
import com.example.app2.myDBRoomHelper


class ContentProvider : ContentProvider() {
    // menbuat dbroom yang menanmpung data dari mydbroomhelper tidak boleh kosong
    private var dbRoomHelper: myDBRoomHelper? = null
    //membuat fungsi oncreate yang bersifat boolean
    override fun onCreate(): Boolean {
        // dbroomhelper  false
        dbRoomHelper = myDBRoomHelper(context!!)
        // maka return true
        return true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        // mmebuat sqlite builder utk membuat sebuah database
        var queryBuilder = SQLiteQueryBuilder()
        // membuat sebuah table
        queryBuilder.tables = userDB.userTable.TABLE_USER
        // cursor mengambil query data dari dbroomhelper
        var cursor = queryBuilder.query(
            dbRoomHelper?.readableDatabase,
            p1, p2, p3, null, null, p4
        )
        // cursor beri notif pada context
        cursor.setNotificationUri(context?.contentResolver, p0)
        // return hasil
        return cursor
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
    // membuat companion untuk mendapatkan akses dari app 2
    companion object {
        val AUTHORITY = "com.example.app2.provider.provider.ContentProvider"
        private val USER_TABLE = userDB.userTable.TABLE_USER
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$USER_TABLE")
    }
}