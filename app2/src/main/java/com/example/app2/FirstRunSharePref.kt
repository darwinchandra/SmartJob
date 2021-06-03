package com.example.app2

import android.content.Context
import android.content.SharedPreferences

class FirstRunSharePref(context: Context) {
    private val SharekeyPref = "FIRST_RUN"
    private var SharePref : SharedPreferences =
        context.getSharedPreferences("SharePreferenceFile", Context.MODE_PRIVATE)
    var firstRun : Boolean
        get() = SharePref.getBoolean(SharekeyPref, true)
        set(value) { SharePref.edit().putBoolean(SharekeyPref,value).commit() }
}