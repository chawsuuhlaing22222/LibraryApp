package com.padc.csh.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.delegates.CategoryDelegate
import com.padc.csh.libraryapp.mvp.views.ShelfBookListView
import com.padc.csh.libraryapp.mvp.views.YourBookView

interface ShelfBookListPresenter: BookDelegate, CategoryDelegate {
    fun onInitView(view: ShelfBookListView)
    fun onSortByAuthor()
    fun onSortByTitle()
    fun resetCategoryList()
    fun onSortByRecentOpen()
    fun onUiReady(owner: LifecycleOwner,bookList: List<BookVO>)

    fun updateShelf(shelf: ShelveVO)
    fun deleteShelf(shelf: ShelveVO)
    //fun setBookListForShelf(shelf: ShelveVO)
}