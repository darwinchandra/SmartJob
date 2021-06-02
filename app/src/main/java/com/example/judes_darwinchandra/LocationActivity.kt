package com.example.judes_darwinchandra

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.example.judes_darwinchandra.ManageProfilActivity.Companion.buildToastMessage
import kotlinx.android.synthetic.main.activity_invitefriends.*
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.activity_manage_profil.*

class LocationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        searchView13.setOnClickListener(View.OnClickListener { searchView13.isIconified = false })

        var tmp = userTransaction(this)
        var result = ""
        for(str in tmp.viewAllName()){
            result += str + System.getProperty("line.separator")
        }
        textName.text = result
        topAppBar_loca.setNavigationOnClickListener {
            finish()
        }
        addlocation.setOnClickListener{
            getCustomDialog()
        }


    }
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
            showToast(LocationActivity.buildToastMessage(nama.text.toString()))
        }

        mydialog.show()
    }

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