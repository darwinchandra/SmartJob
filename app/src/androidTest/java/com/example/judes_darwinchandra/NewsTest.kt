package com.example.judes_darwinchandra

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
//@RunWith menunjukkan runner yang akan digunakan untuk menjalankan pengujian dalam kelas
@RunWith(AndroidJUnit4::class)
class NewsTest {
    //@Rule membuat konteks untuk kode pengujian
    @Rule @JvmField
    //ActivityTestRule menyediakan pengujian fungsional Aktivitas (MainActivity::class)
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    //Metode pengujian dimulai dengan anotasi @Test dan berisi kode untuk dijalankan dan memverifikasi satu fungsi dalam
    //komponen yang ingin di uji
    @Test
    //fungsi untuk menguji ketika kita ke news
    fun GoToNews() {
        //onView untuk mengecek ketika FAB diklik
        Espresso.onView(withId(R.id.floating_action_button)).perform(ViewActions.click())
        //untuk mengecek matches news actvity dengan yang ditampilkan
        Espresso.onView(withId(R.id.newsactivity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    //Mengecek ketika tombol back ditekan
    fun BackFromNews() {
        //onView untuk mengecek ketika FAB diklik
        Espresso.onView(withId(R.id.floating_action_button)).perform(ViewActions.click())
        //untuk mengecek newsactivity
        Espresso.onView(withId(R.id.newsactivity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //ketika tombol back ditekan
        Espresso.pressBack()
        //mengecek main dengan yang ditampilkan
        Espresso.onView(withId(R.id.main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}