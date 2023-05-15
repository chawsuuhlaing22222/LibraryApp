package com.padc.csh.libraryapp.persistence.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelfBookListVO
import com.padc.csh.libraryapp.data.vos.ShelveVO

@Dao
interface LibraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleBookToLibrary(bookVO: BookVO)

    @Query("SELECT * FROM library")
    fun getAllBookFromLibrary():LiveData<List<BookVO>>

    @Query("DELETE FROM library where title= :mTitle")
    fun removeFromLibrary(mTitle:String)

    @Query("SELECT * FROM library where title= :mTitle")
    fun getBookFromLibrary(mTitle: String?):BookVO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createShelf(shelveVO: ShelveVO)

    @Update
    fun updateShelfBookList(shelfVO: ShelveVO)

    @Delete
    fun deleteShelf(shelfVO: ShelveVO)

    @Query("SELECT * FROM shelf")
    fun getAllShelf():LiveData<List<ShelveVO>>?

    @Query("SELECT books FROM shelf where id= :shelfId")
    fun getBooksFromSingleShelf(shelfId: Int?):Cursor


}