package com.padc.csh.libraryapp

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padc.csh.libraryapp.activities.MainActivity
import com.padc.csh.libraryapp.utils.*
import com.zg.burgerjoint.uitests.utils.first
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

    @Test
    fun a_firstBookClick(){

        Thread.sleep(2000)
        onView(allOf(
            isDescendantOfA( allOf(withId(R.id.upperChildLayout), hasDescendant(withText(
                FIRST_CATEGORY_NAME)))),
            isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                FIRST_BOOK_NAME)
        )).perform(click())


        //Detail view test
        onView(allOf( first(withId(R.id.tvBookNameInBookDetail)),withText(FIRST_BOOK_NAME)))
            .check(matches( isDisplayed()))

        onView(allOf( first(withId(R.id.tvBookAuthorInBookDetail)),withText(FIRST_BOOK_AUTHOR)))
            .check(matches( isDisplayed()))

        //click backbutton
        onView(withId(R.id.ivBackBookDetail)).perform(click())
        Thread.sleep(200)
        //vp banner is visible
        //onView(withId(R.id.viewPagerRecent)).check(matches(isDisplayed()))

    }

    @Test
    fun b_secondBookClick(){

        Thread.sleep(2000)

        onView(withId(R.id.homeFragment))
            .perform(swipeUp())

        onView(withId(R.id.homeFragment))
            .perform(swipeUp())

        Thread.sleep(2000)
        onView(allOf(
            isDescendantOfA( allOf(withId(R.id.upperChildLayout), hasDescendant(withText(
                SECOND_CATEGORY_NAME)))),
            isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                SECOND_BOOK_NAME)
        )).perform(click())

        //Detail view test
        onView(allOf( first(withId(R.id.tvBookNameInBookDetail)),withText(SECOND_BOOK_NAME)))
            .check(matches( isDisplayed()))

        onView(allOf( first(withId(R.id.tvBookAuthorInBookDetail)),withText(SECOND_BOOK_AUTHOR)))
            .check(matches( isDisplayed()))

        /*//click backbutton
        onView(withId(R.id.ivBackBookDetail)).perform(click())
        Thread.sleep(200)
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
    }

    @Test
    fun c_thirdBookClick(){

        Thread.sleep(2000)

        onView(withId(R.id.homeFragment))
            .perform(swipeUp())


        onView(withId(R.id.homeFragment))
            .perform(swipeUp())

        onView(withId(R.id.homeFragment))
            .perform(swipeUp())

        Thread.sleep(2000)
        onView(allOf(
            isDescendantOfA( allOf(withId(R.id.upperChildLayout), hasDescendant(withText(
                THIRD_CATEGORY_NAME)))),
            isDescendantOfA(withId(R.id.rvBookList)), withId(R.id.tvBookName), withText(
                THIRD_BOOK_NAME)
        )).perform(click())

        //Detail view test
        onView(allOf( first(withId(R.id.tvBookNameInBookDetail)),withText(THIRD_BOOK_NAME)))
            .check(matches( isDisplayed()))

        onView(allOf( first(withId(R.id.tvBookAuthorInBookDetail)),withText(THIRD_BOOK_AUTHOR)))
            .check(matches( isDisplayed()))

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

    }


    @Test
    fun d_swipdownToVpBanner_checkIsDisplayed() {
        Thread.sleep(1000L)

        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.homeFragment))
            .perform(swipeDown())
        onView(withId(R.id.viewPagerRecent))
            .check(matches(isDisplayed()))
    }

    @Test
    fun e_goToLib_checkBookIsDisplayed(){
        // Click  library tab
        onView(withId(R.id.nav_library)).perform(click())
        Thread.sleep(1000)

        //check visibility of tab,vp,
        onView(withId(R.id.vpLibrary)).check(matches(isDisplayed()))

        onView(withId(R.id.tablayoutLibrary)).check(matches(isDisplayed()))

        //check book name for list ,small view
        onView(
            allOf(withId(R.id.vpLibrary),hasDescendant(withText(FIRST_BOOK_NAME)))).check(
            matches(isDisplayed())
        )
        onView(
            allOf(withId(R.id.vpLibrary),hasDescendant(withText(SECOND_BOOK_NAME)))).check(
            matches(isDisplayed())
        )

        //check category name
        onView(allOf( withId(R.id.rvCategoryList),
            hasDescendant(
                allOf(withId(R.id.chipCategoryNmae),
                    withText(FIRST_CATEGORY_NAME))))).check(matches(isDisplayed()))

        //click first category name
        onView(allOf(withId(R.id.chipCategoryNmae),
                    withText(FIRST_CATEGORY_NAME))).perform(click())
        Thread.sleep(500)

        //check first book name is visible
        onView(allOf( withId(R.id.rvYourBookListSmall),
            hasDescendant(
                allOf(withId(R.id.tvBookName),
                    withText(FIRST_BOOK_NAME))))).check(matches(isDisplayed()))

        //check first book name is invisible
        onView(allOf( withId(R.id.rvYourBookListSmall),
            hasDescendant(
                allOf(withId(R.id.tvBookName),
                    withText(SECOND_BOOK_NAME))))).check(doesNotExist())


        //for second category
        onView(withId(R.id.rvCategoryList)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(allOf( withId(R.id.rvCategoryList),
            hasDescendant(
                allOf(withId(R.id.chipCategoryNmae),
                    withText(SECOND_CATEGORY_NAME))))).check(matches(isDisplayed()))

        //click first category name
        onView(allOf(withId(R.id.chipCategoryNmae),
            withText(SECOND_CATEGORY_NAME))).perform(click())

        Thread.sleep(500)

        //check first book name is visible
        onView(allOf( withId(R.id.rvYourBookListSmall),
            hasDescendant(
                allOf(withId(R.id.tvBookName),
                    withText(FIRST_BOOK_NAME))))).check(matches(isDisplayed()))

        //check first book name is visible
        onView(allOf( withId(R.id.rvYourBookListSmall),
            hasDescendant(
                allOf(withId(R.id.tvBookName),
                    withText(SECOND_BOOK_NAME))))).check(matches(isDisplayed()))

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
        onView(allOf( withId(R.id.rlDialogViewAs),
            hasDescendant(
                anyOf(withId(R.id.rbList),
                    withText("List")
                )))).check(matches(isDisplayed()))

        //CLICK list radio btn
        onView(withId(R.id.rbList)).perform(click())

        Thread.sleep(100)
        //in rvlist,check first book
        onView(
            allOf(withId(R.id.rvYourBookListList),hasDescendant(withText(FIRST_BOOK_NAME))))
            .check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvYourBookListList),hasDescendant(withText(SECOND_BOOK_NAME))))
            .check(matches(isDisplayed()))



        //**click view as for small view
        onView(withId(R.id.ivViewList)).perform(click())

        //check bottom sheet is displayed
        onView(allOf( withId(R.id.rlDialogViewAs),
            hasDescendant(
                anyOf(withId(R.id.rbSmallGrid),
                    withText("Small grid")
                )))).check(matches(isDisplayed()))

        //CLICK list radio btn
        onView(withId(R.id.rbSmallGrid)).perform(click())

        Thread.sleep(100)

        //in rvlist,check first book
        onView(
            allOf(withId(R.id.rvYourBookListSmall),hasDescendant(withText(FIRST_BOOK_NAME))))
            .check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvYourBookListSmall),hasDescendant(withText(SECOND_BOOK_NAME))))
            .check(matches(isDisplayed()))



        //**click view as for large view
        onView(withId(R.id.ivViewList)).perform(click())
        //check bottom sheet is displayed
        onView(allOf( withId(R.id.rlDialogViewAs),
            hasDescendant(
                anyOf(withId(R.id.rbLargeGrid),
                    withText("Large grid")
                )))).check(matches(isDisplayed()))

        //CLICK list radio btn
        onView(withId(R.id.rbLargeGrid)).perform(click())
        Thread.sleep(100)
        //in rvlist,check first book
        onView(
            allOf(withId(R.id.rvYourBookListLarge),hasDescendant(withText(FIRST_BOOK_NAME))))
            .check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvYourBookListLarge),hasDescendant(withText(SECOND_BOOK_NAME))))
            .check(matches(isDisplayed()))





    }





    /*   onView(withId(R.id.homeFragment)).perform(
              ViewActions.repeatedlyUntil(
                  swipeUp(),
                  hasDescendant(withText(THIRD_CATEGORY_NAME)),
                  3
              )
          )*/





















  /*  @Test
    @UiThreadTest
    fun testSwipeNestedScrollViewUntilViewFound() {
        val nestedScrollViewId = R.id.homeFragment
        val targetViewId = R.id.tvCategoryName

        // Register an IdlingResource to wait for the app to become idle during swipe
        val idlingResource = SwipeIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
        do {
            // Swipe up on the nested scroll view
            //onView(withId(R.id.homeFragment)).perform(swipeUp())
            onView(withId(nestedScrollViewId)).perform(actionWithAssertions(swipeUpCustom()))

            // Check if the target view is displayed
            val targetViewMatcher = onView(allOf( withId(targetViewId), withText(THIRD_CATEGORY_NAME)))
            val isTargetViewDisplayed = isViewDisplayed(targetViewMatcher)

            if (isTargetViewDisplayed) {
                // The target view is found, exit the loop
                break
            }
        } while (true)

        // Unregister the IdlingResource
        IdlingRegistry.getInstance().unregister(idlingResource)

        // Add your assertions or further actions here
    }




class SwipeIdlingResource : IdlingResource {
        private var resourceCallback: IdlingResource.ResourceCallback? = null
        private var isIdle = false

        override fun getName(): String {
            return "SwipeIdlingResource"
        }

        override fun isIdleNow(): Boolean {
            return isIdle
        }

        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
            resourceCallback = callback
        }

        fun setViewIdle() {
            isIdle = true
            resourceCallback?.onTransitionToIdle()
        }

        fun observeViewLayout(view: View) {
            val viewTreeObserver = view.viewTreeObserver
            val layoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    // View has been laid out completely
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    setViewIdle()
                }
            }
            viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
        }
    }


  *//*  @Test
    fun testSwipeNestedScrollViewUntilViewFound() {
        val nestedScrollViewId = R.id.nestedScrollView
        val targetViewId = R.id.targetView

        val idlingResource = SwipeIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)

        do {
            // Perform swipe up using a custom scroll action
            onView(withId(nestedScrollViewId)).perform(actionWithAssertions(swipeUpCustom()))

            // Check if the target view is displayed
            val isTargetViewDisplayed = isViewDisplayed(targetViewId)

            if (isTargetViewDisplayed) {
                // The target view is found, exit the loop
                break
            }
        } while (true)

        IdlingRegistry.getInstance().unregister(idlingResource)

        // Add your assertions or further actions here
    }*//*

    private fun swipeUpCustom(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): org.hamcrest.Matcher<View> {
                return Matchers.any(View::class.java)
            }

            override fun getDescription(): String {
                return "Swipe up"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val startCoordinates = intArrayOf(view!!.width / 2, view.height - 200)
                val endCoordinates = intArrayOf(view.width / 2, 0)
                val precision = floatArrayOf(1f, 1f)
                val downEvent = MotionEvent.obtain(
                    SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_DOWN,
                    startCoordinates[0].toFloat(),
                    startCoordinates[1].toFloat(),
                    0
                )
                val moveEvent = MotionEvent.obtain(
                    SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_MOVE,
                    endCoordinates[0].toFloat(),
                    endCoordinates[1].toFloat(),
                    0
                )
                val upEvent = MotionEvent.obtain(
                    SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_UP,
                    endCoordinates[0].toFloat(),
                    endCoordinates[1].toFloat(),
                    0
                )
                view.dispatchTouchEvent(downEvent)
                view.dispatchTouchEvent(moveEvent)
                view.dispatchTouchEvent(upEvent)

                downEvent.recycle()
                moveEvent.recycle()
                upEvent.recycle()
            }
        }
    }


*/




    }