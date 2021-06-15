package com.example.judes_darwinchandra

import android.content.Context

// membuat var pref
var PREF_NAME = "JobPref"
// membuat var widget
var KEY_WIDGET_JobId = "widgetids"
class JobIdsPref(context: Context) {
    // var pref mengambil preferences dari pref name dalam mode private
    val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // membuat fungsi setjobid mutaableset
    fun setJobId(ids: MutableSet<String>){
        //editor diedit
        var editor = pref.edit()
        // editor diisi dengan widget
        editor.putStringSet(KEY_WIDGET_JobId,ids)
        // editor diaplikasikan
        editor.apply()
    }
    // membuat fungsi get dan mengambil stringset dari widget
    fun getJobId() = pref.getStringSet(KEY_WIDGET_JobId, hashSetOf())
}