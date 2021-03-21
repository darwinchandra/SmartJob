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

            val emailArrray:Array<String> = arrayOf("181110468@students.mikroskil.ac.id","181110859@students.mikroskil.ac.id","181112213@students.mikroskil.ac.id")
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, emailArrray)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Problem with ...")
            intent.putExtra(Intent.EXTRA_TEXT, "Hi my name is ... \n\n I want to report a problem about ..." +
                    "\n\n Your assistance in this matter would be greatly appreciated " +
                    "\n\n Thanks & Regards")
                startActivity(intent)
        }



    }



}




