package com.padc.csh.libraryapp.network.responses

import com.padc.csh.libraryapp.data.vos.BookVO

data class BookResponse(
    val copyright: String,
    val num_results: Int,
    val results: Results,
    val status: String
) {
    data class Results(
        val bestsellers_date: String,
        val lists: List<Lists>,
        val next_published_date: String,
        val previous_published_date: String,
        val published_date: String,
        val published_date_description: String
    ) {
        data class Lists(
            val books: List<BookVO>,
            val display_name: String,
            val list_id: Int,
            val list_image: Any,
            val list_image_height: Any,
            val list_image_width: Any,
            val list_name: String,
            val list_name_encoded: String,
            val updated: String
        )
    }
}