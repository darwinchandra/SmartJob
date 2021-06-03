package com.example.app2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
// membuat data dari location
data class Location(
    var id :Int = 0,
    var location : String = ""): Parcelable {

}