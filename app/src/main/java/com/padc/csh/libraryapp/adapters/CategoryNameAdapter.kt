package com.padc.csh.libraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.data.vos.CategoryVO
import com.padc.csh.libraryapp.delegates.CategoryDelegate
import com.padc.csh.libraryapp.viewholders.CategoryNameViewHolder
import kotlinx.android.synthetic.main.view_holder_category.view.*

class CategoryNameAdapter(var categoryDelegate: CategoryDelegate):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList:List<CategoryVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_category,parent,false)
        return CategoryNameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var category = itemList.get(position)
        holder.itemView.chipCategoryNmae.text=category.name
        holder.itemView.chipCategoryNmae.setOnClickListener {
         /*   if(category.isSelected) {
                categoryDelegate.onUnSelectCategory(category)
            }else{
                categoryDelegate.onSelectCategory(category)
            }*/ //for multi select

            //now this is for single select
            categoryDelegate.onSelectCategory(category)

        }

        if(category.isSelected){
            holder.itemView.chipCategoryNmae.setChipBackgroundColorResource(R.color.colorAccent)
            //holder.itemView.chipCategoryNmae.background=holder.itemView.resources.getDrawable(R.drawable.bg_selected_category_name,null)
            holder.itemView.chipCategoryNmae.setTextColor(
                holder.itemView.resources.getColor(R.color.white,null))

        }else{
            holder.itemView.chipCategoryNmae.setChipBackgroundColorResource(R.color.whiteColorSecondary)
            holder.itemView.chipCategoryNmae.setTextColor(
                holder.itemView.resources.getColor(R.color.colorSecondaryText,null))
        }

    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    fun setItemList(categoryList:List<CategoryVO>){
        itemList=categoryList
        notifyDataSetChanged()
    }
}