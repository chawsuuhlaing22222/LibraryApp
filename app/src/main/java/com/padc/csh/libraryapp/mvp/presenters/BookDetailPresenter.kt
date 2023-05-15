package com.padc.csh.libraryapp.mvp.presenters

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.mvp.views.BookDetailView

interface BookDetailPresenter:BasePresenter {
   fun onInitView(view:BookDetailView)
   fun addBookToLib(bookVO: BookVO)
}