package com.padc.csh.libraryapp.network

import com.padc.csh.libraryapp.network.responses.BookResponse
import com.padc.csh.libraryapp.utils.API_GET_OVERVIEW
import com.padc.csh.libraryapp.utils.API_KEY
import com.padc.csh.libraryapp.utils.API_KEY_PARAM
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LibraryApi {

    @GET(API_GET_OVERVIEW)
    fun getAllOverviewBookList(
       @Query(API_KEY_PARAM)  api_key:String= API_KEY
    ):Observable<BookResponse>
}