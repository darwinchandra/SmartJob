package com.example.judes_darwinchandra

import android.os.Build
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_SENDTO
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_help.setNavigationOnClickListener {
            finish()
        }

        //Intent Implisit
        //Ketika report button diclick maka akan disuruh memilih dan pilih email atau gmail dan langsung diarahkan kesana
        // dan semua data sudah terisi
        reportbutton.setOnClickListener {

            var uriEmail = Uri.parse("mailto: 181110859@students.mikroskil.ac.id")
            var intent = Intent(ACTION_SEND,uriEmail);
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_EMAIL, "mailto: 181110859@students.mikroskil.ac.id")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Problem with")
            intent.putExtra(Intent.EXTRA_TEXT, "Hi my name is ... I want to report a problem about ..." +
                    "Your assistance in this matter would be greatly appreciated " +
                    "Thanks & Regards")
            startActivity(intent)
        }



    }



}




