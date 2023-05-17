package com.padc.csh.libraryapp.activities

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.jakewharton.rxbinding4.widget.textChanges
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.adapters.ViewAsListAdapter
import com.padc.csh.libraryapp.data.models.GoogleLibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.mvp.presenters.BookSearchPresenter
import com.padc.csh.libraryapp.mvp.presenters.BookSearchPresenterImpl
import com.padc.csh.libraryapp.mvp.views.BookSearchView
import com.padc.csh.libraryapp.network.responses.BookResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_book_search_list.*
import kotlinx.android.synthetic.main.activity_book_search_list.view.*
import kotlinx.android.synthetic.main.view_pod_book_list.view.*
import java.util.concurrent.TimeUnit

class BookSearchListActivity : AppCompatActivity(),BookSearchView {

    lateinit var mBookSearchPresenter:BookSearchPresenter
    lateinit var listAdapter : ViewAsListAdapter
    private var mGoogleLibraryModel = GoogleLibraryModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search_list)

        setUpPresenter()
        setUpRecycle()
        setUpActionListener()
    }

    private fun setUpPresenter() {
        mBookSearchPresenter = ViewModelProvider(this)[BookSearchPresenterImpl::class.java]
        mBookSearchPresenter.initView(this)
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
                  listAdapter.setUpItemList(listOf())
              }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        ivCloseBookSearch.setOnClickListener {
            edtSearchBookName.setText("")
        }

        edtSearchBookName.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap { mGoogleLibraryModel.searchBook(it.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                listAdapter.setUpItemList(it)
            },{
                showErrorMsg(it.localizedMessage ?: "")
            })

    }

    private fun setUpRecycle() {
        listAdapter = ViewAsListAdapter(mBookSearchPresenter)
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

     fun showErrorMsg(error:String){
        Snackbar.make(window.decorView,"$error", Snackbar.LENGTH_SHORT).show()
    }

    override fun goToBookView(bookVO: BookVO) {
        var book=Gson().toJson(bookVO)
        startActivity(BookDetailActivity.newIntent(this,book))
    }
}