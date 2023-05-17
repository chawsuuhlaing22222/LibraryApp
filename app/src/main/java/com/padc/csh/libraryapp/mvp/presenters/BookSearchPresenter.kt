package com.padc.csh.libraryapp.mvp.presenters

import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.mvp.views.BookSearchView

interface BookSearchPresenter:BasePresenter,BookDelegate {
    fun initView(view: BookSearchView)
}