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


    private val MyNewsReceiver= object : BroadcastReceiver() {
        @SuppressLint("WrongConstant")
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onReceive(context: Context, intent: Intent) {

            var listNews = intent.getParcelableArrayListExtra<MyNewsData>(EXTRA_NEWS)!!
            recyclerView_News.layoutManager= LinearLayoutManager(recyclerView_News.context, OrientationHelper.VERTICAL,false)
            recyclerView_News.adapter=postsAdapterNews(context, listNews!!)
            Log.w("abc",listNews.toString())
        }
    }
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
        var filterNews=IntentFilter(ACTION_NEWS)
        registerReceiver(MyNewsReceiver,filterNews)



    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(MyNewsReceiver)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startMyJob() {
        var serviceComponent = ComponentName(this,News::class.java)
        var mJobInfo = JobInfo.Builder(JobSchedulerId,serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresDeviceIdle(false)
            .setRequiresCharging(false)
            .setPeriodic(3*60*1000)
        var JobNews = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        JobNews.schedule(mJobInfo.build())
        Toast.makeText(this,"Job Service Berjalan", Toast.LENGTH_SHORT).show()
    }
}
