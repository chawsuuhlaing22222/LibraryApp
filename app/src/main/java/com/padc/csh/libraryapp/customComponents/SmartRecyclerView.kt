package com.padc.csh.libraryapp.customComponents

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SmartRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var emptyView : View? =null

    private val dataObserver= object : AdapterDataObserver() {
        override fun onChanged() {
           checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkIfEmpty()
        }
    }

     fun setEmptyView(view:View){
        emptyView = view
    }

    //check rv items are empty, then set visibility of emptyview and rvview
    fun checkIfEmpty(){

        var isEmpty=adapter?.itemCount ==0
        emptyView?.let {
            it.visibility =if(isEmpty) View.VISIBLE else View.GONE
            visibility=if(isEmpty) View.GONE else View.VISIBLE
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {

        val oldAdapter =getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(dataObserver)

        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(dataObserver)
        checkIfEmpty()
    }

}