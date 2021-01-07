package com.example.judes_darwinchandra

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_forgot_password.*

class FilterActivity : AppCompatActivity() {
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