package com.padc.csh.libraryapp.viewpods

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.libraryapp.adapters.BookItemAdapter
import com.padc.csh.libraryapp.adapters.CategoryNameAdapter
import com.padc.csh.libraryapp.adapters.ViewAsListAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.CategoryVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.delegates.CategoryDelegate
import kotlinx.android.synthetic.main.fragment_your_book.view.*
import kotlinx.android.synthetic.main.view_pod_book_list.view.*

class BookListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    var viewType = "small"
    lateinit var delegate: BookDelegate
    lateinit var bookItemAdapter: BookItemAdapter
    lateinit var categoryNameAdapter: CategoryNameAdapter
    var categoryVoList: MutableList<CategoryVO> = mutableListOf()
    private var bookItemList: List<BookVO> = listOf()

    override fun onFinishInflate() {
        // setUpRecycler()
        super.onFinishInflate()
    }

    fun setUpDelegate(bookDelegate: BookDelegate, categoryDelegate: CategoryDelegate) {
        this.delegate = bookDelegate
        bookItemAdapter = BookItemAdapter(delegate)
        categoryNameAdapter = CategoryNameAdapter(categoryDelegate)
        addItemDecoration()

    }

    private fun setUpCategoryRecycler() {
        rvCategoryList.adapter = categoryNameAdapter
        rvCategoryList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryNameAdapter.setItemList(categoryVoList)
       // rvCategoryList.setEmptyView(flCancelCategoryList)
    }

    private fun addItemDecoration() {
        //large
        rvYourBookListLarge.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                //super.getItemOffsets(outRect, view, parent, state)
                outRect.left = 24
            }
        })

        //small
        rvYourBookListSmall.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                // super.getItemOffsets(outRect, view, parent, state)
                outRect.left = 20
            }
        })

        //list
        rvYourBookListList.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                // super.getItemOffsets(outRect, view, parent, state)
                outRect.top = 24
            }
        })
    }

    fun setUpViewType() {
        //call related recycler view
        when (viewType) {
            "small" -> {
                setUpRecyclerSmall()
            }
            "large" -> {
                setUpRecyclerLarge()
            }
            "list" -> {
                changeAsList()
            }
            else -> {
                setUpRecyclerSmall()
            }
        }
    }

    fun setUpData(bookList: List<BookVO>) {
        bookItemList = bookList
        setUpViewType()

        //set category list
        categoryVoList = mutableListOf()
        var categoryNameList: List<String> =
            bookItemList.map { bookVO -> bookVO.categoryName }.distinct() as List<String>
        categoryNameList.forEach {
            categoryVoList.add(CategoryVO(it, false))
        }
        setUpCategoryRecycler()

    }


    fun resetCategory() {
        categoryVoList.forEach {
            it.isSelected = false
        }
        categoryNameAdapter.setItemList(categoryVoList)
        categoryNameAdapter.notifyDataSetChanged()
    }

    fun setUpRecyclerLarge() {
        viewType = "large"
        rvYourBookListList.visibility = View.GONE
        rvYourBookListLarge.visibility = View.VISIBLE
        rvYourBookListSmall.visibility = View.GONE

        bookItemAdapter.setLargeFlag(true)
        bookItemAdapter.setUPItemlist(bookItemList)
        rvYourBookListLarge.adapter = bookItemAdapter
        rvYourBookListLarge.layoutManager = GridLayoutManager(context, 2)

    }

    fun setUpRecyclerSmall() {
        viewType = "small"
        rvYourBookListList.visibility = View.GONE
        rvYourBookListLarge.visibility = View.GONE
        rvYourBookListSmall.visibility = View.VISIBLE

        bookItemAdapter.setUPItemlist(bookItemList)
        bookItemAdapter.setLargeFlag(false)
        rvYourBookListSmall.adapter = bookItemAdapter
        rvYourBookListSmall.layoutManager = GridLayoutManager(context, 3)

    }


    fun changeAsList() {
        viewType = "list"
        rvYourBookListList.visibility = View.VISIBLE
        rvYourBookListLarge.visibility = View.GONE
        rvYourBookListSmall.visibility = View.GONE

        var adapter = delegate?.let { ViewAsListAdapter(it) }
        adapter?.setUpItemList(bookItemList)
        rvYourBookListList.adapter = adapter
        rvYourBookListList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

    }

    fun setUpCategoryData(bookList: List<BookVO>, categoryList: List<CategoryVO>) {
        bookItemList = bookList
        setUpViewType()
        categoryVoList.forEach { outCat ->
            outCat.isSelected = false

            categoryList.forEach { it ->
                if (outCat.name == it.name) {
                    outCat.isSelected = true
                }
            }
        }
        categoryNameAdapter.setItemList(categoryVoList)
    }

}