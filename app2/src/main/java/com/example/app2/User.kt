package com.example.app2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id :Int = 0,
    var location : String = ""): Parcelable {

}