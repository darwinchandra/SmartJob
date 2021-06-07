package com.example.judes_darwinchandra

import android.content.Context

var PREF_NAME = "MyPref"
var KEY_WIDGET_IDS = "widgetids"
class JobIdsPref(context: Context) {
    val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setIds(ids: MutableSet<String>){
        var editor = pref.edit()
        editor.putStringSet(KEY_WIDGET_IDS,ids)
        editor.apply()
    }
    fun getIds() = pref.getStringSet(KEY_WIDGET_IDS, hashSetOf())
}