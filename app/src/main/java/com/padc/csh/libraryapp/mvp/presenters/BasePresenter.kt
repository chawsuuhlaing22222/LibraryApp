package com.padc.csh.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface BasePresenter {
    //to show initial data of view
    fun onUiReady(owner: LifecycleOwner)
}