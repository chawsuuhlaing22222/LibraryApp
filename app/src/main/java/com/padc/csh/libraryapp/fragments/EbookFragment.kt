package com.padc.csh.libraryapp.fragments

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.activities.AddToShelveActivity
import com.padc.csh.libraryapp.activities.BookDetailActivity
import com.padc.csh.libraryapp.activities.BookListByCategoryActivity
import com.padc.csh.libraryapp.adapters.BookListAdapter
import com.padc.csh.libraryapp.data.models.LibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.mvp.presenters.EbookPresenter
import com.padc.csh.libraryapp.mvp.presenters.EbookPresenterImpl
import com.padc.csh.libraryapp.mvp.presenters.HomePresenterImpl
import com.padc.csh.libraryapp.mvp.views.EbookView
import com.padc.csh.libraryapp.network.responses.BookResponse
import kotlinx.android.synthetic.main.activity_add_to_shelve.view.*
import kotlinx.android.synthetic.main.dialog_book_content_menu.view.*
import kotlinx.android.synthetic.main.dialog_book_content_menu.view.tvAddToShelve
import kotlinx.android.synthetic.main.fragment_ebook.*

class EbookFragment : Fragment(), EbookView {

    lateinit var mEbookPresenter: EbookPresenterImpl
    lateinit var mBookListAdapter: BookListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ebook, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecycler()
        mEbookPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mEbookPresenter = ViewModelProvider(this)[EbookPresenterImpl::class.java]
        mEbookPresenter.onInitView(this)
    }

    private fun setUpRecycler() {
        mBookListAdapter = BookListAdapter(mEbookPresenter)
        rvEbooks.adapter = mBookListAdapter
        rvEbooks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvEbooks.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = 30
            }
        })

    }


    override fun onShowEbookList(list: List<BookResponse.Results.Lists>) {
        mBookListAdapter.setUpBookList(list)
    }

    override fun onShowBookCategoryList(categoryName: BookResponse.Results.Lists) {
        var listOfbooks=Gson().toJson(categoryName)
        startActivity(context?.let { BookListByCategoryActivity.newIntent(it,listOfbooks) })
    }

    override fun onShowDialog(bookVO: BookVO) {
        var dialog = context?.let { BottomSheetDialog(it) }
        var dialogView =
            LayoutInflater.from(context).inflate(R.layout.dialog_book_content_menu, null, false)

        //set data
        context?.let {
            Glide.with(it).load(bookVO.bookImage).into(dialogView.ivBookImgContentMenu)
        }
        dialogView.tvBookNameContentMenu.text = bookVO.title
        dialogView.tvBookAuthorContentMenu.text = bookVO.author

        //check this book is in lib
        var bookFromlib = mEbookPresenter.getBookFromLib(bookVO)
        if (bookFromlib == null) {
            dialogView.tvDeleteFromLb.text = "Add To Library"
            dialogView.ivDeleteFromlb.setImageResource(R.drawable.ic_baseline_add_24)
        }

        //action listener
        //delete from lib
        dialogView.tvDeleteFromLb.setOnClickListener {
            if (bookVO.isInLibrary == true || bookFromlib != null) {
                Toast.makeText(context, "remove from library", Toast.LENGTH_SHORT).show()
                mEbookPresenter.removeFromLibrary(bookVO)
            } else {
                Toast.makeText(context, "add to library", Toast.LENGTH_SHORT).show()
                mEbookPresenter.addToLibrary(bookVO)
            }
            dialog?.cancel()
        }

        //add to shelf
        dialogView.tvAddToShelve.setOnClickListener {
           var bookString=Gson().toJson(bookVO)
            startActivity(context?.let { it1 -> AddToShelveActivity.newIntent(it1,bookString) })
            dialog?.cancel()
        }

        dialog?.setContentView(dialogView)
        dialog?.setCancelable(true)
        dialog?.show()
    }

    override fun onShowBookDetail(bookVO: BookVO) {
        var book=Gson().toJson(bookVO)
        startActivity(context?.let { BookDetailActivity.newIntent(it,book) })
    }


    override fun showErrorMsg(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
    }


}