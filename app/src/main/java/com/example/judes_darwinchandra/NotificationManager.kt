package com.example.judes_darwinchandra

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build

class NotificationManager {


    var dataChannel1 = arrayOf("Filter Spesialis","Ubah Nama","Chat Backup")
    var dataChannel2 = arrayOf("Change Password","Forgot Password")
    var dataChannel3 = arrayOf("Message Notification")
    var dataChannel4 = arrayOf("Get Recomendation")


    fun createNotificationGroups(notificationManager : NotificationManager) {
        //memastikan android adalah android Oreo dan mendaftarkan
        //4 grup berbeda untuk notification
        // jika ada grup berbeda, anda dapat menambahkan list
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
            notificationManager!!.createNotificationChannelGroups(list)
        }
    }
    fun createNotificationChannels(notificationManager : NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val att = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            for (s in dataChannel1) {
                val Channel1 = NotificationChannel(s + "_" + "Umum", s, NotificationManager.IMPORTANCE_HIGH)
                Channel1.setLightColor(Color.RED);
                Channel1.enableLights(true)
                Channel1.enableVibration(true)
                Channel1.group = "Umum"
                Channel1.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
                Channel1.setSound(notificationSound, att);

                if(notificationManager!=null){
                    notificationManager?.createNotificationChannel(Channel1)
                }
            }
            for(s in dataChannel2){
                val Channel2 = NotificationChannel(s + "_" + "Email", s, NotificationManager.IMPORTANCE_HIGH)
                Channel2.setLightColor(Color.RED);
                Channel2.enableLights(true)
                Channel2.enableVibration(true)
                Channel2.group = "Email"
                Channel2.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
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
                Channel3.group = "Chat"
                Channel3.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
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
                Channel4.group = "Promosi"
                Channel4.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
                Channel4.setSound(notificationSound, att);
                if(notificationManager!=null){
                    notificationManager?.createNotificationChannel(Channel4)
                }
            }
        }
    }

}
