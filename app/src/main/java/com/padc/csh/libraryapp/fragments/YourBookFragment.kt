package com.padc.csh.libraryapp.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.activities.AddToShelveActivity
import com.padc.csh.libraryapp.activities.BookDetailActivity
import com.padc.csh.libraryapp.adapters.BookItemAdapter
import com.padc.csh.libraryapp.adapters.ViewAsListAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.CategoryVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.mvp.presenters.YourBookPresenter
import com.padc.csh.libraryapp.mvp.presenters.YourBookPresenterImpl
import com.padc.csh.libraryapp.mvp.views.YourBookView
import com.padc.csh.libraryapp.utils.showDialogBookContentMenu
import com.padc.csh.libraryapp.utils.showDialogViewAs
import com.padc.csh.libraryapp.utils.showSortByDialog
import com.padc.csh.libraryapp.viewpods.BookListViewPod
import kotlinx.android.synthetic.main.dialog_book_content_menu.view.*
import kotlinx.android.synthetic.main.dialog_sort_view.view.*
import kotlinx.android.synthetic.main.dialog_view_as.*
import kotlinx.android.synthetic.main.dialog_view_as.view.*
import kotlinx.android.synthetic.main.dialog_view_as.view.rgRadioButtons
import kotlinx.android.synthetic.main.fragment_your_book.*
import kotlinx.android.synthetic.main.view_pod_book_list.view.*
class YourBookFragment : Fragment(),YourBookView {

    lateinit var bookItemAdapter: BookItemAdapter
    lateinit var yourBookPresenter: YourBookPresenter
    var view="small"
    var sort="none"
    lateinit var bookListViewPod:BookListViewPod

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecycler()
        setUpListener()
        yourBookPresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        yourBookPresenter = ViewModelProvider(this)[YourBookPresenterImpl::class.java]
        yourBookPresenter.onInitView(this)
    }

    private fun setUpListener() {

        bookListViewPod?.ivViewList?.setOnClickListener {
            showDialogViewAs(context,view)
        }

        bookListViewPod?.ivSort?.setOnClickListener {
            showSortByDialog(context)
        }

        bookListViewPod?.flCancelCategoryList?.setOnClickListener {
            bookListViewPod.resetCategory()
            yourBookPresenter.resetCategoryList()
        }
    }

    private fun showDialogViewAs(context: Context?,view:String){
        var dialog= context?.let { BottomSheetDialog(it) }
        var dialogView= LayoutInflater.from(context).inflate(R.layout.dialog_view_as,null,false)
        //list
        when(view){
            "small"->{
                dialogView.rbSmallGrid.isChecked=true
            }

            "large"->{
                dialogView.rbLargeGrid.isChecked=true
            }

            else->{
                dialogView.rbList.isChecked=true
            }
        }
        dialogView.rgRadioButtons.setOnCheckedChangeListener { rg, checkId ->
            when (checkId) {
                R.id.rbList -> {
                    this.view ="list"
                    bookListViewPod?.changeAsList()
                    dialog?.cancel()
                }
                R.id.rbSmallGrid -> {
                    this.view="small"
                 bookListViewPod?.setUpRecyclerSmall()
                    dialog?.cancel()
                }
                R.id.rbLargeGrid -> {
                    this.view="large"
                    bookListViewPod?.setUpRecyclerLarge()
                    dialog?.cancel()
                }

            }
        }

        dialog?.setContentView(dialogView)
        dialog?.setCancelable(true)
        dialog?.show()
    }

    private fun setUpRecycler() {
        bookListViewPod = viewPodBookList as BookListViewPod
        bookListViewPod.setUpDelegate(yourBookPresenter,yourBookPresenter)
    }

    override fun onTapBook(bookVO: BookVO) {
        var book= Gson().toJson(bookVO)
        startActivity(context?.let { BookDetailActivity.newIntent(it,book) })
    }

    override fun onShowBookContentMenu(bookVO: BookVO) {
        showDialogBookContentMenu(context,bookVO)
    }

    override fun onShowCategoryList(bookList: List<BookVO>, categoryList: List<CategoryVO>) {
        bookListViewPod.setUpCategoryData(bookList,categoryList)
    }

    fun showDialogBookContentMenu(context: Context?,bookVO: BookVO){
        var dialog= context?.let { BottomSheetDialog(it) }
        var dialogView= LayoutInflater.from(context).inflate(R.layout.dialog_book_content_menu,null,false)

        dialogView.ivAddToShelve.setOnClickListener {
            var bookString=Gson().toJson(bookVO)
            startActivity(context?.let { it1 -> AddToShelveActivity.newIntent(it1,bookString) })
            dialog?.cancel()
        }
        dialog?.setContentView(dialogView)
        dialog?.setCancelable(true)
        dialog?.show()
    }

    override fun onShowBookList(bookList: List<BookVO>) {
       bookListViewPod.setUpData(bookList)
    }

    override fun showErrorMsg(errorMsg: String) {

    }

    fun showSortByDialog(context: Context?){

        var dialog= context?.let { BottomSheetDialog(it) }
        var dialogView= LayoutInflater.from(context).inflate(R.layout.dialog_sort_view,null,false)
        when(sort){
            "author"->{
                dialogView.rbAuthor.isChecked=true
            }

            "title"->{
                dialogView.rbTitle.isChecked=true
            }

            "recent"->{
                dialogView.rbRecentlyOpened.isChecked=true
            }
            else->{
                dialogView.rbAuthor.isChecked=false
                dialogView.rbTitle.isChecked=false
                dialogView.rbRecentlyOpened.isChecked=false
            }
        }

        dialogView.rbAuthor.setOnClickListener {
            sort="author"
            yourBookPresenter.onSortByAuthor()
            dialog?.cancel()
        }

        dialogView.rbTitle.setOnClickListener {
            sort="title"
            yourBookPresenter.onSortByTitle()
            dialog?.cancel()
        }

        dialogView.rbRecentlyOpened.setOnClickListener {
            sort="recent"
            yourBookPresenter.onSortByRecentOpen()
            dialog?.cancel()
        }

        dialog?.setContentView(dialogView)
        dialog?.setCancelable(true)
        dialog?.show()
    }

}