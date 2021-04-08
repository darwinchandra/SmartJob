package com.example.judes_darwinchandra

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MyReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        Log.w("Diana", "Diana")
        //inisialisasi notify , channel , name sama importance
        val Notifyid = 30103
        val Channel_id = "my_channel_01"
        val name = "ON/OFF"
        // importance high agar notifikasi keluar
        val importance = NotificationManager.IMPORTANCE_HIGH
        // inisialisai val yang dapat menampung notifikasichannel dari channel_id,name,importance
        val nNotifyChannel = NotificationChannel(Channel_id,
            name,
            importance)//

        //Membuat Notifikasi
        val mBuilder = NotificationCompat.Builder(context!!,Channel_id)
                //mengambil gambar
            .setSmallIcon(R.drawable.interview)
                //menentukan isi teks dari extra pesan
            .setContentText("Interview Akan diadakan Perusahaan pada : 08 April 2021 9:19")
                //menentukan judul
            .setContentTitle("Interview")
                //menentukan priotasnya
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        //notifikasi bekerja sebagai service sehingg dapat di jalankan walaupun aplikasi dalam keadaan tertutup
        var mNotificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        //memastikan semua notifikasi sudah dihapus sebelumnya
        for(s in mNotificationManager.notificationChannels){
            mNotificationManager.deleteNotificationChannel(s.id)
        }
        //mendaftarkan notifikasi channel kedalam notifikasi manager
        mNotificationManager.createNotificationChannel(nNotifyChannel)
        // menampilkan notifikasinya
        mNotificationManager.notify(Notifyid,mBuilder.build())
    }
}