package com.example.judes_darwinchandra

import android.annotation.SuppressLint
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

        berandaFragment=BerandaFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,berandaFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_page -> {
                    berandaFragment=BerandaFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,berandaFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                R.id.msg_page -> {
                    msgFragment=MsgFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,msgFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                R.id.job_page -> {
                    jobsFragment= JobsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,jobsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                R.id.profile_page -> {
                    profileFragment=ProfileFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                else -> false
            }
        }
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
                    // Respond to navigation item 2 click
                    true
                }
                R.id.profile_page -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }
}