package com.example.judes_darwinchandra

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// menambahkan @parcelize pada class object
@Parcelize
data class objDetailLoker(var namaPerusahaan: String ="Company", var posisiLoker : String = "Jabatan",
                          var gajiLoker : String="gaji", var alamatPerusahaan:String="JL. CONTOH", var jadwal:String = "Interview : 15-May-21 (10:30 AM)"):Parcelable {
}