package com.padc.csh.libraryapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.activities.BookListByCategoryActivity
import com.padc.csh.libraryapp.adapters.BookListAdapter
import com.padc.csh.libraryapp.data.vos.BookVO
import com.padc.csh.libraryapp.delegates.BookDelegate
import com.padc.csh.libraryapp.network.responses.BookResponse
import kotlinx.android.synthetic.main.fragment_audio_book.*


class AudioBookFragment : Fragment(),BookDelegate {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAudioBookList.adapter=BookListAdapter(this)
        rvAudioBookList.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }

    override fun onTapBook(movie: BookVO) {
        startActivity(Intent(context, BookListByCategoryActivity::class.java))
    }

    override fun onMore(bookVO: BookVO) {

    }

    override fun onCategoryMore(categoryName: BookResponse.Results.Lists) {

    }


}