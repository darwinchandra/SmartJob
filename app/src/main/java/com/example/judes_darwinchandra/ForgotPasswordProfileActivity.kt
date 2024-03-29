package com.example.judes_darwinchandra

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password_profile.*

class ForgotPasswordProfileActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_profile)
        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_forgotpassProfile.setNavigationOnClickListener {
            finish()
        }
    }

    fun reset_pass_email_sent_profile(view: View) {
        var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        .setMessage("Please check your email to recovery your password")
        .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })
        dialog.show()

        // tambahkan notif goto email disini
    }
}