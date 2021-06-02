package com.example.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class MainActivity : AppCompatActivity() {
    var mySQLitedb : myDBRoomHelper? = null
    var myFirstRunSharePref : FirstRunSharePref? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mySQLitedb = myDBRoomHelper(this)
        myFirstRunSharePref = FirstRunSharePref(this)
        mySQLitedb?.deleteAllUser()
        myFirstRunSharePref?.firstRun = true
        if(myFirstRunSharePref!!.firstRun){
            val secondIntent = Intent(this,pre_load::class.java)
            startActivity(secondIntent)
        }
        updateAdapter()
        lv_Location.setOnItemClickListener { parent, view, position, id ->
            doAsync {
                var locaList = mySQLitedb?.viewAllName()?.toTypedArray()

                edit_text_location.setText(locaList!![position])
            }
        }
        btn_tambahkota.setOnClickListener {
            var userTmp = User()
            userTmp.location = edit_text_location.text.toString()

            var result = mySQLitedb?.addUser(userTmp)
            if(result!=-1L){
                Toast.makeText(this,"Berhasil", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
            updateAdapter()
            edit_text_location.text.clear()

        }
        btn_delete.setOnClickListener {
            var location = edit_text_location.text.toString()
            if(location!=null || location != ""){

                doAsync {

                    mySQLitedb?.deleteUser(location)
                    updateAdapter()
                }
            }
        }
    }
    fun updateAdapter(){
        doAsync {
            var locaList = mySQLitedb?.viewAllName()?.toTypedArray()
            uiThread {
                if(lv_Location != null && locaList?.size != 0){
                    lv_Location.adapter = ArrayAdapter(applicationContext,
                        android.R.layout.simple_list_item_1,
                        locaList!!
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
    }

}
