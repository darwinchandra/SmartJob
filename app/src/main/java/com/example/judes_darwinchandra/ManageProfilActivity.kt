package com.example.judes_darwinchandra

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.activity_manage_profil.*
import kotlinx.android.synthetic.main.my_custom_dialog.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

//inisialisasi konstanta untuk menampung data yang akan di tampilkan saat di rotate
private const val EXTRA_STATUS = "EXTRA_STATUS"
class ManageProfilActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_profil)
        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_ManageProfil.setNavigationOnClickListener {
            finish()
        }
        val viewimg = findViewById<ImageView>(R.id.imageProfile)
        Thread(Runnable{
            val bitmap = processBitMap(R.drawable.wanita)
            viewimg.post{
                println("Menambahkan Gambar")
                viewimg.setImageBitmap(bitmap)
            }
        }).start()
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
    fun getCustomDialog(view: View) {
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
}