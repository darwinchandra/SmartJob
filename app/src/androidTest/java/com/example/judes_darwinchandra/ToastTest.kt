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
class ToatTest {
    @Rule
    @JvmField
    //val activityScenario = ActivityScenario.launch(BerandaActivity::class.java)
    var activityTestRule = ActivityTestRule(BerandaActivity::class.java)
    @Test
    fun test_dialog() {
        Espresso.onView(ViewMatchers.withId(R.id.profile_page)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.profile)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_manage_profil)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.manageprofileact)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.imagePencil3)).perform(ViewActions.click())

        //memasukkan input untuk mengetes
        Espresso.onView(ViewMatchers.withId(R.id.nama)).perform(ViewActions.typeText("SmartJob"))
        Espresso.onView(ViewMatchers.withId(R.id.ok)).perform(ViewActions.click())
        //memastikan dialog telah selesai
        Espresso.onView(ViewMatchers.withText(R.string.text_enter_name)).check(ViewAssertions.doesNotExist())

        //test jika toast telah muncul
        Espresso.onView(ViewMatchers.withText(ManageProfilActivity.buildToastMessage("SmartJob")))
            .inRoot(ToastMatcher())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}