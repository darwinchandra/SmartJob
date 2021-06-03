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
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_video_call.*
import kotlinx.android.synthetic.main.dialogapply.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


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


        //build db dengan nama userdb
        var db= Room.databaseBuilder(
            this,
            MyDBRoomHelper::class.java,
            "userdb.db"
        ).build()


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


        // update pass
        btn_ConfirmChangePass.setOnClickListener {
            //init file sharepref
            val mySharedPref= SharePrefData(this, sharePrefFileName)
            //ambil data email yang login pada file sharedPref
            var emailLogin = mySharedPref.email.toString()
            //jika passbaru dengan passconfirm berbeda maka diminta untuk mengetik ulang pass tersebut
            if(newpass.text.toString()!=confirmpass.text.toString()){
                //toast untuk mengetik ulang
                showToast(buildToastMessagePassWrong())
                //fokus pada confirm pass , untuk mengetik ulang passnya
                confirmpass.requestFocus()
            }
            else{
                //jika confirm pass sudah cocok
                //init variabel yang sering dipakai
                var newPass=newpass.text.toString()
                var oldPass=oldpw.text.toString()
                // var ini untuk cek apakah oldpassword sudah benar atau tidak,
                // kondisi awal kita asumsikan masih salah
                var oldPasswordcheck=false
                // do async proses baca db
                doAsync {
                    // perulangan di setiap data pada database
                    for(allData in db.userDao().getAllData()){
                        // cek apakah email login tersebut sudah sama dengan email pada for, jika sama
                        // cek lagi apakah oldpassword sudah cocok
                        if(emailLogin== allData.email && oldPass==allData.password){
                            //apabila dua inputan tersebut sudah cocok, maka panggil query update pass
                            db.updatePasswordTransaction(emailLogin,newPass)
                            // dan buat status menjadi true
                            oldPasswordcheck=true
                        }
                    }
                    uiThread {
                        //cek apakah status oldpass false atau true
                        if (oldPasswordcheck==false){
                            //apabla masih false, maka toast oldpassword don't match
                            Toast.makeText(it, "Old Password don't match", Toast.LENGTH_SHORT).show()
                            //fokus pada old password
                            oldpw.requestFocus()
                        }
                        else{
                            // apabila cocok, maka toast
                            Toast.makeText(it, "Password Changed", Toast.LENGTH_SHORT).show()
                            // dua fungsi di bawah ini, dipakai ketika mau konfirm change password lewat email
                            //showToast(buildToastMessagePass(emailLogin))
                            // shownotifvalid()
                            // clear semua edit text
                            clearText()
                            //fokus pada oldpass
                            oldpw.requestFocus()
                        }
                    }
                }
            }
        }
    }

    private fun clearText() {
        oldpw.text?.clear()
        newpass.text?.clear()
        confirmpass.text?.clear()
    }

    fun shownotifvalid(){
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