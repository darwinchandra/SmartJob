package com.example.app2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    // membuat var yang menampung dbroom
    var SQLitedb : myDBRoomHelper? = null
    // membuat var yang menampung share pref
    var FirstRunSharePref : FirstRunSharePref? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // membuat sqlitedb yg menampung dbroomhelper
        SQLitedb = myDBRoomHelper(this)
        // membuat share pref yg menampung firstrunsharepref
        FirstRunSharePref = FirstRunSharePref(this)
        // semua data dihapus
        SQLitedb?.deleteAllUser()
        // share pref dibuat true
        FirstRunSharePref?.firstRun = true
        // jika share pref false maka jalankan secondintent
        if(FirstRunSharePref!!.firstRun){
            val secondIntent = Intent(this, pre_load::class.java)
            startActivity(secondIntent)
        }
        // menggunakan fungsi adapter
        updateAdapter()
        // ketika lv location di klik maka lakukan doasync agar dapat melakukan autocompletetext
        lv_Location.setOnItemClickListener { parent, view, position, id ->
            doAsync {
                // locallist mengambil data dari sqlite dan dituliskan
                var locaList = SQLitedb?.viewAllName()?.toTypedArray()
                // editbox akan sesuai ketika di klik isi dari listviewnya
                edit_text_location.setText(locaList!![position])
            }
        }
        // ketika button add diklik
        btn_tambahkota.setOnClickListener {
            //membuat var yang menampung user data
            var userTmp = Location()
            // user data location diammbil dari edit text
            userTmp.location = edit_text_location.text.toString()

            // hasil ditambah pada database sqlite
            var hasil = SQLitedb?.addLocation(userTmp)
            //jika hasil tidak -1l maka berhasil
            if(hasil!=-1L){
                Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
            }
            // lainnya gagal
            else {
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
            // menggunakan fungsi adapter
            updateAdapter()
            // edit box dibersihkan
            edit_text_location.text.clear()

        }
        // ketika button delete di klik
        btn_delete.setOnClickListener {
            // memngambil data dari edit text
            var location = edit_text_location.text.toString()
            // jika edit tidak kosong
            if(location!=null || location != ""){
                // maka lakukan do async
                doAsync {
                    // data di sqlite di hapus sesuai dengan isi dari location
                    SQLitedb?.deleteLocation(location)
                    // menggunakan fungsinya
                    updateAdapter()
                }
            }
        }
    }
    // membuat fungsi
    fun updateAdapter(){
        // melakukan doasync
        doAsync {
            // locallist menampung semua isi data dari sqlite dan ditulis ke arraylist
            var locaList = SQLitedb?.viewAllName()?.toTypedArray()
            uiThread {
                // jika listview tidak kosong / tidak sama dengan 0 maka listview ditambah dari localist
                if(lv_Location != null && locaList?.size != 0){
                    lv_Location.adapter = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_list_item_1,
                        locaList!!
                    )
                }
            }
        }
    }

    // membuat fungsi
    override fun onResume() {
        //saat Activity dibuka kembali, biasanya dieksekusi setelah onPause()
        super.onResume()
        updateAdapter()
    }

}
