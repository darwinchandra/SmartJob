package com.example.judes_darwinchandra

import android.content.Context

var PREF_NAME = "JobPref"
var KEY_WIDGET_JobId = "widgetids"
class JobIdsPref(context: Context) {
    val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setJobId(ids: MutableSet<String>){
        var editor = pref.edit()
        editor.putStringSet(KEY_WIDGET_JobId,ids)
        editor.apply()
    }
    fun getJobId() = pref.getStringSet(KEY_WIDGET_JobId, hashSetOf())
}