package com.example.judes_darwinchandra

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ManageProfile {
    @Rule @JvmField
    var activityTestRule = ActivityTestRule(BerandaActivity::class.java)
    @Test
    fun test_gotomanageprofile(){
        onView(withId(R.id.btn_manage_profil)).perform(click())
        onView(withId(R.id.manageprofile))
            .check(matches(isDisplayed()))
    }
    @Test
    fun test_inActivityView(){
        onView(withId(R.id.manageprofile)).check(matches(
            withText("Manage Profil")
        ))
    }
}