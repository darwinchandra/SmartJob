package com.example.judes_darwinchandra

import android.os.Build
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
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


        fun composeEmail(addresses: Intent, subject: String, attachment: Uri): Intent? {

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/html"
                putExtra(Intent.EXTRA_EMAIL, "example@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Send Email"))
            }
        }
        button3.setOnClickListener(
            startActivity(composeEmail(intent))
        )
    }
}

private fun Button.setOnClickListener(startActivity: Unit) {
    TODO("Not yet implemented")
}


