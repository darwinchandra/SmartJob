package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import kotlinx.android.synthetic.main.activity_pre_load.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class pre_load : AppCompatActivity() {
    var mySQLitedb: myDBRoomHelper? = null
    private var mhs = listOf(
        User(1, "ACEH"),
        User(2, "BALI"),
        User(3, "BANGKA BELITUNG"),
        User(4, "BANTEN"),
        User(5, "BENGKULU"),
        User(6, "GORONTALO"),
        User(7, "JAKARTA RAYA"),
        User(8, "JAMBI"),
        User(9, "JAWA BARAT"),
        User(10, "JAWA TENGAH")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_load)
        executeLoadDataTransaction()
    }

    private fun finishThisActivity() {
        var myFirstRunSharePref = FirstRunSharePref(this)
        myFirstRunSharePref.firstRun = false
        this.finish()
    }

    private fun executeLoadDataTransaction() {
        btn_no.isEnabled = false
        btn_yes.isEnabled = false
        myProgress.progress = 0
        var progress = 0
        mySQLitedb = myDBRoomHelper(this)
        doAsync {
            mySQLitedb?.beginUserTransaction()
            for (userData in mhs) {
                progress += 1
                mySQLitedb?.addUserTransaction(userData)
                uiThread {
                    myProgress.progress += progress / mhs.size * 100
                    Log.w("Progress", "${myProgress.progress}")
                }
            }
            mySQLitedb?.successUserTransaction()
            mySQLitedb?.endUserTransaction()
            uiThread { finishThisActivity() }
        }
    }
}