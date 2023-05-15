package com.padc.csh.libraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.viewholders.RatingPersonViewHolder

class RatingPersonAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_rating_person,parent,false)
        return RatingPersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int=5
}