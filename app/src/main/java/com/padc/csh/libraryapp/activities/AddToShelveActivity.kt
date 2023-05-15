package com.padc.csh.libraryapp.activities

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.adapters.ShelveListAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.delegates.ShelveDelegate
import com.padc.csh.libraryapp.mvp.presenters.ShelfPresenterImpl
import com.padc.csh.libraryapp.mvp.views.ShelfView
import kotlinx.android.synthetic.main.activity_add_to_shelve.*

class AddToShelveActivity : AppCompatActivity(),ShelfView {

    lateinit var mAdapter :ShelveListAdapter
    lateinit var mShelfPresenter: ShelfPresenterImpl
    lateinit var bookToAdd:BookVO
    companion object{
        const val IEXTRA_BOOK_TO_ADD="BOOK_TO_ADD_TO_SHELF"
        fun newIntent(context: Context,book:String):Intent{
            var  intent=Intent(context,AddToShelveActivity::class.java)
            intent.putExtra(IEXTRA_BOOK_TO_ADD,book)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelve)

        getIntentExtra()
        setUpPresenter()
        setUpRecycler()
        setUpListener()
        mShelfPresenter.onUiReady(this)
    }

    private fun getIntentExtra() {
        var bookString=intent.getStringExtra(IEXTRA_BOOK_TO_ADD)
        var toType=object : TypeToken<BookVO?>(){}.type
        bookToAdd=Gson().fromJson<BookVO>(bookString,toType)
    }

    private fun setUpPresenter() {
        mShelfPresenter=ViewModelProvider(this)[ShelfPresenterImpl::class.java]
        mShelfPresenter.onInitView(this)
    }

    private fun setUpListener() {
        ivCloseInAddToShelve.setOnClickListener {
           mShelfPresenter.onAddToShelve(bookToAdd)
            finish()
        }
    }

    private fun setUpRecycler() {
        mAdapter=ShelveListAdapter(mShelfPresenter)
        rvShelveList.adapter =mAdapter
        rvShelveList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val divider = DividerItemDecoration(
            baseContext,
            (rvShelveList.layoutManager as LinearLayoutManager).orientation
        )
       // divider.setDrawable(AppCompatResources.getDrawable(this, R.drawable.bg_separator_line)!!)
        rvShelveList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top=40
                //outRect.
            }
        })
        //rvShelveList.addItemDecoration(divider)
    }


    override fun onShowShelfList(shelve: List<ShelveVO>) {
        mAdapter.setItemList(shelve)
    }

    override fun onShelfDetail(shelveVO: ShelveVO) {

    }

    override fun showErrorMsg(errorMsg: String) {

    }


}