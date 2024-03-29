package com.example.judes_darwinchandra

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description


object ImageViewHasDrawableMatcher {
    //Fungsi untuk mengecek gambar yang sudah ada dan gambar yang kosong pada image profile
    fun hasDrawable(): BoundedMatcher<View, ImageView> {
        return object: BoundedMatcher<View, ImageView>(ImageView::class.java){

            override fun describeTo(description: Description?) {
                description?.appendText("has drawable")
            }

            override fun matchesSafely(item: ImageView?): Boolean {
                return item?.getDrawable() != null;
            }

        }
    }
}