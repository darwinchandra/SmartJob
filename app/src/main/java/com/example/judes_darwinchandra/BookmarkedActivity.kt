package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.judes_darwinchandra.Data.MyBookmarkedData
import com.example.judes_darwinchandra.adapter.postsAdapter1
import com.example.judes_darwinchandra.adapter.postsAdapterBookmark
import kotlinx.android.synthetic.main.activity_bookmarked.*
import kotlinx.android.synthetic.main.activity_location.*

class BookmarkedActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked)
        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_bookmarked.setNavigationOnClickListener {
            finish()
        }


        val list=ArrayList<MyBookmarkedData>()

        list.add(MyBookmarkedData("Finance Manager","PT. Indah Kiat Pulp & Paper","Kerawang - Jawa Barat","https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"))
        list.add(MyBookmarkedData("CEO","PT. Bukit Asam Tbk","Setia Budi - Jakarta Selatan","http://dewara.com/wp-content/uploads/2018/03/logo-bukit_asam.png"))
        list.add(MyBookmarkedData("IT Support","PT. Aneka Tambang Tbk","Medan - Sumatera Utara","https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png"))



        recyclerView_bookmarked.layoutManager= LinearLayoutManager(recyclerView_bookmarked.context, OrientationHelper.VERTICAL,false)
        recyclerView_bookmarked.adapter=
            postsAdapterBookmark(
                list
            )
    }
}