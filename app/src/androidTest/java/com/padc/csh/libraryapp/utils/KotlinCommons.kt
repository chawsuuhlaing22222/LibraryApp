package com.padc.csh.libraryapp.utils

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.hamcrest.Description
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

var sec_category="Middle Grade Paperback Monthly"
var sec_book_name="THE ONE AND ONLY IVAN"
var sec_book_author="Katherine Applegate."

var third_category="Young Adult Paperback Monthly"
var third_book_name="IF HE HAD BEEN WITH ME"
var third_book_author="Laura Nowlin"

//constants
const val FIRST_CATEGORY_NAME="Combined Print and E-Book Fiction"
const val SECOND_CATEGORY_NAME= "Middle Grade Paperback Monthly"
const val THIRD_CATEGORY_NAME= "Young Adult Paperback Monthly"

const val FIRST_BOOK_NAME="HAPPY PLACE"
const val FIRST_BOOK_AUTHOR="Emily Henry"

const val SECOND_BOOK_NAME="THE ONE AND ONLY IVAN"
const val SECOND_BOOK_AUTHOR="Katherine Applegate."

const val THIRD_BOOK_NAME="IF HE HAD BEEN WITH ME"
const val THIRD_BOOK_AUTHOR="Laura Nowlin"


const val SHELF_NAME="my favourites"
const val UPDATE_SHELF_NAME="My Fav Books"

const val SEARCH_BOOK_NAME="hear"
const val RESULT_BOOK_NAME1="Hear, Listen, Play!"
const val RESULT_BOOK_NAME2="How can I Hear the Voice of God?"





//functions
class RecyclerViewMatcher(private val recyclerViewId: Int) {
    fun atPosition(position: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position")
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                return viewHolder != null && viewHolder.itemView.id == recyclerViewId
            }
        }
    }
}

fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
}


fun isViewDisplayed(viewMatcher: ViewInteraction): Boolean {
    var isDisplayed = false

    viewMatcher.check { view, noViewFoundException ->
        isDisplayed = (view != null && isViewDisplayed(viewMatcher))
    }

    return isDisplayed
}

fun getCurrentActivity(): Activity? {
    val activityRef = AtomicReference<Activity>()
    InstrumentationRegistry.getInstrumentation().runOnMainSync {
        val activities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(
            Stage.RESUMED)
        activityRef.set(activities.elementAtOrNull(0))
    }
    return activityRef.get()
}