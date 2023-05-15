package com.padc.csh.libraryapp.activities

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.adapters.RatingPersonAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.mvp.presenters.BookDetailPresenter
import com.padc.csh.libraryapp.mvp.presenters.BookDetailPresenterImpl
import com.padc.csh.libraryapp.mvp.views.BookDetailView
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity(),BookDetailView {

    lateinit var bookDetailPresenter: BookDetailPresenterImpl
    var bookVo:BookVO?=null
    companion object{
        const val IEXTRA_BOOK="book"
        fun newIntent(context: Context,bookVO: String): Intent {

            var intent=Intent(context,BookDetailActivity::class.java)
            intent.putExtra(IEXTRA_BOOK,bookVO)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        setUpPresenter()
        setUPBookData()
        setUpRecycler()
        setUpListener()
        //check this book is in lib

        bookVo?.let {
            var bookFromlib = bookDetailPresenter.getBookFromLib(it)
            if(bookFromlib==null){
                bookDetailPresenter.addBookToLib(it)
            }else{
                it.recentOpened=true
                bookDetailPresenter.removeFromLibrary(it)
            }
        }
    }

    private fun setUpPresenter() {
        bookDetailPresenter = ViewModelProvider(this)[BookDetailPresenterImpl::class.java]
        bookDetailPresenter.onInitView(this)
    }

    private fun setUpListener() {
        ivBackBookDetail.setOnClickListener {
            finish()
        }
    }

    private fun setUPBookData() {
        var toType=object : TypeToken<BookVO?>(){}.type
        var book=intent.getStringExtra(IEXTRA_BOOK)
        bookVo= Gson().fromJson<BookVO>(book,toType)

        Glide.with(this).load(bookVo?.bookImage).into(ivBookCoverInBookDetail)
        tvBookNameInBookDetail.text=bookVo?.title
        tvBookAuthorInBookDetail.text=bookVo?.author
        tvAboutBookInfoInBookDetail.text=bookVo?.description
        tvBookTypeInBookDetail.text="Ebook"

    }

    private fun setUpRecycler() {
        var ratingPersonAdapter=RatingPersonAdapter()
        rvRatingPersonList.adapter=ratingPersonAdapter
        rvRatingPersonList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvRatingPersonList.addItemDecoration(object :RecyclerView.ItemDecoration(){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top=30
            }
        })
    }

    override fun showErrorMsg(errorMsg: String) {

    }
}