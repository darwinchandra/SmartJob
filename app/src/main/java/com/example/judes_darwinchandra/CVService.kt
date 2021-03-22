package com.example.judes_darwinchandra

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class CVService : Service() {

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"Start Download", Toast.LENGTH_SHORT).show()
        Thread(Runnable{
            for(i in 0..10){
                try{
                    Thread.sleep(500L)
                }
                catch(e : Exception){

                }
            }
        })
        stopSelf()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this,"Download Finish", Toast.LENGTH_SHORT).show()
    }
}
