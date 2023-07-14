package com.example.movies_app.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager, Fragment: Array<Fragment>, title: Array<String>) : FragmentPagerAdapter(fm) {

    var title = title
    var Fragment  =Fragment
    override fun getCount(): Int {
        return Fragment.size
    }

    override fun getItem(position: Int): Fragment {
        return Fragment.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title.get(position)
    }
}