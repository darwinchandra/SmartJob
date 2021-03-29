package com.example.judes_darwinchandra

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.annotation.IntegerRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var dataChannel1 = arrayOf("Filter Spesialis","Ubah Nama","Chat Backup")
    var dataChannel2 = arrayOf("Change Password","Forgot Password")
    var dataChannel3 = arrayOf("Message Notification")
    var dataChannel4 = arrayOf("Get Recomendation")
    var notificationManager : NotificationManager? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.black)
        setContentView(R.layout.activity_main)


        //login button wrong ketika isinya kosong
        login_button.isEnabled=false
        var valid= arrayOf(0,0)
        inputEmail.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches()){
                    valid[0]=1
                    cekvalid(valid)
                }
                else if(inputEmail.text.toString().trim().isEmpty()){
                    inputEmail.setError("Email can't be empty")
                    valid[0]=0
                    cekvalid(valid)
                }
                else{
                    inputEmail.setError("Invalid Email")
                    valid[0]=0
                    cekvalid(valid)
                }
            }
        })
        inputPass.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(inputPass.text.toString().trim().isEmpty()){
                    inputPass.setError("Password can't be empty")
                    valid[1]=0
                    cekvalid(valid)
                }
                else{
                    valid[1]=1
                    cekvalid(valid)
                }
            }
        })






        // buat notif disini
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        createNotificationGroups()
        createNotificationChannels()















    }
    private fun createNotificationGroups() {
//memastikan android adalah android Oreo dan mendaftarkan
//2 grup berbeda untuk notification
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
    private fun createNotificationChannels() {
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
                val Channel2 = NotificationChannel(s + "_" + "Email", s, NotificationManager.IMPORTANCE_NONE)
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
                val Channel3 = NotificationChannel(s + "_" + "Chat", s, NotificationManager.IMPORTANCE_NONE)
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
                val Channel4 = NotificationChannel(s + "_" + "Promosi", s, NotificationManager.IMPORTANCE_NONE)
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
    fun cekvalid(validasi:Array<Int>){

        if(validasi[0]==1 && validasi[1]==1){
            login_button.isEnabled=true
        }
        else{
            login_button.isEnabled=false
        }
    }
    //Intent Eksplisit
    //fungsi untuk keluar kehalaman Registrasi
    fun gotoRegis(view: View) {

        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
    //fungsi untuk keluar kehalaman Beranda
    fun gotoBeranda(view: View) {
        val intent = Intent(this, BerandaActivity::class.java)
        startActivity(intent)

    }
    //fungsi untuk keluar kehalaman Forgot Password
    fun forgotpass_login(view: View) {
        val intent=Intent(this,ForgotPasswordActivity::class.java)
        startActivity(intent)
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }
    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}