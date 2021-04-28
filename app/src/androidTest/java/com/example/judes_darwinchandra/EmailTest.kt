package com.example.judes_darwinchandra

import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailTest {
    @Rule
    @JvmField
    //membuka activity
    val activityTestRule = ActivityTestRule(HelpActivity::class.java)
    // sebelum dijalan program dijalankan intents terlebih dulu
    @Before
    fun setUp() {
        Intents.init()
    }
    // membuat fungsi mengirim ke email
    @Test
    fun actionsendtoemail() {
        // mencocok kan intent dari semuanya seperti actionnya dan datanya
        val expectedIntent : org.hamcrest.Matcher<Intent>? =
            AllOf.allOf(
                IntentMatchers.hasAction(Intent.ACTION_SENDTO),
                IntentMatchers.hasData(Uri.parse("mailto:"))
            )
        // diklik untuk menuju ke emailnya
        Espresso.onView(withId(R.id.reportbutton)).perform(ViewActions.click())
        Intents.intended(expectedIntent)
    }
    // sesudah itu intent ditutup
    @After
    fun tearDown() {
        Intents.release()
    }

}