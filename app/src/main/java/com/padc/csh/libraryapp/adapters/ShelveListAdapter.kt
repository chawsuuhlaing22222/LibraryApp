package com.padc.csh.libraryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.delegates.ShelveDelegate
import com.padc.csh.libraryapp.viewholders.BookItemViewHolder
import com.padc.csh.libraryapp.viewholders.ShelveViewHolder
import kotlinx.android.synthetic.main.activity_shelve_book_list.view.*
import kotlinx.android.synthetic.main.fragment_your_shelve.view.*
import kotlinx.android.synthetic.main.view_holder_shelve_item.view.*

class ShelveListAdapter(var shelveDelegate: ShelveDelegate):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var flag=1
    private var itemList:List<ShelveVO> = listOf()
    private var selectedShelve:MutableList<ShelveVO> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_shelve_item,parent,false)
        return ShelveViewHolder(shelveDelegate,itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var shelf=itemList.get(position)
        if(shelf.img!=null){
           Glide.with(holder.itemView.context).load(shelf.img).into(holder.itemView.ivBookInShelve)
        }else{
            holder.itemView.ivBookInShelve.setImageResource(R.drawable.ic_baseline_insert_drive_file_24)
        }
        holder.itemView.tvShelfNameInShelfItem.setText(shelf.name.toString())
        shelf.shelfBookListVO?.bookList?.count()?.let {
            holder.itemView.tvBookCountInShelfItem.text="$it Books"
        }

        if(flag==1){
          holder.itemView.cbShelve.visibility=View.VISIBLE
        }else{
            holder.itemView.cbShelve.visibility=View.GONE
            holder.itemView.ivMoreShelve.visibility=View.VISIBLE
        }

        holder.itemView.ivMoreShelve.setOnClickListener {
            shelveDelegate.onMore(shelf)
        }

        holder.itemView.cbShelve.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
               if(isChecked){
                   shelveDelegate.selectShelve(shelf)
               }else{
                   shelveDelegate.unSelectShelve(shelf)
               }
            }

        })
    }

    fun setCheckFlag(flag:Int){
      this.flag=flag
    }

    override fun getItemCount(): Int {
       return  itemList.size
    }

    fun setItemList(shelve:List<ShelveVO>){
        itemList = shelve
        notifyDataSetChanged()
    }
}