package com.padc.csh.libraryapp.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.Gson
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.fragments.AudioBookFragment
import com.padc.csh.libraryapp.fragments.EbookFragment
//import com.padc.csh.libraryapp.fragments.EbookFragment.Companion.BOOK_LIST_PARAM
import com.padc.csh.libraryapp.fragments.HomeFragment
import com.padc.csh.libraryapp.fragments.LibraryFragment


class HomeVPAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2


    val ebookFragment = EbookFragment()
    var bookListData = listOf<BookVO>()
    fun setEbookData(bookList: List<BookVO>) {
        bookListData = bookList
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
              /*  if(!bookListData.isNullOrEmpty()){
                    //pass data
                    var bundle = Bundle().apply {
                        this.putString(BOOK_LIST_PARAM, Gson().toJson(bookListData))
                    }
                    ebookFragment.arguments = bundle
                }*/

                ebookFragment
            }

            1 -> {
                /* val shortFragment= ShortsFragment()

                  //pass data
                  var bundle=Bundle().apply {
                      this.putString(KEY_SHORT_DESCRIPTION,"This is short fragment")
                  }
                  shortFragment.arguments=bundle*/

                AudioBookFragment()
            }
            else -> {
                /* val subscriptionFragment=SubscriptionFragment()

                //pass data
                var bundle=Bundle().apply {
                    putString(KEY_SUBSCRIPTION_DESCRIPTION,"This is Subscription fragment")
                }
                subscriptionFragment.arguments=bundle*/

                AudioBookFragment()
            }
        }
    }
}