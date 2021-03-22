package com.example.judes_darwinchandra

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.widget.Toast

class MyWifiStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var wifiStateExtra=intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
        when(wifiStateExtra) {
            WifiManager.WIFI_STATE_ENABLED -> Toast.makeText(context, "wifi on", Toast.LENGTH_SHORT)
                .show()
            WifiManager.WIFI_STATE_DISABLED -> Toast.makeText(
                context,
                "wifi off",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
