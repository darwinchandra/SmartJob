package com.example.judes_darwinchandra

import android.widget.Toast
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.judes_darwinchandra.ManageProfilActivity.Companion.buildToastMessage
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.android.synthetic.main.fragment_forgotpassconfirm.*
import kotlinx.android.synthetic.main.fragment_forgotpass.*

@RunWith(AndroidJUnit4::class)
class ChangePassTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(ChangePasswordActivity::class.java)

    @Test
    fun test_changepassword(){
        Espresso.onView(ViewMatchers.withId(R.id.passchanged)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        // Find the spinner and click on it.
        onView(withId(R.id.newpass)).perform(typeText("wkwka"))
        // Find the spinner item and click on it.
        onView(withId(R.id.confirmpass)).perform(typeText("wkwka"))
        onView(withId(R.id.btn_ConfirmChangePass)).perform(click())
//        onView(ViewMatchers.withText(R.id.NewPass)).check(ViewAssertions.doesNotExist())
//        onView(withId(R.id.newpass)).check(matches(withText(R.id.confirmpass)))

//        onView(withText(R.id.NewPass)).check((matches(withText(R.id.NewPassConfirm))))

//        onView(withId(R.id.confirmpass)).check((matches(withText("wkwka"))))
        onView(withText(ChangePasswordActivity.buildToastMessagePass("darwinch@gmail.com"))).inRoot(ToastMatcher()).check(
            matches(isDisplayed()))

    }
    @Test
    fun test_changepasswordwrong(){
        // Find the spinner and click on it.
        onView(withId(R.id.newpass)).perform(typeText("wkwkswa"))
        // Find the spinner item and click on it.
        onView(withId(R.id.confirmpass)).perform(typeText("wkwkwa"))

        onView(withId(R.id.newpass)).check((matches(withId(R.id.confirmpass))))
        onView(withText(ChangePasswordActivity.buildToastMessagePassWrong())).inRoot(ToastMatcher()).check(
            matches(isDisplayed()))

    }
}