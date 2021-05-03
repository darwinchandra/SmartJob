package com.example.judes_darwinchandra

import android.content.Context
import android.content.SharedPreferences
import android.view.View

class SharePrefData(context: Context, namafile: String){
    val USER_EMAIL = "EMAIL"

    private var myPreferences : SharedPreferences

    init {
        myPreferences = context.getSharedPreferences(namafile, Context.MODE_PRIVATE)
    }
    var email : String?
        get() = myPreferences.getString(USER_EMAIL,"Not registered yet")
        set(value) {
            myPreferences.edit().putString(USER_EMAIL,value).apply()
        }


    fun clearValues(){
        myPreferences.edit().clear().apply()

    }
}
