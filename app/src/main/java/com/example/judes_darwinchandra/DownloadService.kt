package com.example.judes_darwinchandra

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.JobIntentService
//Class DownloadService yang merupakan service yang menjalankan service yang berjalan dilatar belakang
class DownloadService : JobIntentService() {
    //Semua proses yang berjalan dilatar belakang dimasukkan pada onHandleWork
    //prosesnya berjalan secara asyoncrhronous
    override fun onHandleWork(intent: Intent) {
        //progress download dimulai dari 0
            var downloadProgress = 0
        do{
            // thread sleep untuk mensimulasikan data yang di download 1 persen setiap 1 detik
            Thread.sleep(20)
            //progress download bertambah 1
            downloadProgress+=1
            //intent download mengirimkan action string untuk menghubungkan service dengan broadcast
            var intentDownload = Intent(ACTION_DOWNLOAD)
            //Mengirimkan data ke service berupa progress download
            intentDownload.putExtra(EXTRA_PERSEN,downloadProgress)
            //Mengirimkan data ke service untuk mengecek apakah sudah selesai atau belum
            //karena belum selesai maka nilainya adalah false
            intentDownload.putExtra(EXTRA_FINISH,false)
            //jika progress download nya sudah lebih besar dari selesai maka extra_finish berubah jadi true
            if(downloadProgress>=100)
                intentDownload.putExtra(EXTRA_FINISH,true)
            //Mengirimkan data menggunakan sendBroadcast
            sendBroadcast(intentDownload)
        //melakukan perulangan jika download progress lebih kecil dari 100
        }while (downloadProgress<100)
    }
    //onDestroy yang berisikan perintah saat kita menghentikan service atau service dihentikan oleh sistem android
    override fun onDestroy() {
        //menampilakan "Download Selesai" saat service berhenti
        super.onDestroy()
        Toast.makeText(this,"Download Selesai",Toast.LENGTH_SHORT).show()
    }
    //Proses mendaftakan pekerjaan baru
    companion object{
        //enqueWork mendaftarkan antrian pekerjaan baru untuk ditangani oleh service
        fun enqueueWork(context:Context,intent: Intent){
            enqueueWork(context,DownloadService::class.java,
            JOB_ID_DOWNLOAD,intent)
        }
    }
}