package com.padc.csh.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.libraryapp.data.models.LibraryModel
import com.padc.csh.libraryapp.data.models.LibraryModelImpl
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.CategoryVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.mvp.views.HomeView
import com.padc.csh.libraryapp.mvp.views.YourBookView
import com.padc.csh.libraryapp.network.responses.BookResponse

class YourBookPresenterImpl:ViewModel(),YourBookPresenter {

    //view
    private var mYourBookView: YourBookView? = null

    //model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    //booklist
    var bookList = listOf<BookVO>()
    var bookListByCategory = mutableListOf<BookVO>()

    //categoryList for select from in booklist vp
    var selectedCategoryList = mutableListOf<CategoryVO>()



    override fun onInitView(view: YourBookView) {
        mYourBookView = view
    }

    override fun onSortByAuthor() {
        //var ascendingBookList=bookList.sortedBy { bookVO ->bookVO.author  }
        var ascendingBookList=bookList.sortedByDescending { bookVO ->bookVO.author  }
        mYourBookView?.onShowBookList(ascendingBookList)
    }

    override fun onSortByTitle() {
        var ascendingBookList=bookList.sortedBy { bookVO ->bookVO.title  }
        mYourBookView?.onShowBookList(ascendingBookList)
    }

    override fun resetCategoryList() {
        bookListByCategory = mutableListOf<BookVO>()
        selectedCategoryList= mutableListOf()
        mYourBookView?.onShowBookList(bookList)
    }

    override fun onSortByRecentOpen() {

        if(!bookList.isNullOrEmpty()){
            var booksRecent:MutableList<BookVO> = mutableListOf()
            var blist= bookList.filter { bookVO ->  bookVO.recentOpened==true}.sortedByDescending { bookVO -> bookVO.id }
            if(!blist.isNullOrEmpty() ){
                booksRecent =blist as MutableList<BookVO>
            }

            var bookNotRecent:List<BookVO> = listOf()
            var blistNotOpen=    bookList.filter { bookVO -> (bookVO.recentOpened ==true)}.sortedBy { bookVO -> bookVO.id }
            if(!blistNotOpen.isNullOrEmpty() ){
                //bookNotRecent =blistNotOpen as MutableList<BookVO>
                booksRecent.addAll(bookNotRecent)
            }

            mYourBookView?.onShowBookList(booksRecent)
        }

    }



    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getAllBookFromLibrary()?.observe(owner) {
            bookList = it
            mYourBookView?.onShowBookList(it)
        }
    }

    override fun onTapBook(book: BookVO) {

    }

    override fun onMore(bookVO: BookVO) {

    }

    override fun onCategoryMore(categoryName: BookResponse.Results.Lists) {

    }

    override fun onSelectCategory(categoryVO: CategoryVO) {
        categoryVO.isSelected=true
        selectedCategoryList.add(categoryVO)
         bookListByCategory.addAll(bookList.filter {it.categoryName == categoryVO.name })
        mYourBookView?.onShowCategoryList(bookListByCategory,selectedCategoryList)
    }

    override fun onUnSelectCategory(categoryVO: CategoryVO) {
        //categoryVO.isSelected=false
        selectedCategoryList.remove(categoryVO)
       bookListByCategory=bookListByCategory.filter {it.categoryName != categoryVO.name } as MutableList<BookVO>
       if(selectedCategoryList.isNullOrEmpty() || bookListByCategory.isNullOrEmpty()){
           mYourBookView?.onShowBookList(bookList)
       }else{
           mYourBookView?.onShowCategoryList(bookListByCategory,selectedCategoryList)
       }

    }




}