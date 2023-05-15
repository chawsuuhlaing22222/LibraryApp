package com.padc.csh.libraryapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.fragments.HomeFragment
import com.padc.csh.libraryapp.fragments.LibraryFragment
import kotlinx.android.synthetic.main.activity_book_list_by_category.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.appbar
import kotlinx.android.synthetic.main.view_search_bar.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initially set home frag
        switchFragment(HomeFragment())
        setUpListener()
    }

    override fun onResume() {
        super.onResume()
        appbar.edtSearchBook.clearFocus()
    }

    private fun setUpListener() {
        //edit search text action
        appbar.edtSearchBook.clearFocus()
        appbar.edtSearchBook.setOnTouchListener(object :View.OnTouchListener{
            override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
                when (event?.getAction()) {
                    MotionEvent.ACTION_DOWN -> {startActivity(Intent(this@MainActivity,BookSearchListActivity::class.java))}
                    MotionEvent.ACTION_UP -> {}
                }
                return true
            }
        })

        //tab action
        bottom_nav.setOnItemSelectedListener(object :NavigationBarView.OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when(item.itemId){
                    R.id.nav_home->{
                        switchFragment(HomeFragment())
                        return true
                    }

                    R.id.nav_library->{
                        switchFragment(LibraryFragment())
                        return true
                    }
                }
                return false
            }

        })

    }


    private fun switchFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment).commit()
    }
}