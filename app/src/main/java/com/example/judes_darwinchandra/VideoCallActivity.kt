package com.example.judes_darwinchandra

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_video_call.*

class VideoCallActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_call)
        supportActionBar?.hide()

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_videocall.setNavigationOnClickListener {
            finish()
        }
    }

    fun openCam(view: View) {
        cameraPermission()
        var TakePic = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(TakePic.resolveActivity(packageManager) !=null){
            startActivityForResult(TakePic,112)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 112 && resultCode == Activity.RESULT_OK && data!=null){
            var tumbnail = data.extras
        }


    }
    private fun cameraPermission() {
        var permission = arrayOf(Manifest.permission.CAMERA)
        var needPermission : ArrayList<String> = ArrayList()
        for(i in permission){
            if (ContextCompat.checkSelfPermission(this@VideoCallActivity,i) != PackageManager.PERMISSION_GRANTED){
                needPermission.add(i)
            }
        }
        if(!needPermission.isEmpty()){
            ActivityCompat.requestPermissions(this,
                needPermission.toArray(arrayOfNulls(needPermission.size)),
                123)
        }
    }
}