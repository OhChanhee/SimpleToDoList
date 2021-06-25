package com.example.simpletodolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simpletodolist.R
import com.example.simpletodolist.databinding.FragmentFreeworksBinding

class FreeWorksFragment : Fragment() {

    private lateinit var binding : FragmentFreeworksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentFreeworksBinding.inflate(layoutInflater)

        return binding.root
    }

}