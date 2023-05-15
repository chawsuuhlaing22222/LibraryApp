package com.padc.csh.libraryapp.delegates

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.network.responses.BookResponse


interface
BookDelegate {
    fun onTapBook(book:BookVO)
    fun onMore(bookVO: BookVO)
    fun onCategoryMore(categoryName:BookResponse.Results.Lists)



}