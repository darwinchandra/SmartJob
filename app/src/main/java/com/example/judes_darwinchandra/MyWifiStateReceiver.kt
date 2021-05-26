package com.example.judes_darwinchandra

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.widget.Toast
// membuat receiver baru untuk menhandle ketika terjadi perubahan status wifi on atau off.
class MyWifiStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // membuat variable wifiStateExtra yang menampung status wifi enable atau disable.
        var wifiStateExtra=intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
        // membuat switch case untuk mengembalikan nilai toast saat terjadi perubahan
        when(wifiStateExtra) {
            // menampilkan hasil toast WIFI ON ketika status wifi enabled
            //WifiManager.WIFI_STATE_ENABLED -> Toast.makeText(context, "WIFI ON", Toast.LENGTH_SHORT).show()
            // menampilkan hasil toast WIFI OFF ketika status wifi disabled
            //WifiManager.WIFI_STATE_DISABLED -> Toast.makeText(context, "WIFI OFF", Toast.LENGTH_SHORT).show()
        }
    }
}
