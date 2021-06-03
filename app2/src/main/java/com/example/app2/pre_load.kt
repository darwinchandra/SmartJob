package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import kotlinx.android.synthetic.main.activity_pre_load.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class pre_load : AppCompatActivity() {
    // membuat sqlite data dari mydbroomhelper kosong
    var SQLitedb: myDBRoomHelper? = null
    //membuat list
    private var location = listOf(
        Location(1, "ACEH"),
        Location(2, "BALI"),
        Location(3, "BANGKA BELITUNG"),
        Location(4, "BANTEN"),
        Location(5, "BENGKULU"),
        Location(6, "GORONTALO"),
        Location(7, "JAKARTA RAYA"),
        Location(8, "JAMBI"),
        Location(9, "JAWA BARAT"),
        Location(10, "JAWA TENGAH")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_load)
        // execute data yang mau diload
        executeLoadDataTransaction()
    }

    // fungsi finish
    private fun finishThisActivity() {
        // share pref diambil dari firstrunsharepref.kt
        var myFirstRunSharePref = FirstRunSharePref(this)
        // jika sudah run maka false
        myFirstRunSharePref.firstRun = false
        // tutup
        this.finish()
    }

    //fungsi loaddata
    private fun executeLoadDataTransaction() {
        // progress 0
        myProgress.progress = 0
        var progress = 0
        // sqlite dari mydbroomhelper
        SQLitedb = myDBRoomHelper(this)
        // doasync
        doAsync {
            // sqlite beginusertransaction agar dapat ditulis didatabasenya
            SQLitedb?.beginUserTransaction()
            // perulangan userdata sebanyak lokasi
            for (userData in location) {
                // setiap perulangan tmbh 1 progress
                progress += 1
                // sqlite di tmbh dari userdata database
                SQLitedb?.addUserTransaction(userData)
                uiThread {
                    // progress bertambah sebanyak  lokasinya
                    myProgress.progress += progress / location.size * 100
                    Log.w("Progress", "${myProgress.progress}")
                }
            }
            // succes di tulis didatabase
            SQLitedb?.successUserTransaction()
            // akhir transaction
            SQLitedb?.endUserTransaction()
            // tutup aktivitas ini
            uiThread { finishThisActivity() }
        }
    }
}