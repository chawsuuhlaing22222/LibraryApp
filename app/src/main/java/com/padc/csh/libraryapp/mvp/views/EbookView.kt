package com.padc.csh.libraryapp.mvp.views

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.network.responses.BookResponse

interface EbookView :BaseView{

    fun onShowEbookList(list: List<BookResponse.Results.Lists>)
    fun onShowBookCategoryList(categoryName: BookResponse.Results.Lists)
    fun onShowDialog(bookVO: BookVO)
    fun onShowBookDetail(bookVO: BookVO)
}