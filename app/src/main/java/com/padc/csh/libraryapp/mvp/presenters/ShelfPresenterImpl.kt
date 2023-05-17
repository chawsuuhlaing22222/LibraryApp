package com.padc.csh.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.libraryapp.data.models.LibraryModel
import com.padc.csh.libraryapp.data.models.LibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.mvp.views.HomeView
import com.padc.csh.libraryapp.mvp.views.ShelfView

class ShelfPresenterImpl:ViewModel(),ShelfPresenter {


    private var mShelfView : ShelfView?=null

    //model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    //booklist
    var bookList = listOf<BookVO>()

    //selectedShelfList
    var selectedShelfList = mutableListOf<ShelveVO>()

    override fun onInitView(view: ShelfView) {
        mShelfView=view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel?.getAllShelf()?.observe(owner){
            mShelfView?.onShowShelfList(it)
        }
    }

    override fun onAddToShelve(bookVO: BookVO) {

        selectedShelfList.forEach {
            var shelfBookList=it.shelfBookListVO
            it.shelfBookListVO?.bookList?.add(bookVO)
            it.img=bookVO.bookImage
            mLibraryModel?.updateShelf(it)
        }
        selectedShelfList= mutableListOf()


    }

    override fun createShelf(shelveVO: ShelveVO) {
        mLibraryModel?.createShelf(shelveVO)
    }

    override fun onMore(shelveVO: ShelveVO) {
      mShelfView?.onShelfDetail(shelveVO)
    }

    override fun selectShelve(shelve: ShelveVO) {
        selectedShelfList.add(shelve)
    }

    override fun unSelectShelve(shelveVO: ShelveVO) {
        selectedShelfList.remove(shelveVO)
    }
}