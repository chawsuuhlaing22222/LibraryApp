package com.padc.csh.libraryapp.mvp.presenters

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.libraryapp.data.models.LibraryModel
import com.padc.csh.libraryapp.data.models.LibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.mvp.views.BookDetailView
import com.padc.csh.libraryapp.mvp.views.YourBookView

class BookDetailPresenterImpl:BookDetailPresenter,ViewModel() {

    //view
    private var bookDetailView: BookDetailView? = null

    //model
    private var mLibraryModel: LibraryModel = LibraryModelImpl
    override fun onInitView(view: BookDetailView) {
      bookDetailView=view
    }

    override fun addBookToLib(bookVO: BookVO) {
        bookVO.isInLibrary=true
        bookVO.recentOpened=true
        mLibraryModel.addSingleBookToLibrary(bookVO)
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    fun getBookFromLib(book: BookVO):BookVO?{
        return mLibraryModel.getBookFromLib(book.title)
    }

    fun removeFromLibrary(bookVO: BookVO) {
        mLibraryModel.removeFromLibrary(bookVO)
        bookVO.recentOpened=true
        addBookToLib(bookVO)
    }

}