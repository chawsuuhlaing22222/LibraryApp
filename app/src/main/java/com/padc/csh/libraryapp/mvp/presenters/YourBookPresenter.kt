package com.padc.csh.libraryapp.mvp.presenters

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.delegates.CategoryDelegate
import com.padc.csh.libraryapp.mvp.views.HomeView
import com.padc.csh.libraryapp.mvp.views.YourBookView

interface YourBookPresenter:BasePresenter, BookDelegate,CategoryDelegate {

    fun onInitView(view: YourBookView)
    fun onSortByAuthor()
    fun onSortByTitle()
    fun resetCategoryList()
    fun onSortByRecentOpen()
    fun onRemoveFromLib(book:BookVO)


}