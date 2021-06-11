package com.example.simpletodolist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.simpletodolist.databinding.ActivityMainBinding
import com.example.simpletodolist.ui.dashboard.BusyWorksFragment
import com.example.simpletodolist.ui.home.FreeWorksFragment
import com.example.simpletodolist.ui.notifications.WishWorksFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tabTitleList = arrayOf("BusyWorks","FreeWorks","WishWorks")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentAdapter = FragmentAdapter(this)
        fragmentAdapter.addFragment(BusyWorksFragment())
        fragmentAdapter.addFragment(FreeWorksFragment())
        fragmentAdapter.addFragment(WishWorksFragment())
        binding.viewPager.adapter = fragmentAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

        })

        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab,position ->
            tab.text = tabTitleList[position]
        }.attach()


    }
}