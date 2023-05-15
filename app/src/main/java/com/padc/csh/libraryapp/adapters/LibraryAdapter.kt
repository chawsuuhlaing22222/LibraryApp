package com.padc.csh.libraryapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padc.csh.libraryapp.fragments.YourBookFragment
import com.padc.csh.libraryapp.fragments.YourShelveFragment

class LibraryAdapter(fragment:Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
      return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0->YourBookFragment()
            1->YourShelveFragment()
            else->YourShelveFragment()
        }
    }
}