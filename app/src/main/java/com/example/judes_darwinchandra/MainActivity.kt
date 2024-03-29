package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.room.Room
import com.example.judes_darwinchandra.db.MyDBRoomHelper
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.android.synthetic.main.activity_beranda.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import com.example.judes_darwinchandra.ExistingUser as ExistingUser


var sharePrefFileName="PrefEmail"

//variabel Sound Pool
private var sound : SoundPool? =null
//Untuk menangkap ID dari sound pool
private var soundIDplayer= 1

class MainActivity : AppCompatActivity() {

    var JobSchedulerId = 10
    var notificationManager: NotificationManager? = null
    // membuat var yang menampung InterstitialAd isinya kosong
    private var mInterstitialAd: InterstitialAd? = null
    //membuat Tag
    private final var TAG = "MainActivity"
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}

        switch2.setOnClickListener{
            if(switch2.isChecked){
                adView.visibility = View.VISIBLE
                adView.loadAd(AdRequest.Builder().build())
            }
            else{
                adView.visibility = View.GONE
                adView.setEnabled(false);
            }
        }


        adView.adListener = object : AdListener() {


        }


        var alarmIntent = Intent(this, jobInterviewMessage::class.java).let {
            it.action = scheduleJobWidget.ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(this, 101, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE, 1)

        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC, cal.timeInMillis, 60000, alarmIntent)


        //membentuk database dengan nama userdb.db
        var db = Room.databaseBuilder(
            this,
            MyDBRoomHelper::class.java,
            "userdb.db"
        ).build()
        doAsync {
            db.userDao().getAllData()
            Log.w("tes", "tes")
        }

        // ad interstitial dibuat
        createad()

        login_button.setOnClickListener {

            //hasil untuk menampung data yang akan diinput
            var hasil = ""
            var findData = false
            //berisi email dan password yang diinput dan di konversi kedalam bentuk string
            var mailLogin = inputEmail.text.toString()
            var passLogin = inputPass.text.toString()

            doAsync {
                //menvalidasi email apakah sudah ada pada database
                var index = db.selectValidTransaction(mailLogin, passLogin)
                //berisi return code pada index
                var valid = index.size

                //mendapatkan semua data yang ada pada database kemudian menyimpannya pada variabel hasil
                uiThread {
                    //jika valid>0 menandakan jika email sesuai dengan yang ada pada database maka user dapat masuk ke dalam halaman beranda
                    if (valid > 0) {
                        // jika ad tidak null maka keluarkan ad dimain activity
                            if(switch2.isChecked){
                                if (mInterstitialAd != null) {
                                    mInterstitialAd?.show(this@MainActivity)
                                    val intent = Intent(it, BerandaActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        // yang lainnya keluarkan ad belum tersedia dan akan menuju ke beranda
                        else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.")
                            finish()
                            val intent = Intent(it, BerandaActivity::class.java)
                            startActivity(intent)

                        }

                    }
                    //jika data tidak cocok dengan data yang ada pada database maka akan menampilkan toast bahwa username dan password salah
                    else {
                        Toast.makeText(it, "Username dan Password Tidak Cocok", Toast.LENGTH_SHORT)
                            .show()
                        inputEmail.requestFocus()
                    }
                    //menampilkan log berupa data pada database yang telah kita simpan pada variabel hasil

                }

            }


            var mySharedPref = SharePrefEmailLogin(this, sharePrefFileName)

            mySharedPref.email = inputEmail.text.toString()

            // ketika di click login button maka akan dijalankan fungsi write
            if (isExternalStorageReadable()) {
                writeFileExternalMemory()
            }
            clearDataLogin()
            delFile()

            //Cek jika id dari sound tidak sama dengan nol maka akan memainkan soundnya
            if (soundIDplayer != 0) {
                //memainkan sound dan Set sound kiri dan kanan, priority,apakah diulang atau tidak
                sound?.play(soundIDplayer, .99f, .99f, 1, 0, .99f)
            }
        }



        //login button wrong ketika isinya kosong
        login_button.isEnabled = false
        var valid = arrayOf(0, 0)
        inputEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches()) {
                    valid[0] = 1
                    cekvalid(valid)
                } else if (inputEmail.text.toString().trim().isEmpty()) {
                    inputEmail.setError("Email can't be empty")
                    valid[0] = 0
                    cekvalid(valid)
                } else {
                    inputEmail.setError("Invalid Email")
                    valid[0] = 0
                    cekvalid(valid)
                }
            }
        })
        inputPass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (inputPass.text.toString().trim().isEmpty()) {
                    inputPass.setError("Password can't be empty")
                    valid[1] = 0
                    cekvalid(valid)
                } else {
                    valid[1] = 1
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
            uiThread {
                showNotifReminder()
            }
        }
        exist.setOnClickListener {
            val intent = Intent(this, ExistingUser::class.java)
            startActivity(intent)
        }


    }
    // membuat fungsi ad
    fun createad(){
        // membuat var yang menampung build adrequest
        var adRequest = AdRequest.Builder().build()
        // insterstitial di load dan dipanggil kembali jika ad failed atau ad loaded
        InterstitialAd.load(this@MainActivity,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                // jika failed keluar kan tag di load
                Log.d(TAG, adError?.message)
                // ad dibuat menjadi kosong
                mInterstitialAd = null
            }


            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                // jika loaded maka keluarkan tag
                Log.d(TAG, "Ad was loaded.")
                // dan mengeluarkan adnya
                mInterstitialAd = interstitialAd
                // insterstitial di load dan dipanggil kembali pada fullscreen
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {

                    override fun onAdDismissedFullScreenContent() {
                        // jika ditutup maka keluarkan tag dan finish activity dan kemudian menuju ke beranda
                        Log.d(TAG, "Ad was dismissed.")
                        finish()
                        val intent = Intent(this@MainActivity, BerandaActivity::class.java)
                        startActivity(intent)


                    }
                    // jika ad gagal pada saat fullscreen maka keluar log bahwa gagal ad
                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        Log.d(TAG, "Ad failed to show.")
                    }
                    // jika ad keluar pada saat fullscreen maka keluar tag dan membuat insterstitial ad null
                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG, "Ad showed fullscreen content.")
                        mInterstitialAd = null;
                    }
                }
            }
        })}


    // membuat fungsi write ke external memory
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun writeFileExternalMemory() {
        //mengambil file dari external memory yang sudah ada
        var myLog = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        // jika file tidak ada maka membuat file baru
        if (!myLog.exists()) {
            myLog.mkdir()
        }
        // false duplicate isinya
        var duplicateEmail = false
        // jika file sudah ada maka dibuat arraylist
        if (File(myLog, "ExistingUser.txt").exists()) {
            val listemail = ArrayList<String>()
            // file yang dibuat dan ditambahkan ke array
            File(myLog, "ExistingUser.txt").forEachLine(Charsets.UTF_8) {
                listemail.add(it)
            }
            //membuat perulangan pada setiap data list email.Ketika email yang ad pada edittext telah ada di file tersebut.
            // Maka status duplicateEmail menjadi false
            Log.w("listemail", listemail.toString())
            // setiap array listemail maka isi text ditulis dan duplicate true untuk menghindari adanya double
            for (s in listemail) {
                if (s == inputEmail.text.toString()) {
                    duplicateEmail = true
                }
            }
        }
        // jika double salah maka mengambil text dari textbox dan masukkan kefile
        if (duplicateEmail == false) {
            File(myLog, "ExistingUser.txt").apply {
                appendText(inputEmail.text.toString() + "\n")
            }
        }
        // membersih edittext
        inputEmail.text?.clear()
    }

    // membuat fungsi untuk permission external storage
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isExternalStorageReadable(): Boolean {
        // mengecek apakah selfpermission sudah ada atau belum jika belum maka dibuat request permission agar diberikan access
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 5558)
        }
        // mengambil state dari external storage
        var status = Environment.getExternalStorageState()
        // jika storage sama dengan media mount dan media mount read only maka hasilnya true
        if (Environment.MEDIA_MOUNTED.equals(status) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(
                status
            )
        ) {
            return true
        }
        // jika tidak maka return false
        return false
    }

    // memnbuat fungsi requestcode
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        // ketika requestcode 5558 maka akan dilanjutkan dengan jika grantresult tidak kosong dan grantresult ke 0
        // sudah diberikan permission maka keluar toast permission is granted dan sebaliknya jika belum maka
        // akan keluar permission is denied
        when (requestCode) {
            5558 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission is Granted", Toast.LENGTH_SHORT).show()
                else {
                    Toast.makeText(this, "Permission is Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun clearDataLogin() {
        inputEmail.text?.clear()
        inputPass.text?.clear()
    }

    //fungsi untuk menload sound pool
    override fun onStart() {
        //membaca data
        super.onStart()
        //untuk versi yang sudah diatas lolipop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //memanggil fungsi untuk membuat soundpool
            createNewSoundPool()
        }
        //untuk versi lama
        else {
            //memanggil fungsi untuk membuat soundpool
            createOldSoundPool()
        }
        //Menload sound yang akan dipakai dan set prioritas
        soundIDplayer = sound?.load(this, R.raw.transitiontoberanda, 1) ?: 0
    }

    private fun createOldSoundPool() {
        //untuk Membuat SoundPool dengan maxstream,type stream dan quality
        sound = SoundPool(15, AudioManager.STREAM_MUSIC, 0)
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

    fun cekvalid(validasi: Array<Int>) {
        if (validasi[0] == 1 && validasi[1] == 1) {
            login_button.isEnabled = true
        } else {
            login_button.isEnabled = false
        }
    }

    //Intent Eksplisit
    //fungsi untuk keluar kehalaman Registrasi
    fun gotoRegis(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)

    }


    private fun delFile() {
        if (fileList().size != 0) {
            for (i in fileList())
                deleteFile(i)
        }
    }

    //fungsi untuk keluar kehalaman Forgot Password
    fun forgotpass_login(view: View) {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
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

    private fun showNotifReminder() {
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
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

        // membuat pending intent guna untuk memberikan intent content pada notifikiasi nantinya
        // pending intent ii berisi intent yang sebelumnya telah kita buat yaitu  notifyBookmarkIntent
        val myPendingIntent = PendingIntent.getActivity(
            this, 0,
            notifyBookmarkIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        );

        // pembuatan variable notifikiasi yang dan diisi ContentIntentnya menjadi myPendingIntent
        // untuk menghandle ketika diclick ada intent yang akan di triger dan mengarahkannya kepada bookmarkedactivity
        var myNotification = NotificationCompat.Builder(this, "Reminder_Promosi")
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
        //notificationManager?.notify(100, myNotification.build())
    }


}