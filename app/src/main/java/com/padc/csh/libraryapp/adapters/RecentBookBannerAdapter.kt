package com.padc.csh.libraryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.viewholders.RecentBookBannerViewHolder
import kotlinx.android.synthetic.main.view_holder_book_item.view.*
import kotlinx.android.synthetic.main.view_holder_recent_book.view.*

class RecentBookBannerAdapter(private val delegate: BookDelegate): RecyclerView.Adapter<RecentBookBannerViewHolder>() {

    private var bannerList:List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentBookBannerViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recent_book,parent,false)
        return RecentBookBannerViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: RecentBookBannerViewHolder, position: Int) {

        var book=bannerList.get(position)
        Glide.with(holder.itemView.context).load(book.bookImage).into(holder.itemView.ivBookCoverImgInRecent)

        book.bookImageWidth?.let {
            holder.itemView.ivBookCoverImgInRecent.layoutParams.width== it
        }

        book.bookImageHeight?.let {
            holder.itemView.ivBookCoverImgInRecent.layoutParams.height =it
        }

        holder.itemView.flAudioIconRecent.visibility=View.GONE
        holder.itemView.setOnClickListener {
           delegate.onTapBook(book)
        }

        holder.itemView.ivMoreRecent.setOnClickListener {
            delegate.onMore(book)
        }
    }

    override fun getItemCount(): Int {
       // return bannerList.size
        return bannerList.size
    }

    fun setNewData(it: List<BookVO>) {
        bannerList=it
        notifyDataSetChanged()
    }
}