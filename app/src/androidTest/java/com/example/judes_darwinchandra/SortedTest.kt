package com.example.judes_darwinchandra

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SortedTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(FilterActivity::class.java)

    @Test
    fun test_sorted(){

        // klik button ntf dan mengecek textview sudah sama

        onView(withId(R.id.buttonNtF)).perform(click())
        onView(withId(R.id.textView26)).check(matches(withText("Nearest")))
        // klik button Cost dan mengecek textview sudah sama
        onView(withId(R.id.buttonCost)).perform(click())
        onView(withId(R.id.textView26)).check(matches(withText("Low To High")))
        // klik button Ass dan mengecek textview sudah sama
        onView(withId(R.id.buttonAss)).perform(click())
        onView(withId(R.id.textView26)).check(matches(withText("Alphabetic")))


    }
}