package com.example.judes_darwinchandra

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_video_call.*


class ChangePasswordActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_changePass.setNavigationOnClickListener {
            finish()
        }
    }

    fun forgotpass_changepass(view: View) {
        val intent = Intent(this, ForgotPasswordProfileActivity::class.java)
        startActivity(intent)
    }

    fun ChangePassConfirm(view: View) {
        Toast.makeText(this, "Your password has been changed", Toast.LENGTH_SHORT).show()
        finish()
    }
}