package com.padc.csh.libraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.padc.csh.libraryapp.R
import com.padc.csh.libraryapp.adapters.HomeVPAdapter
import com.padc.csh.libraryapp.adapters.LibraryAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_library.*


class LibraryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager()
        setUpTablayout()
    }

    private fun setUpTablayout(){
        //tablayout setup
        TabLayoutMediator(tablayoutLibrary,vpLibrary){tab,position->

            when(position){

                0->tab.text="Your books "
                1->tab.text="Your shelve "

            }
        }.attach()
    }

    private fun setUpViewPager(){
        //viewpager setup
        val libraryAdapter= LibraryAdapter(this)
        vpLibrary.adapter=libraryAdapter
    }


}