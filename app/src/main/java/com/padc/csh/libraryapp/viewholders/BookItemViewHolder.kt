package com.padc.csh.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate

class BookItemViewHolder(var delegate: BookDelegate,itemView: View) : RecyclerView.ViewHolder(itemView) {
   /* init {
        delegate.onTapBook(BookVO(""))
    }*/
}