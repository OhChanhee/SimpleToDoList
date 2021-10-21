package com.example.simpletodolist.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity)
{

    private var fragmentList : ArrayList<Fragment> = ArrayList()
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
       return fragmentList.get(position)
    }
    fun addFragment(item : Fragment)
    {
        fragmentList.add(item)
    }
}