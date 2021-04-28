package com.example.judes_darwinchandra

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_TIME
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_manage_profil.*
import kotlinx.android.synthetic.main.my_custom_dialog.*
import java.io.IOException
import java.util.*


//inisialisasi konstanta untuk menampung data yang akan di tampilkan saat di rotate
private const val EXTRA_STATUS = "EXTRA_STATUS"
var mPendingIntent: PendingIntent? = null
var sendIntent: Intent? = null
var mAlarmManager: AlarmManager? = null
private val TAG: String = "AppDebug"
class ManageProfilActivity : AppCompatActivity() {
    //Inisialisasi notification manager
    var notificationManager : android.app.NotificationManager? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    //Membuat Object Download Receiver untuk menangkap broadcast dari sendbroadcast
    private val downloadReceiver = object : BroadcastReceiver(){
        //onReceive
        override fun onReceive(p0: Context?, p1: Intent?) {
            //untuk mengecek sudah berapa persen
            var persen = p1?.getIntExtra(EXTRA_PERSEN,0)
            //untuk mengecek apakah sudah selesai apa belum, jika belum ada data kirimkan true
            var finish= p1?.getBooleanExtra(EXTRA_FINISH,true)
            //progress nya sesuai dengan sudah berapa persen progress berjalan jika tidak ada maka 0
            progressapp.progress = persen ?: 0
            //cek apakah sudah selesai apa belum
            if(finish!!){
                //jika sudah selesai maka tampilkan toast "Download Selesai"
                Toast.makeText(this@ManageProfilActivity,"Download Selesai",Toast.LENGTH_SHORT).show()

                // Inisialisasi channel ID
                var CHANNEL_ID = ""
                // kondisi untuk mengecek apakah versi android Oreo keatas atau tidak
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //jika merupakan versi android O keatas maka memerlukan Channel
                    //CHANNEL_ID diambil dari namachannel dan grup dari channel tersebut
                    CHANNEL_ID = notificationManager!!.getNotificationChannel("Download_Umum").id
                }
                //Untuk mendapatkan layout yang sudah di buat
                val notificationLayout = RemoteViews(packageName,R.layout.customnotif)
                //Membuat Notifikasi
                var builder = NotificationCompat.Builder(this@ManageProfilActivity,CHANNEL_ID)
                    //Menentukan Judul
                    .setContentTitle("Your Title")
                    //Menentukan ISI
                    .setContentText("Your Text")
                    //Menentukan Group dari notifikasi
                    .setGroup("Umum")
                    //Memberikan Icon pada notifikasi
                    .setSmallIcon(R.drawable.download)
                    //Menentukan Style
                    .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    //Menentukan Custom Layout yang telah dibuat
                    .setCustomContentView(notificationLayout)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //Memunculkan notiifikasi dengan ID
                notificationManager?.notify(NOTIFICATION_PROMOSI,builder.build())

            }
            //Agar buttonletter tidak bisa ditekan saat download berjalan
            btnappletter.isEnabled=finish?:false
        }

    }
    //

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_profil)
        supportActionBar?.hide()

        imagePencil3.setOnClickListener{
            getCustomDialog()
        }

        //notifikasi bekerja sebagai service sehingg dapat di jalankan walaupun aplikasi dalam keadaan tertutup
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        val notifmanage = NotificationManager()
        //mendaftarkan notifikasi Group kedalam notifikasi manager
        notifmanage.createNotificationGroups(notificationManager!!)
        //mendaftarkan notifikasi channel kedalam notifikasi manager
        notifmanage.createNotificationChannels(notificationManager!!)


        //memanggil download service menggunakan intent
        var dokumenService = Intent(this,DownloadService::class.java)
        //onclik pada btnappletter agar ketika diklik proses download berjalan
        btnappletter.setOnClickListener{
            dokumenService.putExtra(EXTRA_TIME,500)
            //memanggil service menggunakan enquequeWork
            DownloadService.enqueueWork(this,dokumenService)
            //filter download untuk menerima ACTION_DOWNLOAD agar service dan broadcast bisa saling berinteraksi
            var filterDownload = IntentFilter(ACTION_DOWNLOAD)
            //yang merespon adalah downloadReceiver
            registerReceiver(downloadReceiver,filterDownload)


            var CHANNEL_ID = ""
            // kondisi untuk mengecek apakah versi android Oreo keatas atau tidak
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                //jika merupakan versi android O keatas maka memerlukan Channel
                //CHANNEL_ID diambil dari namachannel dan grup dari channel tersebut
                CHANNEL_ID = notificationManager!!.getNotificationChannel("Download_Umum").id
            }
            //Untuk mendapatkan layout yang sudah di buat
            val notificationLayout = RemoteViews(packageName,R.layout.notifongoing)
            //Membuat Notifikasi
            var builder = NotificationCompat.Builder(this@ManageProfilActivity,CHANNEL_ID)
                //Menentukan Judul
                .setContentTitle("Your Title")
                //Menentukan ISI
                .setContentText("Your Text")
                //Menentukan Group dari notifikasi
                .setGroup("Umum")
                //Memberikan Icon pada notifikasi
                .setSmallIcon(R.drawable.download)
                //Menentukan Style
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                //Menentukan Custom Layout yang telah dibuat
                .setCustomContentView(notificationLayout)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //onGOing merupakan fungsi untuk membuat notifikasi tidak dapat dihapus
            builder.setOngoing(true)
            //Memunculkan notiifikasi dengan ID
            notificationManager?.notify(NOTIFICATION_PROMOSI,builder.build())
        }


        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)


        val viewimg = findViewById<ImageView>(R.id.imageProfile)
        Thread(Runnable{
            val bitmap = processBitMap(R.drawable.wanita)
            viewimg.post{
                println("Menambahkan Gambar")
                viewimg.setImageBitmap(bitmap)
            }
        }).start()

        topAppBar_ManageProfil.setNavigationOnClickListener {
            finish()
        }
        imageProfile.setOnClickListener{
            pickFromGallery()
        }
        camera.setOnClickListener{
            dispatchCameraIntent()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            Log.d(TAG, "RESULT_OK")

            when(requestCode){

                GALLERY_REQUEST_CODE -> {
                    Log.d(TAG, "GALLERY_REQUEST_CODE detected.")
                    data?.data?.let { uri ->
                        Log.d(TAG, "URI: $uri")
                        Glide.with(this)
                            .load(uri)
                            .into(imageProfile)
                    }
                }
            }
        }
        else{
            Log.d(TAG, "RESULT_OK")
            when(requestCode){

                REQUEST_IMAGE_CAPTURE -> {
                    Log.d(TAG, "REQUEST_IMAGE_CAPTURE detected.")
                    data?.extras.let{ extras ->
                        if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                            return
                        }
                        val imageBitmap = extras[KEY_IMAGE_DATA] as Bitmap?
                        imageProfile.setImageBitmap(imageBitmap)
                    }
                }
            }
        }

    }




    private fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    //untuk mengunregisterReceiver supaya tidak memakan banyak memori
    override fun onDestroy() {
        super.onDestroy()
        try {
            unregisterReceiver(downloadReceiver)
            //Register or UnRegister your broadcast receiver here
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    //mengoveride onsaveinstancestate untuk mengambil text pada textview untuk disimpan pada konstanta
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_STATUS,textViewnama.text.toString())
    }
    //mengoveride onrestoreinstancestate untuk menampilkan text yang disimpan pada konstanta untuk ditampilkan kembali di textview
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textViewnama.text = savedInstanceState?.getString(EXTRA_STATUS) ?: "Kosong"
    }
//membuat function onclick getcustomdialog agar bisa menampilkan custom dialog saat tombol pencil disebelah nama ditekan
    fun getCustomDialog() {
        var Mylayout = layoutInflater.inflate(R.layout.my_custom_dialog,null)
        val mydialogbuilder : AlertDialog.Builder = AlertDialog.Builder(this).apply {
            setView(Mylayout)
            setTitle("Masukkan")
        }
        var mydialog = mydialogbuilder.create()
        var nama = Mylayout.findViewById<EditText>(R.id.nama)
        var Btnok = Mylayout.findViewById<Button>(R.id.ok)
        Btnok.setOnClickListener {
            textViewnama.text=nama.text
            mydialog.cancel()
            //Menampilkan Toast
            showToast(buildToastMessage(nama.text.toString()))
        }

        mydialog.show()
    }

    private fun processBitMap(url: Int): Bitmap? {
        return try{
            var mybitmap = BitmapFactory.decodeResource(resources,url)
            //proses bitmap yang lama
            mybitmap
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
    }
    //Fungsi Show Toast untuk menampilkan toast ketika btn ok diklik
    private fun showToast(message:String){
        //membuat Toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    companion object{
        //fungsi untuk membuat message dari Toast
        fun buildToastMessage(name:String):String{
            return "Nama Anda Adalah $name"
        }
    }
}