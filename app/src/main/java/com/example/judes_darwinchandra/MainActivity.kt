package com.example.judes_darwinchandra

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
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
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifmanage = NotificationManager()
        notifmanage.createNotificationGroups(notificationManager!!)
        notifmanage.createNotificationChannels(notificationManager!!)






        floating_action_button.setOnClickListener {
            Toast.makeText(this, "dsadas", Toast.LENGTH_SHORT).show()
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