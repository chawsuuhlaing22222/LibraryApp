package com.padc.csh.libraryapp.data.models

import com.padc.csh.libraryapp.network.LibraryApi
import com.padc.csh.libraryapp.persistence.LibraryDatabase
import com.padc.csh.libraryapp.utils.BASE_URL
import com.padc.csh.libraryapp.utils.BASE_URL_GOOGLE
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class GoogleBaseModel {

    protected var mTheLibraryApi: LibraryApi

    init {
        val okHttpClient= OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL_GOOGLE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mTheLibraryApi=retrofit.create(LibraryApi::class.java)
    }

}