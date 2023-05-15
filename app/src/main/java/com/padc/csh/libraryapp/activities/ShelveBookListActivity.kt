package com.padc.csh.libraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.adapters.BookItemAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.CategoryVO
import com.padc.csh.libraryapp.data.vos.ShelfBookListVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.delegates.CategoryDelegate
import com.padc.csh.libraryapp.mvp.presenters.ShelfBookListPresenter
import com.padc.csh.libraryapp.mvp.presenters.ShelfBookListPresenterImpl
import com.padc.csh.libraryapp.mvp.presenters.YourBookPresenterImpl
import com.padc.csh.libraryapp.mvp.views.ShelfBookListView
import com.padc.csh.libraryapp.network.responses.BookResponse
import com.padc.csh.libraryapp.utils.showSortByDialog
import com.padc.csh.libraryapp.viewpods.BookListViewPod
import kotlinx.android.synthetic.main.activity_create_shelf.*
import kotlinx.android.synthetic.main.activity_shelve_book_list.*
import kotlinx.android.synthetic.main.dialog_book_content_menu.view.*
import kotlinx.android.synthetic.main.dialog_more_for_booklist_shelve.view.*
import kotlinx.android.synthetic.main.dialog_view_as.view.*
import kotlinx.android.synthetic.main.fragment_your_book.*
import kotlinx.android.synthetic.main.view_pod_book_list.view.*

class ShelveBookListActivity : AppCompatActivity(),ShelfBookListView{

    //var adapter= BookItemAdapter(this)
    lateinit var bookItemAdapter: BookItemAdapter
    var view="small"
    var sort="none"
    lateinit var bookListViewPod:BookListViewPod
    lateinit var mShelfBookListPresenter: ShelfBookListPresenterImpl
    lateinit var shelveVO: ShelveVO
    private var shelfBookList:List<BookVO> = listOf()

    companion object{
        const val IEXTRA_SHELF="shelf"
        fun newIntent(context: Context, shelf: String): Intent {
            var intent=Intent(context,ShelveBookListActivity::class.java)
            intent.putExtra(IEXTRA_SHELF,shelf)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelve_book_list)

        setUpPresenter()
        setUpRecycler()
        setUpListener()
        setUpData()
        mShelfBookListPresenter.onUiReady(this, shelfBookList)
    }

    private fun setUpData() {
        var toType=object : TypeToken<ShelveVO?>(){}.type
        var shelf=intent.getStringExtra(IEXTRA_SHELF)
        shelveVO= Gson().fromJson<ShelveVO>(shelf,toType)
        shelveVO.shelfBookListVO?.bookList?.let {
            shelfBookList=it
        }

        //bind data
        tvShelveTitle.setText(shelveVO.name)
        tvBookCountInShelveBooklist.text=shelfBookList?.size.toString()
        bookListViewPod.setUpData(shelfBookList)

    }

    private fun setUpPresenter() {
        mShelfBookListPresenter = ViewModelProvider(this)[ShelfBookListPresenterImpl::class.java]
        mShelfBookListPresenter.onInitView(this)
    }

    private fun setUpListener() {

        tvShelveTitle.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    var shelf=ShelveVO(tvShelveTitle.text.toString(),shelveVO.img,shelveVO.id, shelveVO.shelfBookListVO
                    )
                    mShelfBookListPresenter.updateShelf(shelf)
                    finish()
                }
                return false;
            }
        })
        ivMoreShelveBooklist.setOnClickListener {
            showShelfDialog()
        }

        bookListViewPod?.ivViewList?.setOnClickListener {
            showDialogViewAs(this,view)
        }

        bookListViewPod?.ivSort?.setOnClickListener {
            showSortByDialog(this)
        }

        bookListViewPod?.flCancelCategoryList?.setOnClickListener {
            bookListViewPod.resetCategory()
            mShelfBookListPresenter.resetCategoryList()
        }
    }

    private fun showDialogViewAs(context: Context?, view:String){
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
                    // dialog?.dismiss()
                    dialog?.cancel()
                }
                R.id.rbSmallGrid -> {
                    this.view="small"
                    bookListViewPod?.setUpRecyclerSmall()
                    //dialog?.dismiss()
                    dialog?.cancel()
                }
                R.id.rbLargeGrid -> {
                    this.view="large"
                    bookListViewPod?.setUpRecyclerLarge()
                    //dialog?.dismiss()
                    dialog?.cancel()
                }

            }
        }

        dialog?.setContentView(dialogView)
        dialog?.setCancelable(true)
        dialog?.show()
    }


    private fun setUpRecycler() {
        bookListViewPod = viewPodBookListInShelveBookList as BookListViewPod
        bookListViewPod?.setUpDelegate(mShelfBookListPresenter,mShelfBookListPresenter)

    }

    override fun onTapBook(bookVO: BookVO) {
        var book=Gson().toJson(bookVO)
        startActivity(BookDetailActivity.newIntent(context =this,book))
    }

    override fun onShowBookContentMenu(bookVO: BookVO) {
        showDialogBookContentMenu(this)
    }

    override fun onShowBookList(bookList: List<BookVO>) {

    }

    override fun onShowCategoryList(bookList: List<BookVO>, categoryList: List<CategoryVO>) {
        bookListViewPod.setUpCategoryData(bookList,categoryList)
    }

    override fun showErrorMsg(errorMsg: String) {

    }



    fun showDialogBookContentMenu(context: Context?){
        var dialog= context?.let { BottomSheetDialog(it) }
        var dialogView= LayoutInflater.from(context).inflate(R.layout.dialog_book_content_menu,null,false)
        dialogView.ivAddToShelve.setOnClickListener {
            startActivity(Intent(context,AddToShelveActivity::class.java))

        }
        dialog?.setContentView(dialogView)
        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun showShelfDialog(){
        var dialog= BottomSheetDialog(this)
        var dialogView= LayoutInflater.from(this).inflate(R.layout.dialog_more_for_booklist_shelve,null,false)
        dialogView.tvRenameShelf.setOnClickListener {
           // startActivity(Intent(this,AddToShelveActivity::class.java))
            tvShelveTitle.isFocusable=true
            tvShelveTitle.requestFocus()
            tvShelveTitle.setSelection(0,tvShelveTitle.length())
            tvShelveTitle.setBackgroundColor(resources.getColor(R.color.white,null))
            dialog.cancel()

        }

        dialogView.tvDeleteShelf.setOnClickListener {
            mShelfBookListPresenter.deleteShelf(shelveVO)
            finish()
            dialog.cancel()
        }
        dialog?.setContentView(dialogView)
        dialog?.setCancelable(true)
        dialog?.show()
    }




}