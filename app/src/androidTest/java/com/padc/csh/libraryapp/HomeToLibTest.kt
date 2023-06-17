package com.padc.csh.libraryapp

import android.content.Intent
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padc.csh.libraryapp.activities.MainActivity
import com.padc.csh.libraryapp.utils.*
import com.padc.csh.libraryapp.viewholders.BookListViewHolder
import com.zg.burgerjoint.uitests.utils.first
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeToLibTest {

    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    fun swipeupForBook(){
        Thread.sleep(2000)
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        onView(withId(R.id.homeFragment)).perform(swipeUp())
        Thread.sleep(2000)
    }


    @Test
    fun homeTestAndLibBookTest() {

        Thread.sleep(2000)
        onView(
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
        ).perform(click())


        //Detail view test
        onView(allOf(first(withId(R.id.tvBookNameInBookDetail)), withText(FIRST_BOOK_NAME)))
            .check(matches(isDisplayed()))

        onView(allOf(first(withId(R.id.tvBookAuthorInBookDetail)), withText(FIRST_BOOK_AUTHOR)))
            .check(matches(isDisplayed()))

        //click backbutton
        onView(withId(R.id.ivBackBookDetail)).perform(click())
        Thread.sleep(200)
        //vp banner is visible
        //onView(withId(R.id.viewPagerRecent)).check(matches(isDisplayed()))

  //  }

  //  @Test
  //  fun b_secondBookClick() {

        swipeupForBook()
        onView(
            allOf(
                isDescendantOfA(
                    allOf(
                        withId(R.id.upperChildLayout), hasDescendant(
                            withText(
                                SECOND_CATEGORY_NAME
                            )
                        )
                    )
                ),
                isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                    SECOND_BOOK_NAME
                )
            )
        ).perform(click())

        //Detail view test
        onView(allOf(first(withId(R.id.tvBookNameInBookDetail)), withText(SECOND_BOOK_NAME)))
            .check(matches(isDisplayed()))

        onView(allOf(first(withId(R.id.tvBookAuthorInBookDetail)), withText(SECOND_BOOK_AUTHOR)))
            .check(matches(isDisplayed()))

       //click backbutton
        onView(withId(R.id.ivBackBookDetail)).perform(click())

        /*Thread.sleep(200)
       //vp banner is visible
      onView(withId(R.id.viewPagerRecent)).check(matches(isDisplayed()))
       Thread.sleep(200)
       onView(withId(R.id.viewPagerRecent))
           .check(matches(isDisplayed()))
           .check { view, noViewFoundException ->
               if (view !is ViewPager) {
                   throw noViewFoundException!!
               }

               val adapter = view.adapter
               val itemCount = adapter?.count ?: 0
               assertThat(itemCount, equalTo(2))
           }
       Thread.sleep(2000)*/
  //  }

 //   @Test
 //   fun c_thirdBookClick() {

        swipeupForBook()

        onView(
            allOf(
                isDescendantOfA(
                    allOf(
                        withId(R.id.upperChildLayout), hasDescendant(
                            withText(
                                THIRD_CATEGORY_NAME
                            )
                        )
                    )
                ),
                isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                    THIRD_BOOK_NAME
                )
            )
        ).perform(click())

        //Detail view test
        onView(allOf(first(withId(R.id.tvBookNameInBookDetail)), withText(THIRD_BOOK_NAME)))
            .check(matches(isDisplayed()))

        onView(allOf(first(withId(R.id.tvBookAuthorInBookDetail)), withText(THIRD_BOOK_AUTHOR)))
            .check(matches(isDisplayed()))

        //click backbutton
        onView(withId(R.id.ivBackBookDetail)).perform(click())

        //vp banner is visible
        /*  onView(withId(R.id.viewPagerRecent)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.viewPagerRecent))
            .check(matches(isDisplayed()))
            .check { view, noViewFoundException ->
                if (view !is ViewPager) {
                    throw noViewFoundException!!
                }

                val adapter = view.adapter
                val itemCount = adapter?.count ?: 0
                assertThat(itemCount, equalTo(3))
            }*/

   // }


 //   @Test
//    fun d_swipdownToVpBanner_checkIsDisplayed() {
        Thread.sleep(1000L)

        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.viewPagerRecent))
            .check(matches(isDisplayed()))
  //  }

//    @Test
 //   fun e_goToLib_checkBookIsDisplayed() {
        // Click  library tab
        onView(withId(R.id.nav_library)).perform(click())
        Thread.sleep(1000)

        //check visibility of tab,vp,
        onView(withId(R.id.vpLibrary)).check(matches(isDisplayed()))

        onView(withId(R.id.tablayoutLibrary)).check(matches(isDisplayed()))

        //check book name for list ,small view
        onView(
            allOf(withId(R.id.vpLibrary), hasDescendant(withText(FIRST_BOOK_NAME)))
        ).check(
            matches(isDisplayed())
        )
        onView(
            allOf(withId(R.id.vpLibrary), hasDescendant(withText(SECOND_BOOK_NAME)))
        ).check(
            matches(isDisplayed())
        )

        //check category name
        onView(
            allOf(
                withId(R.id.rvCategoryList),
                hasDescendant(
                    allOf(
                        withId(R.id.chipCategoryNmae),
                        withText(FIRST_CATEGORY_NAME)
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //click first category name
        onView(
            allOf(
                withId(R.id.chipCategoryNmae),
                withText(FIRST_CATEGORY_NAME)
            )
        ).perform(click())
        Thread.sleep(500)

        //check first book name is visible
        onView(
            allOf(
                withId(R.id.rvYourBookListSmall),
                hasDescendant(
                    allOf(
                        withId(R.id.tvBookName),
                        withText(FIRST_BOOK_NAME)
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //check first book name is invisible
        onView(
            allOf(
                withId(R.id.rvYourBookListSmall),
                hasDescendant(
                    allOf(
                        withId(R.id.tvBookName),
                        withText(SECOND_BOOK_NAME)
                    )
                )
            )
        ).check(doesNotExist())


        //for second category
        onView(withId(R.id.rvCategoryList)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1)
        )

        onView(
            allOf(
                withId(R.id.rvCategoryList),
                hasDescendant(
                    allOf(
                        withId(R.id.chipCategoryNmae),
                        withText(SECOND_CATEGORY_NAME)
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //click first category name
        onView(
            allOf(
                withId(R.id.chipCategoryNmae),
                withText(SECOND_CATEGORY_NAME)
            )
        ).perform(click())

        Thread.sleep(500)

        //check first book name is visible
        onView(
            allOf(
                withId(R.id.rvYourBookListSmall),
                hasDescendant(
                    allOf(
                        withId(R.id.tvBookName),
                        withText(FIRST_BOOK_NAME)
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //check first book name is visible
        onView(
            allOf(
                withId(R.id.rvYourBookListSmall),
                hasDescendant(
                    allOf(
                        withId(R.id.tvBookName),
                        withText(SECOND_BOOK_NAME)
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //click cancel
        onView(withId(R.id.flCancelCategoryList)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.rvYourBookListSmall))
            .check(matches(isDisplayed()))
            .check { view, noViewFoundException ->
                if (view !is RecyclerView) {
                    throw noViewFoundException!!
                }

                val adapter = view.adapter
                val itemCount = adapter?.itemCount ?: 0
                assertThat(itemCount, equalTo(3))
            }


        //**click view as for list view
        onView(withId(R.id.ivViewList)).perform(click())

        //check bottom sheet is displayed
        onView(
            allOf(
                withId(R.id.rlDialogViewAs),
                hasDescendant(
                    anyOf(
                        withId(R.id.rbList),
                        withText("List")
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //CLICK list radio btn
        onView(withId(R.id.rbList)).perform(click())

        Thread.sleep(100)
        //in rvlist,check first book
        onView(
            allOf(withId(R.id.rvYourBookListList), hasDescendant(withText(FIRST_BOOK_NAME)))
        )
            .check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvYourBookListList), hasDescendant(withText(SECOND_BOOK_NAME)))
        )
            .check(matches(isDisplayed()))


        //**click view as for small view
        onView(withId(R.id.ivViewList)).perform(click())

        //check bottom sheet is displayed
        onView(
            allOf(
                withId(R.id.rlDialogViewAs),
                hasDescendant(
                    anyOf(
                        withId(R.id.rbSmallGrid),
                        withText("Small grid")
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //CLICK list radio btn
        onView(withId(R.id.rbSmallGrid)).perform(click())

        Thread.sleep(100)

        //in rvlist,check first book
        onView(
            allOf(withId(R.id.rvYourBookListSmall), hasDescendant(withText(FIRST_BOOK_NAME)))
        )
            .check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvYourBookListSmall), hasDescendant(withText(SECOND_BOOK_NAME)))
        )
            .check(matches(isDisplayed()))


        //**click view as for large view
        onView(withId(R.id.ivViewList)).perform(click())
        //check bottom sheet is displayed
        onView(
            allOf(
                withId(R.id.rlDialogViewAs),
                hasDescendant(
                    anyOf(
                        withId(R.id.rbLargeGrid),
                        withText("Large grid")
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //CLICK list radio btn
        onView(withId(R.id.rbLargeGrid)).perform(click())
        Thread.sleep(100)
        //in rvlist,check first book
        onView(
            allOf(withId(R.id.rvYourBookListLarge), hasDescendant(withText(FIRST_BOOK_NAME)))
        )
            .check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvYourBookListLarge), hasDescendant(withText(SECOND_BOOK_NAME)))
        )
            .check(matches(isDisplayed()))


    }


}







