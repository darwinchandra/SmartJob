package com.example.judes_darwinchandra

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.activity_manage_profil.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class LocationActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.gray3)

        // menggunakan fungsi adapter untuk memasukkan di array list
        updateAdapter()

        searchView13.setOnClickListener(View.OnClickListener { searchView13.isIconified = false })
        topAppBar_loca.setNavigationOnClickListener {
            finish()
        }
        //menambahkan onclick pada saat add di tekan maka akan memunculkan kotak dialog
        addlocation.setOnClickListener{
            //fungsi untuk menampilkan kotak dialog
            getCustomDialog()
        }

    }

    // membuat fungsi
    fun updateAdapter(){
        // membuat temp utk menampung fungsi usertransaction
        var temp= locationTransaction(this)
        // melakukan doasync
        doAsync {
            // locallist menampung semua isi data dari app 2 dan ditulis ke arraylist
            var locaList = temp?.viewAllLocation()?.toTypedArray()
            uiThread {
                // jika listview tidak kosong / tidak sama dengan 0 maka listview ditambah dari localist
                if(lv_Location1 != null && locaList?.size != 0){
                    lv_Location1.adapter = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_list_item_1,
                        locaList!!
                    )
                }
            }
        }
    }


    //custom dialog untuk menambahkan kota
    fun getCustomDialog() {
        //menerapkan layout yang telah di buat
        var Mylayout = layoutInflater.inflate(R.layout.customdialogkota, null)
        //membuild custom dialog
        val mydialogbuilder : AlertDialog.Builder = AlertDialog.Builder(this).apply {
            setView(Mylayout)
            setTitle("Masukkan Kota")
        }
        //melakukan create pada dialog yang telah dibuat
        var mydialog = mydialogbuilder.create()
        var kota = Mylayout.findViewById<EditText>(R.id.dialog_ET_kota)
        var Btnok = Mylayout.findViewById<Button>(R.id.dialog_btn_ok_kota)
        //ketika button ok di klik maka dialog akan ditutup
        Btnok.setOnClickListener {
            insertkota(kota.text.toString())
            mydialog.cancel()
        }

        mydialog.show()
    }
    //fungsi untuk menambahkan kota
    fun insertkota(newlocation:String){

        var temp= locationTransaction(this)

        // melakukan doasync
        doAsync {
            // locallist menampung semua isi data dari app 2 dan ditulis ke arraylist
            var locaList = temp?.viewAllLocation()?.toTypedArray()
            var loca = Location()
            loca.id=locaList.size+1
            //menambahkan lokasi baru
            loca.location=newlocation
            //insert lokasi baru kedalam database
            temp?.insertDataLocation(loca)
            uiThread {
                //mengupdate adapter
                updateAdapter()
            }
        }
    }

}