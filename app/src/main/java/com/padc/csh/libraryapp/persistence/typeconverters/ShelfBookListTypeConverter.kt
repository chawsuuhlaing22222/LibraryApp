package com.padc.csh.libraryapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelfBookListVO

class ShelfBookListTypeConverter {
    @TypeConverter
    fun toString(books:ShelfBookListVO?):String{
        return  Gson().toJson(books)
    }

    @TypeConverter
    fun toShelfBookList(books: String):ShelfBookListVO?{
        var toType=object : TypeToken<ShelfBookListVO?>(){}.type
        return Gson().fromJson(books,toType)
    }
}