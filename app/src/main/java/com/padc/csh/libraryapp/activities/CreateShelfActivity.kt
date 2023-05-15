
package com.padc.csh.libraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.data.vos.ShelfBookListVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.mvp.presenters.ShelfPresenterImpl
import com.padc.csh.libraryapp.mvp.views.ShelfView
import kotlinx.android.synthetic.main.activity_create_shelf.*

class CreateShelfActivity : AppCompatActivity(),ShelfView {

    lateinit var mShelfPresenter: ShelfPresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)

        setUpPresenter()
        setUpListener()
    }


    private fun setUpPresenter() {
        mShelfPresenter = ViewModelProvider(this)[ShelfPresenterImpl::class.java]
        mShelfPresenter.onInitView(this)
    }

    private fun setUpListener() {
        edtShelName.setOnEditorActionListener(object :TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    var shelf=ShelveVO(edtShelName.text.toString(),null,null, ShelfBookListVO(
                        mutableListOf()
                    ))
                    mShelfPresenter.createShelf(shelf)
                  finish()
                }
                return false;
            }
        })
    }

    override fun onShowShelfList(shelve: List<ShelveVO>) {

    }

    override fun onShelfDetail(shelveVO: ShelveVO) {
        TODO("Not yet implemented")
    }

    override fun showErrorMsg(errorMsg: String) {
        TODO("Not yet implemented")
    }
}