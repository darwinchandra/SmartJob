package com.example.judes_darwinchandra

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build


// membuat non-activity class baru agar fungsi createnotifikasi grup dan channel dapat diakses oleh setiap activity
class NotificationManager {

    // membuat 4 variabel untuk menampung channel masing-masing group.
    var dataChannel1 = arrayOf("Filter Spesialis","Ubah Nama","Chat Backup","Download")
    var dataChannel2 = arrayOf("Change Password","Forgot Password")
    var dataChannel3 = arrayOf("Message Notification")
    var dataChannel4 = arrayOf("Get Recomendation")

    // membuat fungsi create notifgroup dengan parameter notificationmanager dari masing masing activity nantinya
    fun createNotificationGroups(notificationManager : NotificationManager) {
        //memastikan android adalah android Oreo dan mendaftarkan
        //4 grup berbeda untuk notification
        // cara pendaftaran dengan memasukkan masing masing group pada list
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val list = mutableListOf<NotificationChannelGroup>()
            list.add(
                NotificationChannelGroup("Umum",
                    "Umum")
            )
            list.add(
                NotificationChannelGroup("Email",
                    "Email")
            )
            list.add(
                NotificationChannelGroup("Chat",
                    "Chat")
            )
            list.add(
                NotificationChannelGroup("Promosi",
                    "Promosi")
            )
            // kemudian membuat channelgroup pada notifmanageractivity tersebut.
            notificationManager!!.createNotificationChannelGroups(list)
        }
    }

    // membuat fungsi create notifchannel dengan parameter notificationmanager dari masing masing activity nantinya
    fun createNotificationChannels(notificationManager : NotificationManager) {
        // cek versi android apakah lebih besar atau saama dengan versi Oreo, soalnya versi oreo keatas harus mempunyai channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // membuat uri sebagai sumber sound notif
            val notificationSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            // membuat atribut dari sound notif
            val att = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()

            for (s in dataChannel1) {
                // mendaftarkan setiap channel notif dari datachannel1 dengan format channelid berikut.
                // Importance high agar notifikasi langsung aktif
                val Channel1 = NotificationChannel(s + "_" + "Umum", s, NotificationManager.IMPORTANCE_HIGH)
                // warna lampu indikator
                Channel1.setLightColor(Color.RED)
                Channel1.enableLights(true)
                // mengaktifkan getaran perangkat ketika notif
                Channel1.enableVibration(true)
                Channel1.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
                // memberikan nama group pada channel
                Channel1.group = "Umum"
                // mengatur suara notif
                Channel1.setSound(notificationSound, att);
                //mendaftarkan channel ke object notifmanager
                if(notificationManager!=null){
                    notificationManager?.createNotificationChannel(Channel1)
                }
            }
            for(s in dataChannel2){
                val Channel2 = NotificationChannel(s + "_" + "Email", s, NotificationManager.IMPORTANCE_HIGH)
                Channel2.setLightColor(Color.RED);
                Channel2.enableLights(true)
                Channel2.enableVibration(true)
                Channel2.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
                Channel2.group = "Email"
                Channel2.setSound(notificationSound, att);
                if(notificationManager!=null){
                    notificationManager?.createNotificationChannel(Channel2)
                }
            }
            for(s in dataChannel3){
                val Channel3 = NotificationChannel(s + "_" + "Chat", s, NotificationManager.IMPORTANCE_HIGH)
                Channel3.setLightColor(Color.RED);
                Channel3.enableLights(true)
                Channel3.enableVibration(true)
                Channel3.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
                Channel3.group = "Chat"
                Channel3.setSound(notificationSound, att);
                if(notificationManager!=null){
                    notificationManager?.createNotificationChannel(Channel3)
                }
            }
            for(s in dataChannel4){
                val Channel4 = NotificationChannel(s + "_" + "Promosi", s, NotificationManager.IMPORTANCE_HIGH)
                Channel4.setLightColor(Color.RED);
                Channel4.enableLights(true)
                Channel4.enableVibration(true)
                Channel4.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
                Channel4.group = "Promosi"
                Channel4.setSound(notificationSound, att);
                if(notificationManager!=null){
                    notificationManager?.createNotificationChannel(Channel4)
                }
            }
        }
    }

}
