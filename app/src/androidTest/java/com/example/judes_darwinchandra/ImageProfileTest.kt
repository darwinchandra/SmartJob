package com.example.judes_darwinchandra

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageProfileTest {

    class ImageProfileTest {
        @get:Rule
        val intentsTestRule = IntentsTestRule(BerandaActivity::class.java)



        @Test
        fun  test_validateIntentSentToPickPackage() {
            onView(withId(R.id.profile_page)).perform(click())
            onView(withId(R.id.profile)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.btn_manage_profil)).perform(click())
            onView(withId(R.id.manageprofileact)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            // GIVEN
            val expectedIntent: Matcher<Intent> = allOf(
                hasAction(Intent.ACTION_PICK),
                hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            )
            val activityResult = createGalleryPickActivityResultStub()
            intending(expectedIntent).respondWith(activityResult)

            // Execute and Verify
            onView(withId(R.id.imageProfile)).perform(click())
            intended(expectedIntent)
        }
        @Test
        private fun createGalleryPickActivityResultStub(): Instrumentation.ActivityResult {
            val resources: Resources = InstrumentationRegistry.getInstrumentation().context.resources
            val imageUri = Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        resources.getResourcePackageName(R.drawable.ic_launcher_background) + '/' +
                        resources.getResourceTypeName(R.drawable.ic_launcher_background) + '/' +
                        resources.getResourceEntryName(R.drawable.ic_launcher_background)
            )
            val resultIntent = Intent()
            resultIntent.setData(imageUri)
            return Instrumentation.ActivityResult(RESULT_OK, resultIntent)
        }
    }
}