package com.padc.csh.libraryapp.delegates

import com.padc.csh.libraryapp.data.vos.CategoryVO

interface CategoryDelegate {

    fun onSelectCategory(categoryVO: CategoryVO)
    fun onUnSelectCategory(categoryVO: CategoryVO)
}