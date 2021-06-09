package com.example.judes_darwinchandra

import android.content.Context
import android.content.SharedPreferences
import android.view.View

// membuat class untuk shared pref dengan param context dan string berupa nama file
class SharePrefEmailLogin(context: Context, namafile: String){
    //key
    val USER_EMAIL = "EMAIL"
    private var myPreferences : SharedPreferences
    // init untuk getSharedPref agar bisa di get
    init {
        myPreferences = context.getSharedPreferences(namafile, Context.MODE_PRIVATE)
    }
    // var email yang akan menampung data yang akan di share
    var email : String?
        //get dan set , get berguna ketika ingin memakai variabel email,
        // sedangakan set untuk memberikan nilai pada variabel.
        // pemberian nilai diperlukan fungsi edit() terlebih dahulu
        // dan juga apply() agar data terubah
        get() = myPreferences.getString(USER_EMAIL,"Not registered yet")
        set(value) {
            myPreferences.edit().putString(USER_EMAIL,value).apply()
        }

}
