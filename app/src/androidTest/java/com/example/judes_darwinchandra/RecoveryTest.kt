package com.example.judes_darwinchandra



import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.android.synthetic.main.fragment_forgotpassconfirm.*
import kotlinx.android.synthetic.main.fragment_forgotpass.*

@RunWith(AndroidJUnit4::class)
class RecoveryTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_recovery(){
        // click forgot untuk menuju ke fragpass
        Espresso.onView(withId(R.id.forgots)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragpass)).check(matches(isDisplayed()))
            // click di input email dan mengetiknya
            onView(withId(R.id.inputEmailFP1)).perform(typeText("darwinch@gmail.com"))
            // mengclick tombol buttonya
            onView(withId(R.id.btnResetPass)).perform(click())
            //mengecek apakah sudah sama textnya dengan text difragmentnya
            onView(withId(R.id.confirmtextfp)).check((matches(withText("We have sent a recovery link to darwinch@gmail.com"))))
        // mengclick ok untuk keluar dari page
            onView(withId(R.id.btnOkresetpassconfirm)).perform(click())
    }
}