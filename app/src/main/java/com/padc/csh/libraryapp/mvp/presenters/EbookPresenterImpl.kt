package com.padc.csh.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.libraryapp.data.models.LibraryModel
import com.padc.csh.libraryapp.data.models.LibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.mvp.views.EbookView
import com.padc.csh.libraryapp.mvp.views.HomeView
import com.padc.csh.libraryapp.network.responses.BookResponse

class EbookPresenterImpl:ViewModel(),EbookPresenter {

    private var mEbookView:EbookView?=null
    private var mLibraryModel:LibraryModel=LibraryModelImpl
    var ebookList:List<BookVO> = listOf()

    override fun onInitView(view: EbookView) {
      mEbookView=view
    }

    override fun onUiReady(owner: LifecycleOwner) {

         mLibraryModel.getOverviewBookList(onSuccess = {
            var eblist=it
             mEbookView?.onShowEbookList(it)
         }, onFailure = {
             mEbookView?.showErrorMsg(it)
         })
    }

    override fun onTapBook(book: BookVO) {
        mEbookView?.onShowBookDetail(book)
    }

    override fun onMore(bookVO: BookVO) {
       mEbookView?.onShowDialog(bookVO)
    }

    override fun onCategoryMore(categoryName: BookResponse.Results.Lists) {
        mEbookView?.onShowBookCategoryList(categoryName)
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