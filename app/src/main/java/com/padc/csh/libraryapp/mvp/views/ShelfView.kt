package com.padc.csh.libraryapp.mvp.views

import com.padc.csh.libraryapp.data.vos.ShelveVO

interface ShelfView:BaseView {

    fun onShowShelfList(shelve:List<ShelveVO>)
    fun onShelfDetail(shelveVO: ShelveVO)
}