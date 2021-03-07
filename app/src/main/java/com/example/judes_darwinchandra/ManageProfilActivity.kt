package com.example.judes_darwinchandra

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.activity_manage_profil.*
import kotlinx.android.synthetic.main.my_custom_dialog.*

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
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_STATUS,textViewnama.text.toString())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textViewnama.text = savedInstanceState?.getString(EXTRA_STATUS) ?: "Kosong"
    }


    fun getCustomDialog(view: View) {
        var Mylayout = layoutInflater.inflate(R.layout.my_custom_dialog,null)
        val mydialogbuilder : AlertDialog.Builder = AlertDialog.Builder(this).apply {
            setView(Mylayout)
            setTitle("Ganti Nama")
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
}