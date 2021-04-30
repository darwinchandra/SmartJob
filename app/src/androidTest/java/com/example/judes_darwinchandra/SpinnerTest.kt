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

        // init myarray untuk mengambil data dari source lable array
        var myArray = activityTestRule.getActivity().getResources()
            .getStringArray(R.array.label_array)

        // menginit size berisikan array
        val size = myArray.size
        // perulangan sampai array ke size
        for (i in 0 until size) {
            //temukan spinnernya dan klik
            onView(withId(R.id.spinnerSpecial)).perform(click())
            // data klik 1 per 1 dikarenakan di dalam perulangan
            onData(`is`(myArray[i])).perform(click())
            // mengecek apakah sudah betul text spec cocok dengan myarray
            onView(withId(R.id.spec)).check(matches(withText(myArray[i])))
        }
    }
}