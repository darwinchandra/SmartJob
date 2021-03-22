package com.example.judes_darwinchandra

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.JobIntentService

class DownloadService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
            var downloadProgress = 0
        do{
            Thread.sleep(20)
            downloadProgress+=1
            var intentDownload = Intent(ACTION_DOWNLOAD)
            intentDownload.putExtra(EXTRA_PERSEN,downloadProgress)
            intentDownload.putExtra(EXTRA_FINISH,false)
            if(downloadProgress>=100)
                intentDownload.putExtra(EXTRA_FINISH,true)
            sendBroadcast(intentDownload)
        }while (downloadProgress<100)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Download Selesai",Toast.LENGTH_SHORT).show()
    }
    companion object{
        fun enqueueWork(context:Context,intent: Intent){
            enqueueWork(context,DownloadService::class.java,
            JOB_ID_DOWNLOAD,intent)
        }
    }
}