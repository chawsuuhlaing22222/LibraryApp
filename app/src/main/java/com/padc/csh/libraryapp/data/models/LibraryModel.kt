package com.padc.csh.libraryapp.data.models

import android.database.Cursor
import androidx.lifecycle.LiveData
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelfBookListVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.network.responses.BookResponse

interface LibraryModel {

    fun getOverviewBookList(
        onSuccess:(lists:List<BookResponse.Results.Lists>)->Unit,
        onFailure:(String)->Unit
    )

    fun addSingleBookToLibrary(bookVO: BookVO)

    fun getAllBookFromLibrary():LiveData<List<BookVO>>?

    fun removeFromLibrary(bookVo:BookVO)

    fun getBookFromLib(title: String?):BookVO?

    fun createShelf(shelveVO: ShelveVO)

    fun updateShelf(shelveVO: ShelveVO)

    fun deleteShelf(shelveVO: ShelveVO)

    fun getAllShelf():LiveData<List<ShelveVO>>?

    fun getBooksFromSingleShelf(shelfId:String):List<BookVO>?
}