package com.padc.csh.libraryapp.delegates

import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelveVO

interface ShelveDelegate {

    fun onMore(shelveVO: ShelveVO)
    fun selectShelve(shelve:ShelveVO)
    fun unSelectShelve(shelveVO: ShelveVO)

}