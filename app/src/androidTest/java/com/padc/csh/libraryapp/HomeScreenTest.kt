package com.padc.csh.libraryapp

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padc.csh.libraryapp.activities.MainActivity
import com.padc.csh.libraryapp.utils.*
import com.padc.csh.libraryapp.utils.Commons.withViewAtPosition
import com.padc.csh.libraryapp.viewholders.BookItemViewHolder
import com.padc.csh.libraryapp.viewholders.BookListViewHolder
import com.zg.burgerjoint.uitests.utils.first
import kotlinx.android.synthetic.main.fragment_ebook.*
import kotlinx.coroutines.selects.select
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

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
    fun a_check(){
        //onView(withId(R.id.vpHome)).perform(swipeLeft()) // or swipeRight()
        //onView(withId(R.id.vpHome)).perform(swipeLeft())
        val viewPagerMatcher = allOf(withId(R.id.vpHome), isDisplayed())
       // onView(withId( R.id.nav_home)).perform(click())

        //Thread.sleep(2000)
       // onView(allOf(withId(R.id.rvEbooks), isDescendantOfA(viewPagerMatcher)))
          //  .check(matches(isDisplayed()))

      /*  onView(allOf(withId(R.id.rvBookList) ,
            isDescendantOfA( allOf(withId(R.id.rvEbooks), isDescendantOfA(viewPagerMatcher)))))
            .check(matches(isDisplayed()))*/


       /* onView(allOf(withId(R.id.rvEbooks), isDescendantOfA(viewPagerMatcher)))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(8))*/

     /*    onView(allOf(withId(R.id.rvEbooks), isDescendantOfA(viewPagerMatcher)))
          .check(matches(isDisplayed()))*/
        Thread.sleep(2000)
     /*   onView(withId(R.id.homeFragment))
            .perform(swipeUp())
        onView(withId(R.id.homeFragment))
            .perform(swipeUp())
*/
        onView(withId(R.id.homeFragment)).perform(repeatedlyUntil(swipeUp(),
            hasDescendant(withText(SECOND_CATEGORY_NAME)),
            2), click()
        )

         onView(allOf(
        isDescendantOfA( allOf(withId(R.id.upperChildLayout), hasDescendant(withText(
            SECOND_CATEGORY_NAME)))),
        isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
        SECOND_BOOK_NAME)
        )).check(matches(isDisplayed()))

       /* onView(withId(R.id.homeFragment))
            .perform(swipeUp())

        Thread.sleep(100)

        onView(allOf(
            isDescendantOfA( allOf(withId(R.id.rvEbooks), hasDescendant(withText(
                "Combined Print and E-Book Nonfiction")))),
            isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                "THE WAGER")
        )).check(matches( isDisplayed()))*/
            //perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))


    }

   // @Test
  /*  fun b_checkViewRecWithBookVisiblity(){

        //onView(withId(R.id.rvEbooks)).check(matches(isDisplayed()))
       *//* onView(withRecyclerView(R.id.rvEbooks).atPosition(0)).check(
            onView(R.id.tvCategoryName).check(matches(withText("")))
        )
        }*//*

        //onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))

        *//*onView(withId(R.id.rvEbooks))
            .check(matches(
                withViewAtPosition
                (0, hasDescendant(allOf(
                    withId(R.id.tvCategoryName), withText(FIRST_CATEGORY_NAME)
                )
            ))))*//*
        Thread.sleep(2000)
        onView(withId(R.id.rvEbooks))
            .check(matches(
                withViewAtPosition
                    (0, hasDescendant(allOf(
                    withId(R.id.rvBookList), isDisplayed()
                )
                ))))

        //for first book visibility //this is correct
      *//*  onView(withId(R.id.rvEbooks)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(allOf(
            isDescendantOfA( allOf(withId(R.id.upperChildLayout), hasDescendant(withText(
                FIRST_CATEGORY_NAME)))),
            isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                FIRST_BOOK_NAME)
        )).perform(click())*//*

        onView(allOf( withId(R.id.rvEbooks), withParent(allOf( withId(R.id.frgEbook),
            isDescendantOfA(withId(R.id.vpHome))
        )))).perform(RecyclerViewActions.scrollToPosition<BookListViewHolder>(8))

        Thread.sleep(9000)
       *//* onView(allOf(
            isDescendantOfA( allOf(withId(R.id.upperChildLayout), hasDescendant(withText(
                SECOND_CATEGORY_NAME)))),
            isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                SECOND_BOOK_NAME)
        )).perform(click())*//*
       *//* //withId(R.id.rvBookList)
        onView(allOf( withId(R.id.rlItemView), hasDescendant(withText(FIRST_BOOK_NAME)), withParent(
            allOf( withId(R.id.rvBookList), withParent(withText(FIRST_CATEGORY_NAME))
        )))).perform(click())

        onView(allOf(withId(R.id.rvBookList), withParent(withText() FIRST_CATEGORY_NAME)))
*//*

       *//* onView(withId(R.id.rvBookList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookItemViewHolder>(0,
                ViewActions.click()
            ))*//*




 *//*       //for second book visibility
        onView(withId(R.id.rvEbooks)).perform(RecyclerViewActions.scrollToPosition<BookListViewHolder>
            (1))
        Thread.sleep(600)
        val secondInnerItem = onView(withRecyclerView(R.id.rvBookList).atPosition(1))
             onView(allOf(
             isDescendantOfA( allOf(withId(R.id.upperChildLayout), hasDescendant(withText(
                 "Combined Print and E-Book Fiction")))),
             isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                 "THE WAGER")
         )).check(matches(isDisplayed()))*//*



   *//*     onView(allOf(
            isDescendantOfA( allOf(withId(R.id.upperChildLayout), hasDescendant(withText(
                SECOND_CATEGORY_NAME)))),
            isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                SECOND_BOOK_NAME)
        )).check(matches(isDisplayed()))*//*


*//*
        onView(allOf(withId(R.id.rvBookList), withParent(withRecyclerView( R.id.rvEbooks).atPosition(0)))).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        val secondInnerItem = onView(withRecyclerView(R.id.rvBookList).atPosition(1))
        secondInnerItem.check(matches(  allOf(withId(R.id.tvBookName),withText(FIRST_BOOK_NAME))))


*//*

*//*        onView(allOf(withId(R.id.rvBookList), withParent(withRecyclerView( R.id.rvEbooks).atPosition(0))))
            .check(matches(
                hasDescendant(
                    allOf(withId(R.id.tvBookName),withText(FIRST_BOOK_NAME))
                )))*//*


      *//*  onView(withRecyclerView(R.id.rvBookList).atPosition(1)).check(matches(
            allOf(withId(R.id.tvBookName),withText(FIRST_BOOK_NAME))
        ))*//*

      *//*  onView(withId(R.id.rvBookList))
            .check(matches(
                withViewAtPosition
                    (1, hasDescendant(allOf(
                    withId(R.id.tvBookAuthor), withText(FIRST_BOOK_AUTHOR)
                )
                ))))*//*

      *//*  onView(withId(R.id.rvEbooks))
            .check(matches(
                withViewAtPosition
                    (0, hasDescendant(allOf(
                    withId(R.id.tvCategoryName), withText("Combined Print and E-Book Fiction")
                )
                )
                )
            ))*//*

       // onView(withRecyclerView(R.id.inner_recycler_view).atPosition(1))


        //onView(first<View>(withId(R.id.tvCategoryName))).check(matches(isDisplayed()))
        //onView(first<View>(withId(R.id.rvBookList))).check(matches(isDisplayed()))
        //onView(first<View>(withId(R.id.tvBookName))).check(matches(isDisplayed()))

        *//*onView(withId(R.id.rvEbooks))
            .check(matches(withViewAtPosition
                (0, hasDescendant(allOf(
                withId(R.id.tvCategoryName), isDisplayed())
            ))))

        onView(
            allOf(withId(CHILD_RECYCLERVIEW_ID),
            withParent(withRecyclerView(PARENT_RECYCLERVIEW_ID).atPosition(1)))).perform(actionOnItemAtPosition(0, click()));
*//*
    }*/





}