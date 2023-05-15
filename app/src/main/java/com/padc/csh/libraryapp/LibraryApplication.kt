package com.padc.csh.libraryapp

import android.app.Application
import com.padc.csh.libraryapp.data.models.LibraryModel
import com.padc.csh.libraryapp.data.models.LibraryModelImpl

class LibraryApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        LibraryModelImpl.initDB(applicationContext)
    }
}