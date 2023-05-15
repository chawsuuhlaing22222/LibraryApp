package com.padc.csh.libraryapp.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.activities.BookListByCategoryActivity
import com.padc.csh.libraryapp.adapters.HomeVPAdapter
import com.padc.csh.libraryapp.adapters.RecentBookBannerAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.mvp.presenters.HomePresenterImpl
import com.padc.csh.libraryapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.Math.abs

class HomeFragment : Fragment(), HomeView {

    lateinit var mHomePresenter: HomePresenterImpl
    lateinit var mRecentBookBannerAdapter: RecentBookBannerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecentBanner()
        setUpViewPager()
        setUpTablayout()
        mHomePresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        mHomePresenter = ViewModelProvider(this)[HomePresenterImpl::class.java]
        mHomePresenter.onInitView(this)

    }


    private fun setUpRecentBanner() {
        mRecentBookBannerAdapter = RecentBookBannerAdapter(mHomePresenter)
        viewPagerRecent.adapter = mRecentBookBannerAdapter
        //viewPagerRecent.setPreviewBothSide(R.dimen.margin_large,R.dimen.margin_large)
        //viewPagerRecent.currentItem=1
        viewPagerRecent.offscreenPageLimit = 3
        viewPagerRecent.clipToPadding = false
        viewPagerRecent.clipChildren = false
        viewPagerRecent.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        //setUp Trasformer
        var transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            var r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPagerRecent.setPageTransformer(transformer)

        /*  //runnable
          var runnable= kotlinx.coroutines.Runnable {
              viewPagerRecent.currentItem=viewPagerRecent.currentItem+1
          }
          //page change
          viewPagerRecent.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
              override fun onPageSelected(position: Int) {
                  super.onPageSelected(position)
                  handler.removeCallbacks(runnable)
                  handler.postDelayed(runnable,2000)
              }
          })*/


    }

    private fun setUpTablayout() {
        //tablayout setup
        TabLayoutMediator(tablayoutHome, vpHome) { tab, position ->

            when (position) {

                0 -> tab.text = "Ebooks "
                1 -> tab.text = "Audiobooks "

            }
        }.attach()
    }

    private fun setUpViewPager() {
        //viewpager setup
        val homeAdapter = activity?.let { HomeVPAdapter(it) }
        vpHome.adapter = homeAdapter
    }


    override fun onShowRecentBookBanner(list: List<BookVO>) {
        mRecentBookBannerAdapter.setNewData(list)
    }

    override fun onNavigateBookDetail() {

    }


    override fun showErrorMsg(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
    }

}