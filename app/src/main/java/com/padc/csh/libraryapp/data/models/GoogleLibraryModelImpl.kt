package com.padc.csh.libraryapp.data.models

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.network.responses.Item
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object GoogleLibraryModelImpl:GoogleBaseModel(),GoogleLibraryModel {

    override fun searchBook(name: String): Observable<MutableList<BookVO>> {
       return  return mTheLibraryApi.searchBookList(name)
           .map { it.items ?: listOf() }
           .flatMapIterable {
                   it->it
           }
           .map {
                   googlebook->

               BookVO(
                   author = googlebook.volumeInfo?.authors?.toList()?.joinToString(",") ?: "",
                   bookImage = googlebook.volumeInfo?.imageLinks?.thumbnail,
                   bookReviewLink = googlebook.volumeInfo?.previewLink,
                   description = googlebook.volumeInfo?.description,
                   bookUri = googlebook.selfLink,
                   publisher = googlebook.volumeInfo?.publisher,
                   title = googlebook.volumeInfo?.title

               )
           }.toList().toObservable()
           .onErrorResumeNext { Observable.just(listOf()) }
           .subscribeOn(Schedulers.io())

    }

    fun search(name:String):Observable<MutableList<BookVO>>{
        return mTheLibraryApi.searchBookList(name)
            .map { it.items ?: listOf() }
            .flatMapIterable {
                it->it
            }
            .map {
                googlebook->
                val se=","
                BookVO(
                    author = googlebook.volumeInfo?.authors?.toList()?.joinToString { se } ?: "",
                    bookImage = googlebook.volumeInfo?.imageLinks?.smallThumbnail,
                    bookReviewLink = googlebook.volumeInfo?.previewLink,
                    description = googlebook.volumeInfo?.description,
                    bookUri = googlebook.selfLink,
                    publisher = googlebook.volumeInfo?.publisher,
                    title = googlebook.volumeInfo?.title

                )
            }.toList().toObservable()
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())

    }


}