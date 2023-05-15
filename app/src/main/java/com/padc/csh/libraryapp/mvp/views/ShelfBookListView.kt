package com.padc.csh.libraryapp.mvp.views

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.CategoryVO

interface ShelfBookListView:BaseView {
    fun onTapBook(bookVO: BookVO)
    fun onShowBookContentMenu(bookVO: BookVO)
    fun onShowBookList(bookList:List<BookVO>)
    fun onShowCategoryList(bookList: List<BookVO>, categoryList:List<CategoryVO>)
}