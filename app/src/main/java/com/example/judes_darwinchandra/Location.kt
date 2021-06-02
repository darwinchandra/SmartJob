package com.example.judes_darwinchandra

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Location (
        var id :Int = 0,
        var location : String = ""): Parcelable{
}