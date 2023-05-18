package com.padc.csh.libraryapp

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padc.csh.libraryapp.activities.MainActivity
import com.padc.csh.libraryapp.utils.RESULT_BOOK_NAME1
import com.padc.csh.libraryapp.utils.RESULT_BOOK_NAME2
import com.padc.csh.libraryapp.utils.SEARCH_BOOK_NAME
import kotlinx.android.synthetic.main.activity_book_search_list.*
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchTest {

    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun searchBook_checkBookDisplay() {

        onView(withId(R.id.edtSearchBook)).check(matches(isDisplayed()))
        onView(withId(R.id.edtSearchBook)).check(matches(isClickable()))
        onView(withId(R.id.edtSearchBook)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.edtSearchBookName)).check(matches(isDisplayed()))
        onView(withId(R.id.edtSearchBookName)).check(matches(isClickable()))
        onView(withId(R.id.edtSearchBookName)).perform(
            click(),
            typeText(SEARCH_BOOK_NAME),
            closeSoftKeyboard()
        )
        Thread.sleep(1000)

        onView(withId(R.id.rvSearchBookList)).check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvSearchBookList), hasDescendant( anyOf(
                allOf(withId(com.padc.csh.libraryapp.R.id.tvBookNameViewAsList), withText(
                    RESULT_BOOK_NAME1)),
                allOf(withId(com.padc.csh.libraryapp.R.id.tvBookNameViewAsList), withText(
                    RESULT_BOOK_NAME2))
            )))
            ).check(matches(isDisplayed()))








    }
}