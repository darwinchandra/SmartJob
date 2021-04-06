package com.example.judes_darwinchandra

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.judes_darwinchandra.R.layout.activity_detail_pekerjaan
import kotlinx.android.synthetic.main.activity_detail_pekerjaan.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_manage_profil.*
import kotlinx.android.synthetic.main.dialogapply.*
import kotlinx.android.synthetic.main.dialogapply.ok1
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.my_custom_dialog.*
import kotlinx.android.synthetic.main.postapplied.*
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
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_detailKerja.setNavigationOnClickListener {
            finish()
        }

        icon_perusahaan_detailkerja.clipToOutline = true


        // di DetailPekerjaanActivity tinggal mengubah isi dari masing" Text box sesuai dengan data yang telah dikirimkan.
        var dataPerusahaan=intent.getParcelableExtra<objDetailLoker>(EXTRA_DETAIL_LOKER)
        jabatan_pegawai_detail.text=dataPerusahaan?.posisiLoker
        string_salary_detail.text=dataPerusahaan?.gajiLoker
        lokasi_perusahaan_detail.text=dataPerusahaan?.alamatPerusahaan



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

    fun custom_apply(view: View) {
        var Mylayout = layoutInflater.inflate(R.layout.dialogapply,null)
        val mydialogbuilder : AlertDialog.Builder = AlertDialog.Builder(this).apply {
            setView(Mylayout)
            setTitle("Notification")
        }
        var mydialog = mydialogbuilder.create()
        var nama = Mylayout.findViewById<TextView>(R.id.nama1)
        var Btnok = Mylayout.findViewById<Button>(R.id.ok1)
        Btnok.setOnClickListener {
            textViewnama.text=nama.text

            mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            buttonApply2.setOnClickListener {
                if(mPendingIntent!=null){
                    mAlarmManager?.cancel(mPendingIntent)
                    mPendingIntent?.cancel()
                }
                var alarmTimer = Calendar.getInstance()

                alarmTimer.set(2021,3,5,21,6,0)
                Log.w("Ok", "${alarmTimer.time}")
                sendIntent = Intent(this, MyReceiver::class.java)
                sendIntent?.putExtra(EXTRA_PESAN,alarmTimer.toString())

                mPendingIntent = PendingIntent.getBroadcast(this,101,sendIntent,0)

//            mAlarmManager?.set(AlarmManager.RTC,alarmTimer.timeInMillis,mPendingIntent)
                mAlarmManager?.setInexactRepeating(AlarmManager.RTC,alarmTimer.timeInMillis,
                    AlarmManager.INTERVAL_FIFTEEN_MINUTES,mPendingIntent)
                Toast.makeText(this,"Scheduler Di Aktifkan",Toast.LENGTH_SHORT).show()
            }
            ok1.setOnClickListener {
                if(mPendingIntent!=null){
                    mAlarmManager?.cancel(mPendingIntent)
                    mPendingIntent?.cancel()
                    Toast.makeText(this,"Scheduler Di matikan",Toast.LENGTH_SHORT).show()
                }

            }
            mydialog.cancel()
        }
        mydialog.show()
    }


}
