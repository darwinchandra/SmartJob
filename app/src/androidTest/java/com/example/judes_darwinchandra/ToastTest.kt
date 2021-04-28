package com.example.judes_darwinchandra

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastTest {
    //@Rule membuat konteks untuk kode pengujian
    @Rule
    @JvmField
    //ActivityTestRule menyediakan pengujian fungsional Aktivitas (MainActivity::class)
    var activityTestRule =ActivityTestRule(ManageProfilActivity::class.java)
    //Metode pengujian dimulai dengan anotasi @Test dan berisi kode untuk dijalankan dan memverifikasi satu fungsi dalam
    //komponen yang ingin di uji
    @Test
    //fungsi untuk menguji
    fun test_dialog() {
        /*//perform untuk mengklik profle page
        Espresso.onView(ViewMatchers.withId(R.id.profile_page)).perform(ViewActions.click())
        //untuk mengecek apakah kesamaan
        Espresso.onView(ViewMatchers.withId(R.id.profile)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //perform untuk menklik btn manage profile
        Espresso.onView(ViewMatchers.withId(R.id.btn_manage_profil)).perform(ViewActions.click())*/
        //untuk mengecek apakah activity yang dituju sudah sesuai
        Espresso.onView(ViewMatchers.withId(R.id.manageprofileact)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        //perform klik pada image pencil 3 untuk mengeluarkan dialog
        Espresso.onView(ViewMatchers.withId(R.id.imagePencil3)).perform(ViewActions.click())

        //memasukkan input untuk mengetes
        Espresso.onView(ViewMatchers.withId(R.id.nama)).perform(ViewActions.typeText("SmartJob"))
        //perform untuk mengklik button ok
        Espresso.onView(ViewMatchers.withId(R.id.ok)).perform(ViewActions.click())
        //memastikan dialog telah selesai
        Espresso.onView(ViewMatchers.withText(R.string.text_enter_name)).check(ViewAssertions.doesNotExist())

        //test jika toast telah muncul
        Espresso.onView(ViewMatchers.withText(ManageProfilActivity.buildToastMessage("SmartJob")))
            .inRoot(ToastMatcher())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}