package com.example.judes_darwinchandra

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.judes_darwinchandra.ManageProfilActivity.Companion.buildToastMessage
import kotlinx.android.synthetic.main.my_custom_dialog.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DialogTest {
    //@Rule membuat konteks untuk kode pengujian
    @Rule
    @JvmField
    //ActivityTestRule menyediakan pengujian fungsional Aktivitas (MainActivity::class)
    var activityTestRule =ActivityTestRule(BerandaActivity::class.java)
    //Metode pengujian dimulai dengan anotasi @Test dan berisi kode untuk dijalankan dan memverifikasi satu fungsi dalam
    //komponen yang ingin di uji
    @Test
    //fungsi untuk menguji dari profile page kemudian masuk kedalam manage profile dan mengklik btn manage profile
    fun test_dialog() {
        Espresso.onView(withId(R.id.profile_page)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.profile)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.btn_manage_profil)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.manageprofileact)).check(matches(isDisplayed()))
        //klik tulisan pencil untuk memunculkan kotak dialog
        Espresso.onView(ViewMatchers.withId(R.id.imagePencil3)).perform(ViewActions.click())

        //memasukkan input untuk mengetes
        Espresso.onView(withId(R.id.nama)).perform(typeText("SmartJob"))
        //perform untuk mengklik button ok
        Espresso.onView(ViewMatchers.withId(R.id.ok)).perform(ViewActions.click())
        //memastikan dialog telah selesai
        Espresso.onView(ViewMatchers.withText(R.string.text_enter_name)).check(doesNotExist())
    }


}