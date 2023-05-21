package com.padc.csh.libraryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.viewholders.ListAsViewHolder
import kotlinx.android.synthetic.main.view_holder_list_as_view.view.*

class ViewAsListAdapter(var delegate: BookDelegate):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var searchFlag=0
    var itemList:List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_list_as_view,parent,false)
        return ListAsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var book= itemList.get(position)
        Glide.with(holder.itemView.context).load(book.bookImage).into(holder.itemView.ivBookCoverImgViewAsList)
        holder.itemView.tvAuthorViewAsList.text=book.author
        holder.itemView.tvBookNameViewAsList.text=book.title
       /* if(book.bookImageHeight!=0 && book.bookImageHeight!=null) {
            holder.itemView.ivBookCoverImgViewAsList.layoutParams.height = book.bookImageHeight!!
        }

        if(book.bookImageWidth!=null && book.bookImageWidth!=0) {
            holder.itemView.ivBookCoverImgViewAsList.layoutParams.width = book.bookImageWidth!!
        }*/



        if(searchFlag==1){
            holder.itemView.ivMoreViewAsList.visibility=View.GONE
            holder.itemView.ivDownloadDone.visibility=View.GONE
        }else{
            holder.itemView.ivMoreViewAsList.visibility=View.VISIBLE
            holder.itemView.ivDownloadDone.visibility=View.VISIBLE
        }

        holder.itemView.ivMoreViewAsList.setOnClickListener {
           delegate.onMore(book)
        }

        holder.itemView.flBookCover.setOnClickListener {
            delegate.onTapBook(book)
        }



    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    fun setSearchFlag(flag:Int){
        searchFlag=flag
    }

    fun setUpItemList(bookList: List<BookVO>){
        itemList = bookList
        notifyDataSetChanged()
    }
}