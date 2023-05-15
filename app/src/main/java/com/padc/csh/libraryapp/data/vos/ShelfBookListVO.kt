package com.padc.csh.libraryapp.data.vos

import androidx.room.TypeConverters
import com.padc.csh.libraryapp.persistence.typeconverters.BookListTypeConverter

@TypeConverters(
    BookListTypeConverter::class
)
data class ShelfBookListVO (
    var bookList:MutableList<BookVO>?
        )