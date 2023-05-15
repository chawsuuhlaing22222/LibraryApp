package com.padc.csh.libraryapp.data.models

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.util.splitToIntList
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelfBookListVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.network.responses.BookResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object LibraryModelImpl:LibraryModel,BaseModel() {


    override fun getOverviewBookList(
        onSuccess:( lists:List<BookResponse.Results.Lists> )-> Unit,
        onFailure: (String) -> Unit
    ) {
         mTheLibraryApi.getAllOverviewBookList()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe( { it ->
                 var bookListFromLib= libraryDb?.libraryDao()?.getAllBookFromLibrary()?.value

                 it.results.lists.forEach {list->
                     list.books.forEach { book->
                         book.categoryName= list.list_name
                         var filterList=bookListFromLib?.filter {b->b.title==book.title}
                         if(!filterList.isNullOrEmpty()){
                             book.id= filterList[0].id
                             book.isInLibrary=true
                             book.recentOpened=true
                         }

                     }

                 }
                 onSuccess(it.results.lists)
             },{
                 onFailure(it.message ?: "")
             })
    }

    override fun addSingleBookToLibrary(bookVO: BookVO) {
         libraryDb?.libraryDao()?.insertSingleBookToLibrary(bookVO)
    }

    override fun getAllBookFromLibrary(): LiveData<List<BookVO>>? {
        return libraryDb?.libraryDao()?.getAllBookFromLibrary()
    }

    override fun removeFromLibrary(bookVo: BookVO) {
        bookVo.title?.let { libraryDb?.libraryDao()?.removeFromLibrary(it) }
    }

    override fun getBookFromLib(title: String?): BookVO? {
        return libraryDb?.libraryDao()?.getBookFromLibrary(title)
    }

    override fun createShelf(shelveVO: ShelveVO) {
      libraryDb?.libraryDao()?.createShelf(shelveVO)
    }

    override fun updateShelf(shelveVO: ShelveVO) {
        var shelf=shelveVO
        libraryDb?.libraryDao()?.updateShelfBookList(shelveVO)
    }

    override fun deleteShelf(shelveVO: ShelveVO) {
       libraryDb?.libraryDao()?.deleteShelf(shelveVO)
    }

    override fun getAllShelf(): LiveData<List<ShelveVO>>? {
    return libraryDb?.libraryDao()?.getAllShelf()
    }

    override fun getBooksFromSingleShelf(shelfId: String): List<BookVO>? {
        var shelfBookListVO=libraryDb?.libraryDao()?.getBooksFromSingleShelf(shelfId.toInt()) as ShelfBookListVO
     return shelfBookListVO.bookList
    }


}