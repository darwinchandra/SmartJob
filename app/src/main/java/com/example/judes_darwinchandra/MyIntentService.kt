package com.example.judes_darwinchandra

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService
import java.lang.Exception


private const val JOB_ID = 3213
const val EXTRA_TIME = "EXTRA_TIME"
class MyIntentService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        showToast("Start")
        if(intent!=null){
            var duration= intent.getLongExtra(EXTRA_TIME,0L)
            try {
                for(i in 0..10){
                    Thread.sleep(duration)
                    Log.d("JobIntentService","Send = $i")
                }
            }
            catch (e : Exception){}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("Finish")
    }
    val mHandler: Handler = Handler(Looper.getMainLooper())

    fun showToast(text : String=""){
        mHandler.post(Runnable {
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
        })
    }
    companion object{
        fun enqueueWork(context: Context,intent: Intent){
            enqueueWork(context, MyIntentService::class.java,
            JOB_ID,intent)
        }
    }
}


