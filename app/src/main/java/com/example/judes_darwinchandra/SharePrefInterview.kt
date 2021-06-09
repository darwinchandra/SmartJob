package com.example.judes_darwinchandra

import android.content.Context
import android.content.SharedPreferences
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.adapter.postsAdapterInterview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var mExampleList: ArrayList<objDetailLoker>? = null
private val mRecyclerView: RecyclerView? = null
private val mAdapter: postsAdapterInterview? = null
private val mLayoutManager: RecyclerView.LayoutManager? = null
class SharePrefInterview(context: Context, interview: String) {

    val PrefInterview = "Interview"
    private var myPreferences : SharedPreferences
    init {
        myPreferences = context.getSharedPreferences(interview, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = myPreferences.edit()
        val gson = Gson();
        val json = gson.toJson(mExampleList)
        val sType = object : TypeToken<List<objDetailLoker>>() { }.type

        mExampleList = gson.fromJson(json, sType)
        if (mExampleList == null) {
            mExampleList = ArrayList()
        }
    }
    // var email yang akan menampung data yang akan di share
    var Interview : String?
        //get dan set , get berguna ketika ingin memakai variabel email,
        // sedangakan set untuk memberikan nilai pada variabel.
        // pemberian nilai diperlukan fungsi edit() terlebih dahulu
        // dan juga apply() agar data terubah
        get() = myPreferences.getString(PrefInterview, "Empty")
        set(value) {
            myPreferences.edit().putString(PrefInterview, value).apply()
        }
}


