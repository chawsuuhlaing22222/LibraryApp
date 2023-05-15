package com.padc.csh.libraryapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.libraryapp.data.vos.BookVO
import java.lang.ProcessBuilder.Redirect.to

class BuyLinksTypeConverter {

    @TypeConverter
    fun toString(buyLinks:List<BookVO.BuyLink>?):String{
      return  Gson().toJson(buyLinks)
    }

    @TypeConverter
    fun toBuyLink(buyLInkString: String):List<BookVO.BuyLink>?{
        var toType=object : TypeToken<List<BookVO.BuyLink>?>(){}.type
        return Gson().fromJson(buyLInkString,toType)
    }
}