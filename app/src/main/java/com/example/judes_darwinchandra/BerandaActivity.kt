package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.judes_darwinchandra.adapter.postsAdapter1
import com.example.judes_darwinchandra.adapter.postsAdapter2
import kotlinx.android.synthetic.main.activity_beranda.*
import kotlinx.android.synthetic.main.fragment_beranda.*

class BerandaActivity : AppCompatActivity() {


    lateinit var msgFragment: MsgFragment
    lateinit var berandaFragment: BerandaFragment
    lateinit var jobsFragment: JobsFragment
    lateinit var profileFragment: ProfileFragment
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
        supportActionBar?.hide() // hide the title bar

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        //Membuat fragment yang akan menanmpung fragment lainnya
        berandaFragment=BerandaFragment()
        //membuat transaksi manager untuk halaman yang pertama
        supportFragmentManager
            .beginTransaction()
            // replace framelayout dengan halaman berandafragment
            .replace(R.id.frame_layout,berandaFragment)
            // set transaksinya
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            //jalankan fragmentnya
            .commit()

        //ketika menekan bottom navigasi yang terdiri dari beberapa tombol tersebut akan menuju kefragmentnya.
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            //ketika item diklik
            when(item.itemId) {

                R.id.home_page -> {
                    //membuat fragment yang akan menampung beranda
                    berandaFragment=BerandaFragment()
                    supportFragmentManager
                            //ketika pas di klik akan terjadi transaksi dari beranda,message,jobs,profile ke beranda itu sendiri
                        .beginTransaction()
                            // dan framelayout akan direplace fragment beranda itu sendiri
                        .replace(R.id.frame_layout,berandaFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                //fragment messages
                R.id.msg_page -> {
                    //membuat fragment yang akan menampung message
                    msgFragment=MsgFragment()
                    supportFragmentManager
                        //ketika pas di klik akan terjadi transaksi dari beranda,profile,jobs ke message
                        .beginTransaction()
                        // dan framelayout akan direplace fragment message
                        .replace(R.id.frame_layout,msgFragment)
                        //set fragment message untuk diopen
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        //dan jalankan
                        .commit()
                    //jika button message yang diklik
                    true
                }
                //fragment halaman jobs
                R.id.job_page -> {
                    //membuat fragment yang akan menampung jobs
                    jobsFragment= JobsFragment()
                    supportFragmentManager
                        //ketika pas di klik akan terjadi transaksi dari beranda,message,profile ke jobs
                        .beginTransaction()
                        // dan framelayout akan direplace fragment jobs
                        .replace(R.id.frame_layout,jobsFragment)
                        //set fragment jobs untuk diopen
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        //dan jalankan
                        .commit()
                    //jika button message yang diklik
                    true
                }
                //fragment profile
                R.id.profile_page -> {
                    //membuat fragment yang akan menampung profile
                    profileFragment=ProfileFragment()
                    supportFragmentManager
                        //ketika pas di klik akan terjadi transaksi dari beranda,message,jobs ke profile
                        .beginTransaction()
                        // dan framelayout akan direplace fragment profile
                        .replace(R.id.frame_layout,profileFragment)
                        //set fragment profile untuk diopen
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        //dan jalankan
                        .commit()
                    //jika button message yang diklik
                    true
                }
                //diluar itu maka error
                else -> false
            }
        }
        //tombol button ketika diklik ulang maka akan tetap atau diarahkan kefragment lain
        bottom_navigation.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.home_page -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.msg_page -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.job_page -> {
                    // Respond to navigation item 3 click
                    true
                }
                R.id.profile_page -> {
                    // Respond to navigation item 4 click
                    true
                }
                //diluar itu salah.
                else -> false
            }
        }

        // variabe wifiManager sebagai receiver
        var wifiManager = MyWifiStateReceiver()
        // filterwifi disini bergungsi sebegai filter yang membuat receiver dapat bekerja,
        // ketika terdapat aksi perubahan status wifi
        var filterWifi= IntentFilter()
        filterWifi.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        // register receiver dengan filter yang telah dibuat sebelumnya
        registerReceiver(wifiManager,filterWifi)



    }
}













