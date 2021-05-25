package com.example.judes_darwinchandra

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.random.Random

class RegisterActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.black)
        setContentView(R.layout.activity_register)


        regis_button.isEnabled=false
        var valid= arrayOf(0,0,0,0)
        inputNamaRegis.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(inputNamaRegis.text.toString().trim().isEmpty()){
                    inputNamaRegis.setError("Name can't be empty")
                    valid[0]=0
                    cekvalid(valid)
                }
                else{
                    valid[0]=1
                    cekvalid(valid)
                }
            }
        })
        inputEmailRegis.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmailRegis.text.toString()).matches()){
                    valid[1]=1
                    cekvalid(valid)
                }
                else if(inputEmailRegis.text.toString().trim().isEmpty()){
                    inputEmailRegis.setError("Email can't be empty")
                    valid[1]=0
                    cekvalid(valid)
                }
                else{
                    inputEmailRegis.setError("Invalid Email")
                    valid[1]=0
                    cekvalid(valid)
                }
            }
        })

        inputPassRegis.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(inputPassRegis.text.toString().trim().isEmpty()){
                    inputPassRegis.setError("Password can't be empty")
                    valid[2]=0
                    cekvalid(valid)
                }
                else{
                    valid[2]=1
                    cekvalid(valid)
                }

                if (inputPassRegis.text.toString()!=inputConfirmPassRegis.text.toString()){
                    valid[3]=0
                    inputConfirmPassRegis.setError("Password didn't match")
                    cekvalid(valid)
                }
                else{
                    inputConfirmPassRegis.setError(null)
                    valid[3]=1
                    cekvalid(valid)
                }
            }
        })
        inputConfirmPassRegis.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(inputConfirmPassRegis.text.toString().trim().isEmpty()){
                    inputConfirmPassRegis.setError("Password can't be empty")
                    valid[3]=0
                    cekvalid(valid)
                }
                else{
                    valid[3]=1
                    cekvalid(valid)
                }

                if (inputPassRegis.text.toString()!=inputConfirmPassRegis.text.toString()){
                    valid[3]=0
                    inputConfirmPassRegis.setError("Password didn't match")
                    cekvalid(valid)
                }
                else{
                    valid[3]=1
                    cekvalid(valid)
                }
            }
        })

        var db= Room.databaseBuilder(
            this,
            MyDBRoomHelper::class.java,
            "myroomdb.db"
        ).build()


        regis_button.setOnClickListener {
            //Database
//            var hasil =""
//            doAsync {
//                db.userDao().insertAll(User(Random.nextInt(), inputNamaRegis.text.toString(), inputEmailRegis.text.toString(),inputPassRegis.text.toString()))
//                for(allData in db.userDao().getAllData()){
//                    hasil += "${allData.nama} ${allData.email} ${allData.password}\n"
//
//                }
//                uiThread {
//                    Log.w("Hasil",hasil)
//                }
//            }
//
//
//
//
//            finish()
//            Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)

            var hasil =""
            var mailLogin = inputEmailRegis.text.toString()
            var passLogin = inputPassRegis.text.toString()
            doAsync {
                var index = db.userDao().validateEmailRegis(mailLogin)
                var valid= index.size


                uiThread {
                    if(valid>0)
                    {
                        inputEmailRegis.requestFocus()
                        Toast.makeText(it,"Email Telah terdaftar" , Toast.LENGTH_SHORT).show()
                        val intent = Intent(it, RegisterActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        db.userDao().insertAll(User(Random.nextInt(), inputNamaRegis.text.toString(), inputEmailRegis.text.toString(),inputPassRegis.text.toString()))
                        for(allData in db.userDao().getAllData()){
                            hasil += "${allData.nama} ${allData.email} ${allData.password}\n"

                        }
                        Toast.makeText(it ,"Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(it, RegisterActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    Log.w("Hasil",hasil)
                }
            }





        }
    }

    fun cekvalid(validasi:Array<Int>){

        if(validasi[0]==1 && validasi[1]==1 && validasi[2]==1&& validasi[3]==1){
            regis_button.isEnabled=true
        }
        else{
            regis_button.isEnabled=false
        }
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }
    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    fun gobacktoLogin(view: View) {
        finish()
    }

}