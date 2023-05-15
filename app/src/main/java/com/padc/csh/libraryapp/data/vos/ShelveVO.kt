package com.padc.csh.libraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.padc.csh.libraryapp.persistence.typeconverters.BookListTypeConverter
import com.padc.csh.libraryapp.persistence.typeconverters.ShelfBookListTypeConverter

@Entity("shelf")
@TypeConverters(
    BookListTypeConverter::class,
    ShelfBookListTypeConverter::class
)
data class ShelveVO (
    @ColumnInfo("name")
    var name:String?,

    @ColumnInfo("cover_img")
    var img:String?,

    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    var id:Int?,

    @ColumnInfo("books")
    //var books:List<BookVO>?
    var shelfBookListVO: ShelfBookListVO= ShelfBookListVO(bookList = mutableListOf())
)