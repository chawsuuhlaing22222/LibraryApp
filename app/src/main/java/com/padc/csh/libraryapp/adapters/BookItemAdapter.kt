package com.padc.csh.libraryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.viewholders.BookItemViewHolder
import kotlinx.android.synthetic.main.view_holder_book_item.view.*

class BookItemAdapter(var delegate: BookDelegate) :
    RecyclerView.Adapter<BookItemViewHolder>() {

    var itemList: List<BookVO> = listOf()
    private var largeFlag=false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_book_item, parent, false)
        return BookItemViewHolder(delegate, itemView)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {

        var book = itemList.get(position)
        book.bookImageWidth?.let {
            if(largeFlag){
                holder.itemView.ivBookCoverImg.layoutParams.width = it+200
            }else{
                holder.itemView.ivBookCoverImg.layoutParams.width = it
            }

        }

        book.bookImageHeight?.let {
            if(largeFlag){
                holder.itemView.ivBookCoverImg.layoutParams.height = it+200
            }else{
                holder.itemView.ivBookCoverImg.layoutParams.height = it
            }

        }



        Glide.with(holder.itemView.context).load(book.bookImage)
            .into(holder.itemView.ivBookCoverImg)
        holder.itemView.tvBookAuthor.text = book.author
        holder.itemView.tvBookName.text = book.title
        holder.itemView.flAudioIcon.visibility = View.GONE
        holder.itemView.flSaveIcon.visibility = View.GONE


        holder.itemView.ivMore.setOnClickListener{
            delegate.onMore(book)
        }

        holder.itemView.setOnClickListener{
            delegate.onTapBook(book)
        }
    }

    fun setUPItemlist(list: List<BookVO>) {
        itemList = list
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    fun setLargeFlag(flag:Boolean){
        largeFlag = flag
    }

}