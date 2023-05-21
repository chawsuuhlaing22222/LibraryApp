package com.padc.csh.libraryapp.mvp.views

import com.padc.csh.libraryapp.data.vos.BookVO

interface LibraryView {

    fun navigateBookDetail(book: BookVO)
}