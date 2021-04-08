package com.example.judes_darwinchandra

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.judes_darwinchandra.R.layout.activity_detail_pekerjaan
import kotlinx.android.synthetic.main.activity_detail_pekerjaan.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_manage_profil.*
import kotlinx.android.synthetic.main.dialogapply.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.my_custom_dialog.*
import kotlinx.android.synthetic.main.postapplied.*
import java.sql.BatchUpdateException
import java.util.*

const val EXTRA_DETAIL_LOKER="EXTRA_DETAIL_LOKER"

class DetailPekerjaanActivity : AppCompatActivity() {
    private var mPendingIntent: PendingIntent? = null
    private var sendIntent: Intent? = null
    private var mAlarmManager: AlarmManager? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_pekerjaan)

        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.gray3)

        topAppBar_detailKerja.setNavigationOnClickListener {
            finish()
        }

        icon_perusahaan_detailkerja.clipToOutline = true


        // di DetailPekerjaanActivity tinggal mengubah isi dari masing" Text box sesuai dengan data yang telah dikirimkan.
        var dataPerusahaan=intent.getParcelableExtra<objDetailLoker>(EXTRA_DETAIL_LOKER)
        jabatan_pegawai_detail.text=dataPerusahaan?.posisiLoker
        string_salary_detail.text=dataPerusahaan?.gajiLoker
        lokasi_perusahaan_detail.text=dataPerusahaan?.alamatPerusahaan

        // mereplace layout menggunakan inflate untuk mengambil dialogapply
        var Mylayout = layoutInflater.inflate(R.layout.dialogapply, null)
        // membuat alertdialog untuk popup yang menunjukan dont notify jika anda mau
        val mydialogbuilder: AlertDialog.Builder = AlertDialog.Builder(this).apply {
            // menggunakan layout yang sudah dibuat
            setView(Mylayout)
            // membuat judul
            setTitle("Notification")

        }
        // builder dibuat
        var mydialog = mydialogbuilder.create()

        var DontNotify = Mylayout.findViewById<Button>(R.id.dontnotify)
        //ketika buttonapply2 di klik maka akan keluar builder yang sudah dibuat dan notif schedule dijalankan
    buttonApply2.setOnClickListener {
        // builder ditampilkan
        mydialog.show()
        // alarm manager bekerja sebagai service sehingga dapat di jalankan walaupun aplikasi dalam keadaan tertutup
        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // jika mpending tidak kosong
        if (mPendingIntent != null) {
            // alarm dibatalkan dan pending juga dibatalkan
            mAlarmManager?.cancel(mPendingIntent)
            mPendingIntent?.cancel()
        }
        // membuat var yang menampung isi calender
        var alarmTimer = Calendar.getInstance()
        // alarm diset dari perusahaan dan akan dikirim tepat pada waktunya atau harinya
        alarmTimer.set(2021, 3, 7, 9, 19, 0)
        Log.w("Ok", "${alarmTimer.time}")
        // menerima notifkasi dari myreceive
        sendIntent = Intent(this, MyReceiver::class.java)
        // menerima pesan notif dari perusahaan

        // menerima broadcast
        mPendingIntent = PendingIntent.getBroadcast(this, 101, sendIntent, 0)
        mAlarmManager?.set(AlarmManager.RTC,alarmTimer.timeInMillis,mPendingIntent)

        // membuat toast berisikan bahwa sudah hidup notifikasi schedulenya
        Toast.makeText(this, "Scheduler On", Toast.LENGTH_SHORT).show()
    }
        val btn = Mylayout.findViewById<Button>(R.id.ok1)
        // jika ok tidak terjadi apa-apa
        btn?.setOnClickListener{
            mydialog.cancel()
        }
            // ketika btnok diklik maka notifikasi schedule akan dimatikan
                DontNotify?.setOnClickListener {
                    if(mPendingIntent!=null) {
                        mAlarmManager?.cancel(mPendingIntent)
                        mPendingIntent?.cancel()
                        Toast.makeText(this, "Scheduler Off", Toast.LENGTH_SHORT).show()
                    }
                    //builder ditutup
                    mydialog.cancel()
                }







    }

    fun descklik(view: View) {
        desc.setTextColor(Color.BLACK)
        reviews.setTextColor(Color.GRAY)
        company.setTextColor(Color.GRAY)
        teks.text="Description"

    }
    fun reviewklik(view: View) {
        reviews.setTextColor(Color.BLACK)
        desc.setTextColor(Color.GRAY)
        company.setTextColor(Color.GRAY)
        teks.text="Review"
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun companyklik(view: View) {
        company.setTextColor(Color.BLACK)
        desc.setTextColor(Color.GRAY)
        reviews.setTextColor(Color.GRAY)
        teks.text="Company"
    }




}


