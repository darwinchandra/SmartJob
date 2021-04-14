package com.example.judes_darwinchandra.Data

import android.graphics.Bitmap

//Data class untuk menampung data dari contact
data class myContact(
    var nama : String,
    var nomorHp : String,
    var image : Bitmap? = null
)