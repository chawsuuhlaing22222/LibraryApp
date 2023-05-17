package com.padc.csh.libraryapp.mvp.presenters

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.mvp.views.HomeView

interface HomePresenter:BasePresenter,BookDelegate{

    fun onInitView(view: HomeView)
    fun addToLibrary(bookVO: BookVO)
    fun removeFromLibrary(bookVO: BookVO)
}