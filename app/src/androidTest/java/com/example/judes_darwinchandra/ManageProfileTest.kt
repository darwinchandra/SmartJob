package com.example.judes_darwinchandra

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ManageProfileTest {
    @Rule @JvmField
    var activityTestRule =ActivityTestRule(BerandaActivity::class.java)
    @Test
    fun test_gotomanageprofile(){
        onView(withId(R.id.profile_page)).perform(click())
        onView(withId(R.id.profile)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_manage_profil)).perform(click())
        onView(withId(R.id.manageprofileact)).check(matches(isDisplayed()))
    }
    @Test
    fun testback_manageprofile(){
        onView(withId(R.id.profile_page)).perform(click())
        onView(withId(R.id.profile)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_manage_profil)).perform(click())
        onView(withId(R.id.manageprofileact)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.berandamain)).check(matches(isDisplayed()))

    }
}