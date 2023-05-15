package com.padc.csh.libraryapp.data.vos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter
import java.util.Calendar

/*
@Entity("recents")
@TypeConverters()

var
data class RecentlyOpenVO (
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    var id:Int?,

    @ColumnInfo("books")
    var books:List<BookVO>?
        )*/
@RequiresApi(Build.VERSION_CODES.O)
fun main(){
   /* var time=
    print(time)

    var calendarTime="2023-05-14T01:01:02.487405500"
    var formatter:DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    var dateTime:LocalDateTime = LocalDateTime.parse(calendarTime, formatter);


    print("$time = $dateTime")*/
}



