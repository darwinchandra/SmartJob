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
        btn_submit.setOnClickListener {
            var userTmp = User()
            userTmp.location = edit_text_name.text.toString()

            var result = mySQLitedb?.addUser(userTmp)
            if(result!=-1L){
                Toast.makeText(this,"Berhasil", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
            updateAdapter()
            edit_text_name.text.clear()

        }
        btn_delete.setOnClickListener {
            var nama = spinner1.selectedItem.toString()
            if(nama!=null || nama != ""){

                doAsync {

                    mySQLitedb?.deleteUser(nama)
                    updateAdapter()
                }
            }
        }
    }
    fun updateAdapter(){
        doAsync {
            var nameList = mySQLitedb?.viewAllName()?.toTypedArray()
            uiThread {
                if(spinner1 != null && nameList?.size != 0){
                    var arrayAdapter = ArrayAdapter(applicationContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        nameList!!
                    )
                    spinner1.adapter = arrayAdapter
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
    }

}
