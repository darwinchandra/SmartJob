package com.example.judes_darwinchandra

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
//variabel Sound Pool
private var sound : SoundPool? =null
//Untuk menangkap ID dari sound pool
private var soundIDplayer= 1

class MainActivity : AppCompatActivity() {
    var JobSchedulerId = 10
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
                if(Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches()){
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


        floating_action_button.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
        // notifikasi reminder bookmarked dijalankan setelah 10000mili detik setelah aplikasi dijalankan
        // doAsync diletakkan pada oncreate
        doAsync {
            Thread.sleep(10000L)
            uiThread{
                showNotifReminder()
            }
        }
    }
    //fungsi untuk menload sound pool
    override fun onStart() {
        //membaca data
        super.onStart()
        //untuk versi yang sudah diatas lolipop
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //memanggil fungsi untuk membuat soundpool
            createNewSoundPool()
        }
        //untuk versi lama
        else{
            //memanggil fungsi untuk membuat soundpool
            createOldSoundPool()
        }
        //Menload sound yang akan dipakai dan set prioritas
        soundIDplayer= sound?.load(this,R.raw.transitiontoberanda,1)?: 0
    }

    private fun createOldSoundPool() {
        //untuk Membuat SoundPool dengan maxstream,type stream dan quality
        sound = SoundPool(15,AudioManager.STREAM_MUSIC,0)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createNewSoundPool() {
        sound = SoundPool.Builder()
            .setMaxStreams(15)
            .build()
    }

    override fun onStop() {
        super.onStop()
        sound?.release()
        sound = null
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
        //Cek jika id dari sound tidak sama dengan nol maka akan memainkan soundnya
        if(soundIDplayer != 0){
            //memainkan sound dan Set sound kiri dan kanan, priority,apakah diulang atau tidak
            sound?.play(soundIDplayer, .99f, .99f,1,0,.99f)
        }
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




    private fun showNotifReminder(){
        //notif manager getsystem notif service
        var notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        //class baru untuk create notif. class "NotificationManager" telah dibuat pada class tersendiri pertemuan sebelumnya
        // sehingga dapat di akses pada tiap activity
        val notifmanage = NotificationManager()
        // create group dan channelnya
        notifmanage.createNotificationGroups(notificationManager!!)
        notifmanage.createNotificationChannels(notificationManager!!)

        // membuat intent untuk mengarahkan ke bookmark activity
        val notifyBookmarkIntent = Intent(this, BookmarkedActivity::class.java)
            .apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }

        // membuat pending intent guna untuk memberikan intent content pada notifikiasi nantinya
        // pending intent ii berisi intent yang sebelumnya telah kita buat yaitu  notifyBookmarkIntent
        val myPendingIntent = PendingIntent.getActivity(this,0,
            notifyBookmarkIntent,
            PendingIntent.FLAG_CANCEL_CURRENT);

        // pembuatan variable notifikiasi yang dan diisi ContentIntentnya menjadi myPendingIntent
        // untuk menghandle ketika diclick ada intent yang akan di triger dan mengarahkannya kepada bookmarkedactivity
        var myNotification = NotificationCompat.Builder(this,"Reminder_Promosi")
            // title notif
            .setContentTitle("Bookmarked Loker")
            //isi notif
            .setContentText("Yuk cek kembali Loker yang sudah di bookmark kamu")
            // group notif
            .setGroup("Promosi")
            //icon notif
            .setSmallIcon(R.drawable.ic_baseline_bookmark_24)
            .setContentIntent(myPendingIntent)
        // build var notif yang telah dbuat dengan id 100
        notificationManager?.notify(100,myNotification.build())
    }
}