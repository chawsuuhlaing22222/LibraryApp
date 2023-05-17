package com.padc.csh.libraryapp.data.models

import com.padc.csh.libraryapp.data.vos.BookVO
import io.reactivex.rxjava3.core.Observable

interface GoogleLibraryModel{

    fun searchBook(name:String):Observable<MutableList<BookVO>>
}