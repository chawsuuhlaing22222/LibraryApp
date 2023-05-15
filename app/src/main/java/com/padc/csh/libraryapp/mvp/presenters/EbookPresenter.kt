package com.padc.csh.libraryapp.mvp.presenters

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.mvp.views.EbookView
import com.padc.csh.libraryapp.mvp.views.HomeView

interface EbookPresenter :BasePresenter,BookDelegate{
    fun onInitView(view: EbookView)
    fun addToLibrary(bookVO: BookVO)
    fun removeFromLibrary(bookVO: BookVO)
}