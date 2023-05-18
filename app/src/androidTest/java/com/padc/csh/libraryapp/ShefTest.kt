package com.padc.csh.libraryapp

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padc.csh.libraryapp.activities.MainActivity
import com.padc.csh.libraryapp.utils.*
import kotlinx.android.synthetic.main.activity_shelve_book_list.*
import kotlinx.android.synthetic.main.dialog_more_for_booklist_shelve.view.*
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.closeTo
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ShefTest {

    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun a_goToShelf_createShelf() {

        //click lib
        onView(withId(R.id.nav_library)).perform(click())
        onView(withId(R.id.vpLibrary)).perform(swipeLeft())
        onView(withId(R.id.viewPodEmptyShelve)).check(matches(isDisplayed()))
        onView(withId(R.id.fbtnCreateShelve)).check(matches(isDisplayed()))

        //click create new
        onView(withId(R.id.fbtnCreateShelve)).check(matches(isClickable()))
        Thread.sleep(1000L)
        onView(withId(R.id.fbtnCreateShelve)).perform(
            click()
        )

        Thread.sleep(1000L)
        onView(withId(R.id.edtShelName)).check(matches(isDisplayed()))
        onView(withId(R.id.edtShelName)).perform(typeText(SHELF_NAME), pressImeActionButton())
        Thread.sleep(1000L)

        //check shelf is here
        onView(withId(R.id.rvShelveListInLibray)).check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.rvShelveListInLibray),
                hasDescendant(
                    allOf(
                        withId(com.padc.csh.libraryapp.R.id.tvShelfNameInShelfItem),
                        withText(SHELF_NAME)
                    )
                )
            )
        ).check(matches(isDisplayed()))

    }

    @Test
    fun b_goToBookTab_addBookSortCategoryToShelf() {
        //click book frag
        onView(withId(R.id.nav_library)).perform(click())
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

        //click first book
        onView(
            allOf(
                withId(R.id.ivMoreInBookItem),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlItemView), hasDescendant(
                            allOf(
                                withId(R.id.tvBookName), withText(
                                    FIRST_BOOK_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).perform(click())

        Thread.sleep(1000L)
        //check dialog visible
        onView(withId(R.id.ivAddToShelve)).check(matches(isDisplayed()))
        onView(withId(R.id.ivAddToShelve)).perform(click())
        Thread.sleep(1000L)

        onView(
            allOf(
                withId(R.id.cbShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.cbShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).perform(click())

        Thread.sleep(1000)
        onView(withId(R.id.ivCloseInAddToShelve)).check(matches(isClickable()))
        onView(withId(R.id.ivCloseInAddToShelve)).perform(click())
        //end of adding first book

        Thread.sleep(1000)
        //click to add second book
        onView(
            allOf(
                withId(R.id.ivMoreInBookItem),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlItemView), hasDescendant(
                            allOf(
                                withId(R.id.tvBookName), withText(
                                    SECOND_BOOK_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).perform(click())

        Thread.sleep(1000L)
        //check dialog visible
        onView(withId(R.id.ivAddToShelve)).check(matches(isDisplayed()))
        onView(withId(R.id.ivAddToShelve)).perform(click())
        Thread.sleep(1000L)

        onView(
            allOf(
                withId(R.id.cbShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.cbShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).perform(click())
        Thread.sleep(1000L)
        onView(withId(R.id.ivCloseInAddToShelve)).perform(click())
        //end of adding second book
        Thread.sleep(1000L)
        //click to add third book
        onView(
            allOf(
                withId(R.id.ivMoreInBookItem),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlItemView), hasDescendant(
                            allOf(
                                withId(R.id.tvBookName), withText(
                                    THIRD_BOOK_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).perform(click())

        Thread.sleep(1000L)
        //check dialog visible
        onView(withId(R.id.ivAddToShelve)).check(matches(isDisplayed()))
        onView(withId(R.id.ivAddToShelve)).perform(click())
        Thread.sleep(1000L)

        onView(
            allOf(
                withId(R.id.cbShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.cbShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).perform(click())
        Thread.sleep(1000L)
        onView(withId(R.id.ivCloseInAddToShelve)).perform(click())
        //end of adding third book
        Thread.sleep(1000L)
        //TO check book count in library
        onView(withId(R.id.vpLibrary)).perform(swipeLeft())
        Thread.sleep(1000)

        //check books item count
        onView(
            allOf(
                withId(R.id.rvShelveListInLibray),
                hasDescendant(
                    allOf(
                        withId(com.padc.csh.libraryapp.R.id.tvShelfNameInShelfItem),
                        withText(SHELF_NAME)
                    )
                )
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.rvShelveListInLibray),
                hasDescendant(
                    allOf(
                        withId(com.padc.csh.libraryapp.R.id.tvBookCountInShelfItem),
                        withText("3 Books")
                    )
                )
            )
        ).check(matches(isDisplayed()))


        //go to shelf detail  //gotShelfDetail_checkBooksHere
        onView(
            allOf(
                withId(R.id.ivMoreShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.ivMoreShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).perform(click())
        Thread.sleep(1000)

        //check shelve books here
        onView(
            allOf(
                withId(R.id.tvShelveTitle),
                withText(SHELF_NAME)
            )
        ).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tvBookCountInShelveBooklist), withText("3 Books"))).check(
            matches(
                isDisplayed()
            )
        )

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

        //check second book is visible
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

        //click category
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

        //check second book name is invisible
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
        ).check(ViewAssertions.doesNotExist())


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

        //check second book name is visible
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
        Thread.sleep(4000)
        onView(withId(R.id.rvYourBookListSmall))
            .check(matches(isDisplayed()))
            .check { view, noViewFoundException ->
                if (view !is RecyclerView) {
                    throw noViewFoundException!!
                }

                val adapter = view.adapter
                val itemCount = adapter?.itemCount ?: 0
                assertThat(itemCount, Matchers.equalTo(3))
            }


        //view as filter
        //**click view as for list view
        onView(withId(R.id.ivViewList)).perform(click())

        //check bottom sheet is displayed
        onView(
            allOf(
                withId(R.id.rlDialogViewAs),
                hasDescendant(
                    Matchers.anyOf(
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



        //**click view as for large view
        onView(withId(R.id.ivViewList)).perform(click())
        //check bottom sheet is displayed
        onView(
            allOf(
                withId(R.id.rlDialogViewAs),
                hasDescendant(
                    Matchers.anyOf(
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
        ).check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvYourBookListLarge), hasDescendant(withText(SECOND_BOOK_NAME)))
        ).check(matches(isDisplayed()))


        //**click view as for small view
        onView(withId(R.id.ivViewList)).perform(click())

        //check bottom sheet is displayed
        onView(
            allOf(
                withId(R.id.rlDialogViewAs),
                hasDescendant(
                    Matchers.anyOf(
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
        ).check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.rvYourBookListSmall), hasDescendant(withText(SECOND_BOOK_NAME)))
        ).check(matches(isDisplayed()))


        //start to rename
        //click menu icon
        onView(withId(com.padc.csh.libraryapp.R.id.ivMoreShelveBooklist)).check(matches(isDisplayed()))
        onView(withId(com.padc.csh.libraryapp.R.id.ivMoreShelveBooklist)).perform(click())

        Thread.sleep(1000)
        onView(withId(com.padc.csh.libraryapp.R.id.tvRenameShelf)).check(matches(isDisplayed()))
        onView(withId(com.padc.csh.libraryapp.R.id.tvRenameShelf)).perform(click())

        //check edit text appear
        onView(withId(R.id.edtShelveTitle)).check(matches(isDisplayed()))

        //type
        onView(withId(R.id.edtShelveTitle)).perform(clearText())
        onView(withId(R.id.edtShelveTitle)).perform(typeText(UPDATE_SHELF_NAME),
            pressImeActionButton()
        )
        Thread.sleep(1000)
        //check edt gone,text appear

        //check shelf is here
        onView(withId(R.id.rvShelveListInLibray)).check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.rvShelveListInLibray),
                hasDescendant(
                    allOf(
                        withId(com.padc.csh.libraryapp.R.id.tvShelfNameInShelfItem),
                        withText(UPDATE_SHELF_NAME)
                    )
                )
            )
        ).check(matches(isDisplayed()))

        //start to delete
        //go to shelf detail  //gotShelfDetail_checkBooksHere
        onView(
            allOf(
                withId(R.id.ivMoreShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    UPDATE_SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.ivMoreShelve),
                isDescendantOfA(
                    allOf(
                        withId(R.id.rlShelfItem), hasDescendant(
                            allOf(
                                withId(R.id.tvShelfNameInShelfItem), withText(
                                    UPDATE_SHELF_NAME
                                )
                            )
                        )
                    )
                )
            )
        ).perform(click())
        Thread.sleep(1000)

        //click menu icon
        onView(withId(com.padc.csh.libraryapp.R.id.ivMoreShelveBooklist)).check(matches(isDisplayed()))
        onView(withId(com.padc.csh.libraryapp.R.id.ivMoreShelveBooklist)).perform(click())

        Thread.sleep(1000)
        onView(withId(com.padc.csh.libraryapp.R.id.tvDeleteShelf)).check(matches(isDisplayed()))
        onView(withId(com.padc.csh.libraryapp.R.id.tvDeleteShelf)).perform(click())
        Thread.sleep(1000)

        onView(allOf( withId(R.id.btnYes), isDescendantOfA(withId(R.id.rlConfirmDialog))
        )).check(matches(isDisplayed()))

        onView(allOf( withId(R.id.btnYes), isDescendantOfA(withId(R.id.rlConfirmDialog))
        )).perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.viewPodEmptyShelve)).check(matches(isDisplayed()))




    }

    @Test
    fun c_renameAndDeleteShelf(){



    }
}

 /*   @Test
    fun c_gotShelfDetail_checkBooksHere(){

        onView(allOf(
            withId(R.id.ivMoreShelve),
            isDescendantOfA( allOf(withId(R.id.rlShelfItem), hasDescendant(allOf(withId(R.id.tvShelfNameInShelfItem), withText(
                SHELF_NAME)))))
        )).check(matches(isDisplayed()))

        onView(allOf(
            withId(R.id.ivMoreShelve),
            isDescendantOfA( allOf(withId(R.id.rlShelfItem), hasDescendant(allOf(withId(R.id.tvShelfNameInShelfItem), withText(
                SHELF_NAME)))))
        )).perform(click())
        Thread.sleep(1000)

        //check shelve books here
        onView(allOf( withId(R.id.tvShelveTitle), withText(SHELF_NAME))).check(matches(isDisplayed()))
        onView(allOf( withId(R.id.tvBookCountInShelveBooklist), withText("3 Books"))).check(matches(isDisplayed()))

        //check first book name is visible
        onView(allOf( withId(R.id.rvYourBookListSmall),
            hasDescendant(
                allOf(withId(R.id.tvBookName),
                    withText(FIRST_BOOK_NAME))))).check(matches(isDisplayed()))

        //check first book name is invisible
        onView(allOf( withId(R.id.rvYourBookListSmall),
            hasDescendant(
                allOf(withId(R.id.tvBookName),
                    withText(SECOND_BOOK_NAME))))).check(ViewAssertions.doesNotExist())

        //check category name
        onView(allOf( withId(R.id.rvCategoryList),
            hasDescendant(
                allOf(withId(R.id.chipCategoryNmae),
                    withText(FIRST_CATEGORY_NAME))))).check(matches(isDisplayed()))



    }*/

  /*  @Test
    fun d_clickEachCategory_checkBooks(){
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
                    withText(SECOND_BOOK_NAME))))).check(ViewAssertions.doesNotExist())


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
                assertThat(itemCount, Matchers.equalTo(3))
            }
    }*/

 /*   @Test
    fun e_clickViewFilter_checkRelatedViewAppeared(){
        //click view as for list view
        onView(withId(R.id.ivViewList)).perform(click())

        //check bottom sheet is displayed
        onView(allOf( withId(R.id.rlDialogViewAs),
            hasDescendant(
                Matchers.anyOf(
                    withId(R.id.rbList),
                    withText("List")
                )
            ))).check(matches(isDisplayed()))

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



        //click view as for small view
        onView(withId(R.id.ivViewList)).perform(click())

        //check bottom sheet is displayed
        onView(allOf( withId(R.id.rlDialogViewAs),
            hasDescendant(
                Matchers.anyOf(
                    withId(R.id.rbSmallGrid),
                    withText("Small grid")
                )
            ))).check(matches(isDisplayed()))

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



        //click view as for large view
        onView(withId(R.id.ivViewList)).perform(click())
        //check bottom sheet is displayed
        onView(allOf( withId(R.id.rlDialogViewAs),
            hasDescendant(
                Matchers.anyOf(
                    withId(R.id.rbLargeGrid),
                    withText("Large grid")
                )
            ))).check(matches(isDisplayed()))

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


*/
