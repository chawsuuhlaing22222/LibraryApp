package com.padc.csh.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.libraryapp.data.models.LibraryModel
import com.padc.csh.libraryapp.data.models.LibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.mvp.views.BookDetailView
import com.padc.csh.libraryapp.mvp.views.BookSearchView
import com.padc.csh.libraryapp.network.responses.BookResponse

class BookSearchPresenterImpl:BookSearchPresenter,ViewModel() {
    //view
    private var bookSearchView: BookSearchView? = null

    //model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    override fun initView(view: BookSearchView) {
       bookSearchView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapBook(book: BookVO) {
        bookSearchView?.goToBookView(book)
    }

    override fun onMore(bookVO: BookVO) {

    }

    override fun onCategoryMore(categoryName: BookResponse.Results.Lists) {

    }
}