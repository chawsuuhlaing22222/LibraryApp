package com.padc.csh.libraryapp.mvp.views

import com.padc.csh.libraryapp.data.vos.BookVO

interface HomeView:BaseView {

    fun onShowRecentBookBanner(list:List<BookVO>)
    fun navigateBookDetail(book: BookVO)
    fun showDialog(book:BookVO)
}