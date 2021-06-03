package com.example.judes_darwinchandra

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
// membuat data class location yang terdiri dari id dan location
data class Location (
        var id :Int = 0,
        var location : String = ""): Parcelable{
}