package com.example.app2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.app2.MyDB.userDB

class myDBRoomHelper (context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION)
{
    //membuat companion yang menampung database name dan versi
    companion object{
        private val DATABASE_NAME = "userdb.db"
        private val DATABASE_VERSION = 1
    }
    // fungsi oncreate utk membuat tabel pada database
    override fun onCreate(db: SQLiteDatabase?) {
        // create table data yang diisi dengan column id dan location
        var CREATE_USER_TABLE = "CREATE TABLE ${userDB.userTable.TABLE_USER} " +
                "(${userDB.userTable.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${userDB.userTable.COLUMN_LOCATION} TEXT)"
        // tabel di buat pada database
        db?.execSQL(CREATE_USER_TABLE)
    }

    // fungsi onupgrade
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        // membuang database jika juga ada
        db?.execSQL("DROP TABLE IF EXISTS ${userDB.userTable.TABLE_USER}")
        // dan membuatnya lagi
        onCreate(db)
    }

    // fungsi menambahkan Location
    fun addLocation(location: Location) : Long{
        // databaase di tulis
        var db = this.writableDatabase
        // contentvales di apply dan di berik userdb utk column location
        var contentValues = ContentValues().apply {
            put(userDB.userTable.COLUMN_LOCATION, location.location)

        }
        // success untuk menginput data ke table menggunakan data dari contentvalues
        var success = db.insert(userDB.userTable.TABLE_USER, null, contentValues)
        // database ditutup
        db.close()
        // hasil
        return success
    }
    // membuat fungsi nama
    fun viewAllName() : List<String>{
        // membuat locallist yang menampung arraylist
        val locaList = ArrayList<String>()
        // // membuat selectname utk mengambil data column location dari tabel
        val SELECT_NAME = "SELECT ${userDB.userTable.COLUMN_LOCATION} FROM " +
                "${userDB.userTable.TABLE_USER}"
        // membuat database ditulis
        var db = this.writableDatabase
        // cursor adalah kosong
        var cursor : Cursor?=null

        try {
            // cursor di isi dengan data dari select name
            cursor = db.rawQuery(SELECT_NAME, null)
        }catch (e : SQLException){
            // hasil
            return ArrayList()
        }
        // membuat var location yang menampung
        var location = ""
        // jika cursor berpindah pada klik pertama
        if(cursor.moveToFirst()){
            // maka lakukan
            do{
                // user mengambil data dari cursor menggunakan columnindex yang berada di column location
                location = cursor.getString(cursor.getColumnIndex(userDB.userTable.COLUMN_LOCATION))
                // username ditambah pada mynamelist
                locaList.add(location)
                //sampai klik selanjutnya
            }while (cursor.moveToNext())
        }
        // hasil
        return locaList
    }

    // fungsi hapus
    fun deleteLocation(location : String){
        // membuat data ditulis / mengubah
        var db = this.writableDatabase
        // memilih dari database column location
        var selection ="${userDB.userTable.COLUMN_LOCATION} = ?"
        // membuat array dari location
        var selectionArgs = arrayOf(location)
        // data didatabase didelete pada saat dipilih
        db.delete(userDB.userTable.TABLE_USER, selection, selectionArgs)
    }
    // fungsi hapus total
    fun deleteAllUser(){
        // mengubah data atau menulis
        var db = this.writableDatabase
        // data dihapus semua dari tabel user
        db.delete(userDB.userTable.TABLE_USER, "",null)
    }

    // fugsi begin
    fun beginUserTransaction(){
        // membuat data ditulis dimulai
        this.writableDatabase.beginTransaction()
    }
    // fugsi success
    fun successUserTransaction(){
        // membuat data diubah sampai selesai
        this.writableDatabase.setTransactionSuccessful()
    }
    // fugsi end
    fun endUserTransaction(){
        // membuat data ditulis diakhiri
        this.writableDatabase.endTransaction()
    }
    // fungsi menambah
    fun addUserTransaction(location : Location){
        // sqlstring untuk memasukkan data ke tabel user
        val sqlString = "INSERT INTO ${userDB.userTable.TABLE_USER} " +
                "(${userDB.userTable.COLUMN_ID}" +
                ",${userDB.userTable.COLUMN_LOCATION}) VALUES (?,?)"
        // database di compile dari sqlstring
        val myStatement = this.writableDatabase.compileStatement(sqlString)
        // id varnya long
        myStatement.bindLong(1,location.id.toLong())
        // id varnya string
        myStatement.bindString(2,location.location)
        // statement dijalankan
        myStatement.execute()
        // statement dibersihkan
        myStatement.clearBindings()

    }

}