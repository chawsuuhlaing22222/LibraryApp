package com.padc.csh.libraryapp.adapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.network.responses.BookResponse
import com.padc.csh.libraryapp.viewholders.BookListViewHolder
import kotlinx.android.synthetic.main.view_holder_book_list.view.*

class BookListAdapter(var delegate: BookDelegate): RecyclerView.Adapter<BookListViewHolder>() {

   var itemList:List<BookResponse.Results.Lists> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder{
       var itemView=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book_list,parent,false)
        return BookListViewHolder(delegate,itemView)
    }

    override fun onBindViewHolder(holder:BookListViewHolder, position: Int) {

        var list=itemList.get(position)
        holder.itemView.tvCategoryName.text=list.list_name

        holder.itemView.ivCategoryDetail.setOnClickListener {
          //delegate.onCategoryMore(list.list_name)
            delegate.onCategoryMore(list)
        }
        setUpRecycler(holder, list.books)


    }

    private fun setUpRecycler(holder: RecyclerView.ViewHolder,bookList:List<BookVO>) {
        var adapter=BookItemAdapter(delegate = delegate)
        holder.itemView.rvBookList.adapter=adapter
        adapter.setUPItemlist(bookList)
        holder.itemView.rvBookList.layoutManager = LinearLayoutManager(
            holder.itemView.context,LinearLayoutManager.HORIZONTAL,false)
        holder.itemView.rvBookList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.left = 20;


                // Add top margin only for the first item to avoid double space between items
                if(parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = 0;
                }
            }
        })


    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    fun setUpBookList(lists:List<BookResponse.Results.Lists>){
        itemList = lists
        notifyDataSetChanged()
    }
}