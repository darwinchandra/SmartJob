package com.example.judes_darwinchandra

import android.app.AlertDialog
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_video_call.*



class ChangePasswordActivity : AppCompatActivity() {
    //init notif manager
    var notificationManager : NotificationManager? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_changePass.setNavigationOnClickListener {
            finish()
        }
        //notif manager getsystem notif service
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        //class baru untuk create notif
        val notifmanage = NotificationManager()
        notifmanage.createNotificationGroups(notificationManager!!)
        notifmanage.createNotificationChannels(notificationManager!!)

        btn_ConfirmChangePass.setOnClickListener {
            if (newpass.text.toString()==confirmpass.text.toString()){
                showToast(buildToastMessagePass("darwinch@gmail.com"))
                // init channelid
                var channel_id =""
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    channel_id = notificationManager!!.getNotificationChannel("Change Password_Email"
                    ).id
                }
                //membuat notifikasi
                var myNotification = NotificationCompat.Builder(this,channel_id)
                    // title notif
                    .setContentTitle("Confirmation Request")
                    //isi notif
                    .setContentText("Click To Check Your Mail Inbox")
                    // group notif
                    .setGroup("Email")
                    //icon notif
                    .setSmallIcon(R.drawable.ic_baseline_mail_24)

                //Membentuk Aksi Intent UNTUK membuka inbox email
                val notifyIntent = Intent(Intent.ACTION_MAIN)
                notifyIntent.addCategory(Intent.CATEGORY_APP_EMAIL)
                val notifyPandingIntent = PendingIntent.getActivities(
                    this, NOTIFICATION_EMAIL,
                    arrayOf(notifyIntent),
                    PendingIntent.FLAG_UPDATE_CURRENT)
                myNotification.setContentIntent(notifyPandingIntent)

                // memunculkan notifikasi dengan id konstanta NOTIFICATION_EMAIL
                notificationManager?.notify(
                    NOTIFICATION_EMAIL,
                    myNotification.build())

            }
            else if(newpass.text.toString()!=confirmpass.text.toString()){
                showToast(buildToastMessagePassWrong())
            }

        }
    }

    fun forgotpass_changepass(view: View) {
        val intent = Intent(this, ForgotPasswordProfileActivity::class.java)
        startActivity(intent)
    }


    private fun showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    companion object{
        fun buildToastMessagePass(name:String):String{
            return "Please confirm by $name"
        }
        fun buildToastMessagePassWrong():String{
            return "Confirmpassword must be same with New Password"
        }
    }



}