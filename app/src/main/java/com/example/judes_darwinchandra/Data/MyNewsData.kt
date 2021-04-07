package com.example.judes_darwinchandra.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyNewsData (val author:String, val title:String,
                       val description:String,var url:String,val urlToImage:String,val publishedAt:String,val content:String) :
    Parcelable{

}