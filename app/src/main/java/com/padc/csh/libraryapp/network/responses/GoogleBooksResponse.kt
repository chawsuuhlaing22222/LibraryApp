package com.padc.csh.libraryapp.network.responses

import com.google.gson.annotations.SerializedName


data class GoogleBooksResponse(
    val items: List<Item>?,
    val kind: String?,
    val totalItems: Int?
):java.io.Serializable

data class Item(
    val accessInfo: AccessInfo?,
    val etag: String?,
    val id: String?,
    val kind: String?,
    val saleInfo: SaleInfo?,
    val searchInfo: SearchInfo?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?
):java.io.Serializable

data class AccessInfo(
    val accessViewStatus: String?,
    val country: String?,
    val embeddable: Boolean?,
    val epub: Epub?,
    val pdf: Pdf?,
    val publicDomain: Boolean?,
    val quoteSharingAllowed: Boolean?,
    val textToSpeechPermission: String?,
    val viewability: String?,
    val webReaderLink: String?
):java.io.Serializable

data class Epub(
    val acsTokenLink: String?,
    val isAvailable: Boolean?
):java.io.Serializable

data class Pdf(
    val acsTokenLink: String?,
    val isAvailable: Boolean?
):java.io.Serializable


data class SaleInfo(
    val country: String?,
    val isEbook: Boolean?,
    val saleability: String?
):java.io.Serializable

data class SearchInfo(
    val textSnippet: String?
):java.io.Serializable

data class VolumeInfo(
    val allowAnonLogging: Boolean?,
    val authors: List<String>?,
    val averageRating: Int?,
    val canonicalVolumeLink: String?,
    val categories: List<String>?,
    val contentVersion: String?,
    val description: String?,
    val imageLinks: ImageLinks?,
    val industryIdentifiers: List<IndustryIdentifier>?,
    val infoLink: String?,
    val language: String?,
    val maturityRating: String?,
    val pageCount: Int?,
    val panelizationSummary: PanelizationSummary?,
    val previewLink: String?,
    val printType: String?,
    val publishedDate: String?,
    val publisher: String?,
    val ratingsCount: Int?,
    val readingModes: ReadingModes?,
    val subtitle: String?,
    val title: String?
):java.io.Serializable

data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
):java.io.Serializable

data class IndustryIdentifier(
    val identifier: String?,
    val type: String?
):java.io.Serializable

data class PanelizationSummary(
    val containsEpubBubbles: Boolean?,
    val containsImageBubbles: Boolean?
):java.io.Serializable

data class ReadingModes(
    val image: Boolean?,
    val text: Boolean?
):java.io.Serializable


