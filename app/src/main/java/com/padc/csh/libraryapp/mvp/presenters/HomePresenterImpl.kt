package com.padc.csh.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.libraryapp.data.models.LibraryModel
import com.padc.csh.libraryapp.data.models.LibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.mvp.views.HomeView
import com.padc.csh.libraryapp.network.responses.BookResponse

class HomePresenterImpl : ViewModel(), HomePresenter {

    //view
    private var mHomeView: HomeView? = null

    //model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    //booklist
    var bookList = listOf<BookVO>()

    override fun onInitView(view: HomeView) {
        mHomeView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

        mLibraryModel.getAllBookFromLibrary()?.observe(owner) {
            mHomeView?.onShowRecentBookBanner(it)
        }

    }

    override fun onTapBook(book: BookVO) {

    }

    override fun onMore(bookVO: BookVO) {
         mHomeView?.showDialog(bookVO)
    }

    override fun onCategoryMore(categoryName: BookResponse.Results.Lists) {

    }

    override fun addToLibrary(bookVO: BookVO) {
        mLibraryModel.addSingleBookToLibrary(bookVO)
    }

    fun getBookFromLib(book: BookVO):BookVO?{
        return mLibraryModel.getBookFromLib(book.title)
    }

    override fun removeFromLibrary(bookVO: BookVO) {
        mLibraryModel.removeFromLibrary(bookVO)
    }
}