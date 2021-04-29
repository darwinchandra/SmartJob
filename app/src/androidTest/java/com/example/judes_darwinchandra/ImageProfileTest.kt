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
        //@Rule membuat konteks untuk kode pengujian
        @get:Rule
        //ActivityTestRule menyediakan pengujian fungsional Aktivitas (BerandaActivity::class)
        val intentsTestRule = IntentsTestRule(BerandaActivity::class.java)
        //Metode pengujian dimulai dengan anotasi @Test dan berisi kode untuk dijalankan dan memverifikasi satu fungsi dalam
        //komponen yang ingin di uji
        @Test
        fun  test_validateIntentSentToPickPackage() {
            //perform klik untuk masuk ke profile page
            onView(withId(R.id.profile_page)).perform(click())
            //mencocokkan profile page dengan id dari activity tersebut
            onView(withId(R.id.profile)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            //perform klik pada btn manage profile
            onView(withId(R.id.btn_manage_profil)).perform(click())
            //mencocokkan activity dengan id pada activity manage profile
            onView(withId(R.id.manageprofileact)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            // memberikan aksi untuk mengambil gambar dan mencocoknya
            val expectedIntent: Matcher<Intent> = allOf(
                hasAction(Intent.ACTION_PICK),
                hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            )
            // intending direspon dengan activityresult
            val activityResult = createGalleryPickActivityResultStub()
            intending(expectedIntent).respondWith(activityResult)

            // Mengeksekusi dan mengverify
            onView(withId(R.id.imageProfile)).perform(click())
            intended(expectedIntent)
        }
    //Fungsi untuk mengambil resource dari gallery kita
    private fun createGalleryPickActivityResultStub(): Instrumentation.ActivityResult {
            //resource yang kita ambil
            val resources: Resources = InstrumentationRegistry.getInstrumentation().context.resources
            //uri dari image
            val imageUri = Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE)
            val resultIntent = Intent()
            resultIntent.setData(imageUri)
            return Instrumentation.ActivityResult(RESULT_OK, resultIntent)
        }
    }
