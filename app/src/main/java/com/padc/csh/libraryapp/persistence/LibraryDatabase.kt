package com.padc.csh.libraryapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.data.vos.ShelveVO
import com.padc.csh.libraryapp.persistence.dao.LibraryDao
import com.padc.csh.libraryapp.persistence.typeconverters.BookListTypeConverter
import com.padc.csh.libraryapp.persistence.typeconverters.BuyLinksTypeConverter
import com.padc.csh.libraryapp.persistence.typeconverters.ShelfBookListTypeConverter

@TypeConverters(BookListTypeConverter::class,BuyLinksTypeConverter::class,ShelfBookListTypeConverter::class)
@Database(entities = [BookVO::class,ShelveVO::class], version =7, exportSchema = false)
abstract class LibraryDatabase:RoomDatabase() {

    companion object{
        const val DB_NAME="THE_LIBRARY_DB"
        var libraryDB:LibraryDatabase?=null
        fun getLibraryDBInstance(context: Context):LibraryDatabase?{

            when(libraryDB){
                null->{
                    libraryDB= Room.databaseBuilder(context,LibraryDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return libraryDB
        }
    }

    abstract fun libraryDao(): LibraryDao
}