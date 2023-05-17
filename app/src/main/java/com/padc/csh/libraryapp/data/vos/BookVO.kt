package com.padc.csh.libraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padc.csh.libraryapp.persistence.typeconverters.BuyLinksTypeConverter


@Entity(tableName = "library")
@TypeConverters(BuyLinksTypeConverter::class)
data class BookVO(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,

    @SerializedName("age_group")
    @ColumnInfo("age_group")
    val ageGroup: String?="",

    @SerializedName("amazon_product_url")
    @ColumnInfo("amazon_product_url")
    val amazonProductUrl: String?="",

    @SerializedName("article_chapter_link")
    @ColumnInfo("article_chapter_link")
    val articleChapterLink: String?="",

    @SerializedName("author")
    @ColumnInfo("author")
    val author: String?="",

    @SerializedName("book_image")
    @ColumnInfo("book_image")
    val bookImage: String?="",

    @SerializedName("book_image_height")
    @ColumnInfo("book_image_height")
    val bookImageHeight: Int?=0,

    @SerializedName("book_image_width")
    @ColumnInfo("book_image_width")
    val bookImageWidth: Int?=0,

    @SerializedName("book_review_link")
    @ColumnInfo("book_review_link")
    val bookReviewLink: String?="",

    @SerializedName("book_uri")
    @ColumnInfo("book_uri")
    val bookUri: String?="",

    @SerializedName("buy_links")
    @ColumnInfo("buy_links")
    val buyLinks: List<BuyLink>?=null,

    @SerializedName("contributor")
    @ColumnInfo("contributor")
    val contributor: String?="",

    @SerializedName("contributor_note")
    @ColumnInfo("contributor_note")
    val contributorNote: String?="",

    @SerializedName("created_date")
    @ColumnInfo("created_date")
    val createdDate: String?="",

    @SerializedName("description")
    @ColumnInfo("description")
    val description: String?="",

    @SerializedName("first_chapter_link")
    @ColumnInfo("first_chapter_link")
    val firstChapterLink: String?="",

    @SerializedName("price")
    @ColumnInfo("price")
    val price: String?="",

    @SerializedName("primary_isbn10")
    @ColumnInfo("primary_isbn10")
    val primaryIsbn10: String?="",

    @SerializedName("primary_isbn13")
    @ColumnInfo("primary_isbn13")
    val primaryIsbn13: String?="",

    @SerializedName("publisher")
    @ColumnInfo("publisher")
    val publisher: String?="",

    @SerializedName("rank")
    @ColumnInfo("rank")
    val rank: Int?=0,

    @SerializedName("rank_last_week")
    @ColumnInfo("rank_last_week")
    val rankLastWeek: Int?=0,

    @SerializedName("sunday_review_link")
    @ColumnInfo("sunday_review_link")
    val sundayReviewLink: String?="",

    @SerializedName("title")
    @ColumnInfo("title")
    val title: String?="",

    @SerializedName("updated_date")
    @ColumnInfo("updated_date")
    val updatedDate: String?="",

    @SerializedName("weeks_on_list")
    @ColumnInfo("weeks_on_list")
    val weeksOnlist: Int?=0,

    @ColumnInfo("categoryName")
    var categoryName: String?="",


    @ColumnInfo("inLibrary")
    var isInLibrary: Boolean?=false,

    @ColumnInfo("recentOpened")
   var recentOpened:Boolean?=false

) :java.io.Serializable{

    data class BuyLink(
        val name: String?,
        val url: String?
    ):java.io.Serializable
}

