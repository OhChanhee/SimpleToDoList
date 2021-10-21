package com.example.simpletodolist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simpletodolist.databinding.ActivityTreeBinding

class TreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}