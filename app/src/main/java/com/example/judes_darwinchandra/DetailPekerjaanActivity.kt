package com.example.judes_darwinchandra

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_detail_pekerjaan.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.postapplied.*

class DetailPekerjaanActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pekerjaan)

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

}