package com.example.judes_darwinchandra

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class objDetailLoker(var namaPerusahaan: String ="Company", var posisiLoker : String = "Jabatan",
                          var gajiLoker : String="gaji", var alamatPerusahaan:String="JL. CONTOH"):Parcelable {
}