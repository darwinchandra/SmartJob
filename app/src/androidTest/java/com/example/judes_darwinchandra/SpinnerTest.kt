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
class SpinnerTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(FilterActivity::class.java)

    @Test
    fun test_spinner(){

        var myArray = activityTestRule.getActivity().getResources()
            .getStringArray(R.array.label_array)

        // Iterate through the spinner array of items.
        val size = myArray.size
        for (i in 0 until size) {
            // Find the spinner and click on it.
            onView(withId(R.id.spinnerSpecial)).perform(click())
            // Find the spinner item and click on it.
            onData(`is`(myArray[i])).perform(click())

            onView(withId(R.id.spec)).check(matches(withText(myArray[i])))

            // Find the Submit button and click on it.
//            onView(withId(R.id.spinner)).perform(click())
//            // Find the text view and check that the spinner item
//            // is part of the string.
//            onView(withId(R.id.spinner))
//                .check(matches(withSpinnerText(containsString(myArray[i]))))
        }
    }
}