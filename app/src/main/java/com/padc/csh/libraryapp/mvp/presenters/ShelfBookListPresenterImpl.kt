package com.padc.csh.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.libraryapp.data.models.LibraryModel
import com.padc.csh.libraryapp.data.models.LibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.CategoryVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.mvp.views.ShelfBookListView
import com.padc.csh.libraryapp.mvp.views.YourBookView
import com.padc.csh.libraryapp.network.responses.BookResponse
class ShelfBookListPresenterImpl:ViewModel(),ShelfBookListPresenter {

    //view
    private var mShelfBookListView: ShelfBookListView? = null

    //model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    //booklist
    var bookList = listOf<BookVO>()
    var bookListByCategory = mutableListOf<BookVO>()

    //categoryList for select from in booklist vp
    var selectedCategoryList = mutableListOf<CategoryVO>()

    override fun onInitView(view: ShelfBookListView) {
        mShelfBookListView = view
    }

    override fun onSortByAuthor() {
        var ascendingBookList=bookList.sortedBy { bookVO ->bookVO.author  }
        mShelfBookListView?.onShowBookList(ascendingBookList)
    }

    override fun onSortByTitle() {
        var ascendingBookList=bookList.sortedBy { bookVO ->bookVO.title  }
        mShelfBookListView?.onShowBookList(ascendingBookList)
    }

    override fun resetCategoryList() {
        bookListByCategory = mutableListOf<BookVO>()
        selectedCategoryList= mutableListOf()
        mShelfBookListView?.onShowBookList(bookList)
    }

    override fun onSortByRecentOpen() {

        if(!bookList.isNullOrEmpty()){

            var booksRecent:ArrayList<BookVO> = arrayListOf()
            var blist= bookList.filter { bookVO ->  bookVO.recentOpened==true}.sortedByDescending { bookVO -> bookVO.id }
            if(!blist.isNullOrEmpty() ){
                booksRecent.addAll(blist)
            }

            var blistNotOpen=
                bookList.filter { bookVO -> (bookVO.recentOpened ==false)}.sortedBy { bookVO -> bookVO.id }
            if(!blistNotOpen.isNullOrEmpty() ){

                booksRecent.addAll(blistNotOpen)

            }
            mShelfBookListView?.onShowBookList(booksRecent)
        }

    }

    override fun onUiReady(owner: LifecycleOwner, bookLists: List<BookVO>) {
        bookList=bookLists
    }

    override fun updateShelf(shelf: ShelveVO) {
        mLibraryModel?.updateShelf(shelf)
    }

    override fun deleteShelf(shelf: ShelveVO) {
        mLibraryModel?.deleteShelf(shelf)
    }

    override fun onTapBook(book: BookVO) {
       mShelfBookListView?.onTapBook(book)
    }

    override fun onMore(bookVO: BookVO) {

    }

    override fun onCategoryMore(categoryName: BookResponse.Results.Lists) {

    }

    override fun onSelectCategory(categoryVO: CategoryVO) {
        categoryVO.isSelected=true
        selectedCategoryList.add(categoryVO)
        bookListByCategory.addAll(bookList.filter {it.categoryName == categoryVO.name })
        mShelfBookListView?.onShowCategoryList(bookListByCategory,selectedCategoryList)
    }

    override fun onUnSelectCategory(categoryVO: CategoryVO) {
        //categoryVO.isSelected=false
        selectedCategoryList.remove(categoryVO)
        bookListByCategory=bookListByCategory.filter {it.categoryName != categoryVO.name } as MutableList<BookVO>
        if(selectedCategoryList.isNullOrEmpty() || bookListByCategory.isNullOrEmpty()){
            mShelfBookListView?.onShowBookList(bookList)
        }else{
            mShelfBookListView?.onShowCategoryList(bookListByCategory,selectedCategoryList)
        }
    }
}