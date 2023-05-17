package com.padc.csh.libraryapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.padc.csh.libraryapp.R
import kotlinx.android.synthetic.main.dialog_book_content_menu.view.*
import kotlinx.android.synthetic.main.dialog_sort_view.view.*

const val BASE_URL="https://api.nytimes.com/svc/books/v3/"
const val API_GET_OVERVIEW="lists/overview.json"
const val API_KEY="0U7qhEeaB3hne6603pVwQwcpGBROiWCO"
const val API_KEY_PARAM="api-key"

const val BASE_URL_GOOGLE="https://www.googleapis.com/books/v1/"
const val END_POINT_GET_SEARCH="volumes"






















fun showDialogBookContentMenu(context: Context?){
    var dialog= context?.let { BottomSheetDialog(it) }
    var dialogView= LayoutInflater.from(context).inflate(R.layout.dialog_book_content_menu,null,false)
   dialogView.ivAddToShelve.setOnClickListener {
      // Toast.makeText(context, "Add to shelve", Toast.LENGTH_SHORT).show()

   }
    dialog?.setContentView(dialogView)
    dialog?.setCancelable(true)
    dialog?.show()
}

fun showSortByDialog(context: Context?){
    var dialog= context?.let { BottomSheetDialog(it) }
    var dialogView= LayoutInflater.from(context).inflate(R.layout.dialog_sort_view,null,false)
    dialogView.rbAuthor.setOnClickListener {
        Toast.makeText(context, "Author", Toast.LENGTH_SHORT).show()
    }
    dialog?.setContentView(dialogView)
    dialog?.setCancelable(true)
    dialog?.show()
}

fun showDialogViewAs(context: Context?){
    var dialog= context?.let { BottomSheetDialog(it) }
    var dialogView= LayoutInflater.from(context).inflate(R.layout.dialog_view_as,null,false)
    dialog?.setContentView(dialogView)
    dialog?.setCancelable(true)
    dialog?.show()
}
