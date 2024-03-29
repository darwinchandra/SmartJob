package com.example.judes_darwinchandra

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
// Penambahan Interface data agar dapat memangil fungsi kirimtext sebagai perantara kirim data antar Fragment
class ForgotPasswordActivity : AppCompatActivity(), InterfaceDataText {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        val fragmentfp=ForgotpassFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_forgotpass,fragmentfp).commit()


        topAppBar_forgotpass.setNavigationOnClickListener {
            finish()
        }
    }
    //overide function kirimText ini telah dapat di overide karena telah menambahkan InterfaceDataText pada activity
    override fun kirimText(TextInputEditText: String) {
        //Implementasi bundle
        val bundle=Bundle()
        bundle.putString("EmailFP",TextInputEditText)
        // memulai transaksi
        val transaksi= this.supportFragmentManager.beginTransaction()
        val fragmentfpConfirm=ForgotpassconfirmFragment()
        //mengisi argument dari fragmentfpconfirm dengan bundle
        fragmentfpConfirm.arguments=bundle

        //menimpa fragementfp dengan fragementfpConfirm
        transaksi.replace(R.id.frame_layout_forgotpass,fragmentfpConfirm)
        transaksi.commit()

        // tambahkan goto email disini

    }


    /*fun reset_pass_email_sent(view: View) {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmailFP1.text.toString()).matches()){
            var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
                .setMessage("Please check your email to recovery your password")
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                })
            dialog.show()
        }
        else if(inputEmailFP1.text.toString().trim().isEmpty()){
            inputEmailFP1.setError("Email can't be empty")
        }
        else{
            inputEmailFP1.setError("Invalid Email")
        }


    }

    fun gotoRegis_forgotpass(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }*/

}