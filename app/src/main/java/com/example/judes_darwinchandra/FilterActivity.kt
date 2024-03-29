package com.example.judes_darwinchandra

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.iterator
import com.example.judes_darwinchandra.adapter.postsAdapterInterview
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_filter.spinnerSpecial

class FilterActivity : AppCompatActivity() {


    //init notifikasi managernya
    var notificationManager: NotificationManager? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_filter.setNavigationOnClickListener {
            finish()
        }
        topAppBar_filter.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.reset -> {
                    Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        //gunakan arrayadapter untuk menampung isi array data spinner diatas
        val myAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.label_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerSpecial.adapter = adapter
            spinnerSpecial.onItemSelectedListener = object :

                AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    spec.text = spinnerSpecial.getItemAtPosition(p2).toString()
                }

            }
        }


            buttonNtF.setOnClickListener{
                buttonNtF.setBackgroundColor(Color.YELLOW)
                buttonCost.setBackgroundColor(Color.WHITE)
                buttonAss.setBackgroundColor(Color.WHITE)
            textView26.text = "Nearest"
            }



            buttonCost.setOnClickListener{
        buttonCost.setBackgroundColor(Color.YELLOW)
        buttonNtF.setBackgroundColor(Color.WHITE)
        buttonAss.setBackgroundColor(Color.WHITE)
            textView26.text = "Low To High"
        }



            buttonAss.setOnClickListener{
            textView26.text = "Alphabetic"
                buttonAss.setBackgroundColor(Color.YELLOW)
                buttonNtF.setBackgroundColor(Color.WHITE)
                buttonCost.setBackgroundColor(Color.WHITE)
        }


        //adapter ini membuat dropdown view yang isinya dari dataspinner

        //masukkan adapter ke spinner
        spinnerSpecial!!.setAdapter(myAdapter)
        //notifikasi manager getsystem notif service untuk mendapatkan notifikasi
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        //membuat variabel baru untuk mengcreate notif
        val mynotification = NotificationManager()
        //membuat notifikasi grup
        mynotification.createNotificationGroups(notificationManager!!)
        //membuat notifikasi channel
        mynotification.createNotificationChannels(notificationManager!!)


        buttonApply.setOnClickListener{
            //membuat variable channel id
            var channel_id = ""
            // ketika build versi sdk lebih besar atau sama dengan build kode versi O
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //channel_id mendapatkan notifikasi dari filter spesialis grup Umum
                channel_id = notificationManager!!.getNotificationChannel(
                    "Filter Spesialis_Umum"
                ).id
            }
            //membuat notifikasi yang menampung channel_id
            var myNotification1 = NotificationCompat.Builder(this, channel_id)
                    //membuat title
                .setContentTitle("Filter Applied")
                    //membuat isi text
                .setContentText("Pekerjaan yang anda inginkan " + spinnerSpecial.selectedItem.toString())
                    //berada digrup umum
                .setGroup("Umum")
                    //membuat icon
                .setSmallIcon(R.drawable.job_icon)
            // memunculkan notifikasinya
            notificationManager?.notify(NOTIFICATION_UMUM,myNotification1.build())

            finish()
        }
    }


    fun NearToFar(view: View) {
        buttonNtF.setBackgroundColor(Color.YELLOW)
        buttonCost.setBackgroundColor(Color.WHITE)
        buttonAss.setBackgroundColor(Color.WHITE)
    }
    fun cost(view: View) {
        buttonNtF.setBackgroundColor(Color.WHITE)
        buttonCost.setBackgroundColor(Color.YELLOW)
        buttonAss.setBackgroundColor(Color.WHITE)
    }
    fun Ascending(view: View) {
        buttonNtF.setBackgroundColor(Color.WHITE)
        buttonCost.setBackgroundColor(Color.WHITE)
        buttonAss.setBackgroundColor(Color.YELLOW)
    }



}
