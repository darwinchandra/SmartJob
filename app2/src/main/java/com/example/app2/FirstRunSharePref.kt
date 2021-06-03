package com.example.app2

import android.content.Context
import android.content.SharedPreferences

class FirstRunSharePref(context: Context) {
    // membuat share pref yang menampung
    private val SharekeyPref = "FIRST_RUN"
    // share pref diambil dari shareprefencefile dalam mode private
    private var SharePref : SharedPreferences =
        context.getSharedPreferences("SharePreferenceFile", Context.MODE_PRIVATE)
    // membuat var boolean yang pertama get  sharepref true dan kedua set share pref edit memiliki value dan dijalankan
    var firstRun : Boolean
        get() = SharePref.getBoolean(SharekeyPref, true)
        set(value) { SharePref.edit().putBoolean(SharekeyPref,value).commit() }
}