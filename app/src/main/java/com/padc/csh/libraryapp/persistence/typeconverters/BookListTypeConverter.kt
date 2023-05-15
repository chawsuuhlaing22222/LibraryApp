package com.padc.csh.libraryapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.libraryapp.data.vos.BookVO

class BookListTypeConverter {

    @TypeConverter
    fun toString(books:List<BookVO>?):String{
        return  Gson().toJson(books)
    }

    @TypeConverter
    fun toBookList(books: String):List<BookVO>?{
        var toType=object : TypeToken<List<BookVO>?>(){}.type
        return Gson().fromJson(books,toType)
    }
}