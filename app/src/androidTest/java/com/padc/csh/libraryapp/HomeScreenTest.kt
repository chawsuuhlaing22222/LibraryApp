package com.padc.csh.libraryapp


import android.content.Intent
import android.content.res.Resources
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padc.csh.libraryapp.activities.MainActivity
import com.padc.csh.libraryapp.utils.*
import kotlinx.android.synthetic.main.fragment_ebook.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

//Not Use This Class
@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeScreenTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun scrollToPos(){
      var v=  onView(
            allOf(
                isDescendantOfA(
                    allOf(
                        withId(R.id.upperChildLayout), hasDescendant(
                            withText(
                                FIRST_CATEGORY_NAME
                            )
                        )
                    )
                ),
                isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                    FIRST_BOOK_NAME
                )
            )
        )

        // Locate the NestedScrollView
        // Locate the NestedScrollView
/*        onView(
            allOf(
                isAssignableFrom(NestedScrollView::class.java),
                isDescendantOfA(withId(R.id.homeFragment))
            )
        ).perform(scrollTo())*/

// Scroll to a specific item in the RecyclerView

// Scroll to a specific item in the RecyclerView
        onView(
            allOf(
                withId(R.id.rvEbooks),
                isDescendantOfA(withId(R.id.homeFragment))
            )
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(4))

    }

    var sec_category="Middle Grade Paperback Monthly"
    var sec_book_name="THE ONE AND ONLY IVAN"
    var sec_book_author="Katherine Applegate."

    var third_category="Young Adult Paperback Monthly"
    var third_book_name="IF HE HAD BEEN WITH ME"
    var third_book_author="Laura Nowlin"
  @Test
    fun test(){

        Thread.sleep(1000)
      onView(withId(R.id.homeFragment)).perform(swipeUp())
      onView(withId(R.id.homeFragment)).perform(swipeUp())
      onView(withId(R.id.homeFragment)).perform(swipeUp())
      onView(withId(R.id.homeFragment)).perform(swipeUp())
      onView(withId(R.id.homeFragment)).perform(swipeUp())
      onView(withId(R.id.homeFragment)).perform(swipeUp())
      onView(withId(R.id.homeFragment)).perform(swipeUp())
      onView(withId(R.id.homeFragment)).perform(swipeUp())
      onView(withId(R.id.homeFragment)).perform(swipeUp())

      var v=  onView(
          allOf(
              isDescendantOfA(
                  allOf(
                      withId(R.id.upperChildLayout), hasDescendant(
                          withText(
                              sec_category
                          )
                      )
                  )
              ),
              isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                  sec_book_name
              )
          )
      )

      v.check(matches(isDisplayed()))
      v.perform(click())
      onView(withId(R.id.ivBackBookDetail)).perform(click())

Thread.sleep(1000)

      var v2=  onView(
          allOf(
              isDescendantOfA(
                  allOf(
                      withId(R.id.upperChildLayout), hasDescendant(
                          withText(
                              third_category
                          )
                      )
                  )
              ),
              isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                  third_book_name
              )
          )
      )


      v2.check(matches(isDisplayed()))
      v2.perform(click())


  }



}