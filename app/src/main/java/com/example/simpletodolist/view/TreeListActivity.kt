package com.example.simpletodolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpletodolist.R
import com.example.simpletodolist.databinding.ActivityTreeBinding
import com.example.simpletodolist.databinding.ActivityTreeListBinding

class TreeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreeListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}