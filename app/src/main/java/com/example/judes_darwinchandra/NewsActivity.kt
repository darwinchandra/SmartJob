package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.judes_darwinchandra.Data.MyNewsData
import com.example.judes_darwinchandra.adapter.postsAdapterNews
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {

    //BroadcastReceiver untuk menerima object yang telah di broadcast
    private val MyNewsReceiver= object : BroadcastReceiver() {
        @SuppressLint("WrongConstant")
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onReceive(context: Context, intent: Intent) {
            //get parcelable dari object agar dapat digunakan pada activity ini
            var listNews = intent.getParcelableArrayListExtra<MyNewsData>(EXTRA_NEWS)!!
            //init nilai dari recyclerview
            recyclerView_News.layoutManager= LinearLayoutManager(recyclerView_News.context, OrientationHelper.VERTICAL,false)
            //set adapter dari recyclerview dengan data context dan object itu sendiri
            // agar dapat memberikan isi pada recyclerview kita
            recyclerView_News.adapter=postsAdapterNews(context, listNews!!)

        }
    }
    //init var job scheduler
    var JobSchedulerId = 10
    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_News.setNavigationOnClickListener {
            finish()
        }


        startMyJob()

        //memberikan filter action yang sama saat sendBroadcast
        var filterNews=IntentFilter(ACTION_NEWS)
        //register untuk trigger receiver yang telah dibuat
        registerReceiver(MyNewsReceiver,filterNews)



    }

    override fun onDestroy() {
        super.onDestroy()
        // unreg receiver ketika onDestroy
        unregisterReceiver(MyNewsReceiver)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startMyJob() {
        //var serviceComponen digunakan untuk mendaftarkan job Scheduler sebagai service yang akan digunakan
        var serviceComponent = ComponentName(this,News::class.java)
        //membentuk job info untuk job scheduler
        var mJobInfo = JobInfo.Builder(JobSchedulerId,serviceComponent)
            //menentukan jenis koneksi internet
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            //menentukan apakah job dijalankan ketika perangkat dalam kondisi idle
            .setRequiresDeviceIdle(false)
            //menentukan apakah job dijalankan pada saat baterai di charger
            .setRequiresCharging(false)
        //membentuk objek Job Scheduler
        var JobNews = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        //menjalankan job Scheduler
        JobNews.schedule(mJobInfo.build())
    }
}
