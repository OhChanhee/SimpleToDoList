package com.example.simpletodolist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.simpletodolist.databinding.ActivityMainBinding
import com.example.simpletodolist.fragment.BusyWorksFragment
import com.example.simpletodolist.fragment.FreeWorksFragment
import com.example.simpletodolist.fragment.WishWorksFragment
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_action_bar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.app_bar_additem)
        {
            openAddItemActivity()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openAddItemActivity(){
        startActivity(Intent(this,AddActivity::class.java))
    }
}