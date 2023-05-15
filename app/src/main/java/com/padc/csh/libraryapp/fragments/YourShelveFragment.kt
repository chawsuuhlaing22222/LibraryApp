package com.padc.csh.libraryapp.fragments

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.activities.CreateShelfActivity
import com.padc.csh.libraryapp.activities.ShelveBookListActivity
import com.padc.csh.libraryapp.adapters.ShelveListAdapter
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.delegates.ShelveDelegate
import com.padc.csh.libraryapp.mvp.presenters.ShelfPresenter
import com.padc.csh.libraryapp.mvp.presenters.ShelfPresenterImpl
import com.padc.csh.libraryapp.mvp.views.ShelfView
import kotlinx.android.synthetic.main.activity_add_to_shelve.*
import kotlinx.android.synthetic.main.fragment_your_shelve.*


class YourShelveFragment : Fragment() ,ShelfView{

  lateinit var mShelfPresenter: ShelfPresenterImpl
  lateinit var  mAdapter : ShelveListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_shelve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecycler()
        setUpListener()
        mShelfPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mShelfPresenter = ViewModelProvider(this)[ShelfPresenterImpl::class.java]
        mShelfPresenter.onInitView(this)
    }

    private fun setUpListener() {
        fbtnCreateShelve.setOnClickListener {
            startActivity(Intent(context,CreateShelfActivity::class.java))
        }
    }

    private fun setUpRecycler() {
       mAdapter = ShelveListAdapter(mShelfPresenter)
        rvShelveListInLibray.adapter = mAdapter
        rvShelveListInLibray.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvShelveListInLibray.addItemDecoration(object : RecyclerView.ItemDecoration() {
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

        rvShelveListInLibray.setEmptyView(viewPodEmptyShelve)
    }

    override fun onShowShelfList(shelve: List<ShelveVO>) {
        mAdapter.setCheckFlag(0)
        mAdapter.setItemList(shelve)
    }

    override fun onShelfDetail(shelveVO: ShelveVO) {
        var shelf=Gson().toJson(shelveVO)
        startActivity(context?.let { ShelveBookListActivity.newIntent(it,shelf) })
    }

    override fun showErrorMsg(errorMsg: String) {

    }


}