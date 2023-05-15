package com.padc.csh.libraryapp.mvp.presenters

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.delegates.ShelveDelegate
import com.padc.csh.libraryapp.mvp.views.HomeView
import com.padc.csh.libraryapp.mvp.views.ShelfView

interface ShelfPresenter :BasePresenter,ShelveDelegate{
    fun onInitView(view: ShelfView)
    fun onAddToShelve(bookVO: BookVO)
    fun createShelf(shelveVO: ShelveVO)
}