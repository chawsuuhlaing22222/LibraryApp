package com.padc.csh.libraryapp.activities

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.adapters.ViewAsListAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.network.responses.BookResponse
import kotlinx.android.synthetic.main.activity_book_search_list.*
import kotlinx.android.synthetic.main.activity_book_search_list.view.*
import kotlinx.android.synthetic.main.view_pod_book_list.view.*

class BookSearchListActivity : AppCompatActivity(),BookDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search_list)

        setUpRecycle()
        setUpActionListener()
    }

    private fun setUpActionListener() {

        ivBackBookSearch.setOnClickListener {
            finish()
        }

        tabLayoutSearchBook.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
              if( tab?.id ==R.id.tabEbook){
                  Toast.makeText(this@BookSearchListActivity, "E Book", Toast.LENGTH_SHORT).show()
              }else{
                  Toast.makeText(this@BookSearchListActivity, "Audio Book", Toast.LENGTH_SHORT).show()
              }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setUpRecycle() {
        var listAdapter=ViewAsListAdapter(this)
        listAdapter.setSearchFlag(1)
        rvSearchBookList.adapter=listAdapter
        rvSearchBookList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvSearchBookList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = 24
            }
        })
    }

    override fun onTapBook(book: BookVO) {

    }

    override fun onMore(bookVO: BookVO) {

    }

    override fun onCategoryMore(categoryName: BookResponse.Results.Lists) {

    }
}