package com.example.judes_darwinchandra

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.judes_darwinchandra.ImageViewHasDrawableMatcher.hasDrawable
import org.hamcrest.Matcher
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
    class CameraTest {
        @get:Rule
        val intentsTestRule = IntentsTestRule(BerandaActivity::class.java)

        @Test
        fun  test_cameraIntent() {
            Espresso.onView(ViewMatchers.withId(R.id.profile_page)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.profile))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.btn_manage_profil)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.manageprofileact))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            // GIVEN
            val activityResult = createImageCaptureActivityResultStub()
            val expectedIntent: Matcher<Intent> = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
            intending(expectedIntent).respondWith(activityResult)

            // Execute and Verify
            onView(withId(R.id.imageProfile)).check(matches(hasDrawable()))
            onView(withId(R.id.camera)).perform(click())
            intended(expectedIntent)
            onView(withId(R.id.imageProfile)).check(matches(hasDrawable()))
        }

        private fun createImageCaptureActivityResultStub(): Instrumentation.ActivityResult? {
            val bundle = Bundle()
            bundle.putParcelable(
                KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                    intentsTestRule.getActivity().getResources(),
                    R.drawable.ic_launcher_background
                )
            )
            val resultData = Intent()
            resultData.putExtras(bundle)
            return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
        }
    }
