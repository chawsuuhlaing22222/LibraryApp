package com.padc.csh.libraryapp.activities

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.adapters.BookItemAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.mvp.presenters.EbookPresenterImpl
import com.padc.csh.libraryapp.mvp.views.EbookView
import com.padc.csh.libraryapp.network.responses.BookResponse

import com.padc.csh.libraryapp.utils.*
import kotlinx.android.synthetic.main.activity_book_list_by_category.*
import kotlinx.android.synthetic.main.dialog_book_content_menu.view.*


class BookListByCategoryActivity : AppCompatActivity(), EbookView {

    lateinit var mEbookPresenter: EbookPresenterImpl
    lateinit var mListOfBook:BookResponse.Results.Lists
    lateinit var mAdapter : BookItemAdapter
    companion object{
        const val IEXTRA_LIST_BOOK="list_book"
        fun newIntent(context: Context, listObj: String): Intent {

            var intent=Intent(context,BookListByCategoryActivity::class.java)
            intent.putExtra(IEXTRA_LIST_BOOK,listObj)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list_by_category)


        setUpPresenter()
        setUpRecycler()
        getParamData()
        //not call onUI Readay because booklistbyCategory do not need it
        setUpActionListener()
    }

    private fun setUpActionListener(){
        ivBackBooksByCategory.setOnClickListener {
            finish()
        }

    }

    private fun getParamData() {
        var toType=object : TypeToken<BookResponse.Results.Lists?>(){}.type
        var listOfbook=intent.getStringExtra(IEXTRA_LIST_BOOK)
        mListOfBook= Gson().fromJson<BookResponse.Results.Lists>(listOfbook,toType)

        //set data to recycler
        tvCategoryNameBookListByCate.text=mListOfBook.list_name
        mAdapter.setLargeFlag(true)
        mAdapter.setUPItemlist(mListOfBook.books)
    }

    private fun setUpPresenter() {
        mEbookPresenter = ViewModelProvider(this)[EbookPresenterImpl::class.java]
        mEbookPresenter.onInitView(this)
    }

    private fun setUpRecycler() {
        mAdapter = BookItemAdapter(mEbookPresenter)
        rvBookListByCategory.adapter = mAdapter
        rvBookListByCategory.layoutManager = GridLayoutManager(this, 2)
        rvBookListByCategory.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.left = 20
                outRect.top = 20
            }
        })
    }



    override fun onShowEbookList(list: List<BookResponse.Results.Lists>) {

    }

    override fun onShowBookCategoryList(categoryName: BookResponse.Results.Lists) {

    }

    override fun onShowDialog(bookVO: BookVO) {
        var dialog = BottomSheetDialog(this)
        var dialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_book_content_menu, null, false)

        //set data
        Glide.with(this).load(bookVO.bookImage).into(dialogView.ivBookImgContentMenu)
        dialogView.tvBookNameContentMenu.text = bookVO.title
        dialogView.tvBookAuthorContentMenu.text = bookVO.author

        //check this book is in lib
        var bookFromlib = mEbookPresenter.getBookFromLib(bookVO)
        if (bookFromlib == null) {
            dialogView.tvDeleteFromLb.text = "Add To Library"
            dialogView.ivDeleteFromlb.setImageResource(R.drawable.ic_baseline_add_24)
        }

        //action listener
        dialogView.tvDeleteFromLb.setOnClickListener {
            if (bookVO.isInLibrary == true || bookFromlib != null) {
                Toast.makeText(this, "remove from library", Toast.LENGTH_SHORT).show()
                mEbookPresenter.removeFromLibrary(bookVO)
            } else {
                Toast.makeText(this, "add to library", Toast.LENGTH_SHORT).show()
                mEbookPresenter.addToLibrary(bookVO)
            }
            dialog?.cancel()
        }

        dialog?.setContentView(dialogView)
        dialog?.setCancelable(true)
        dialog?.show()
    }

    override fun onShowBookDetail(bookVO: BookVO) {
        var book= Gson().toJson(bookVO)
        startActivity(BookDetailActivity.newIntent(this,book))
    }

    override fun showErrorMsg(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }
}