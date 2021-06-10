package com.example.simpletodolist.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simpletodolist.R
import com.example.simpletodolist.databinding.FragmentBusyworksBinding


class BusyWorksFragment : Fragment() {


    private lateinit var binding : FragmentBusyworksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBusyworksBinding.inflate(layoutInflater)

        return binding.root
    }


}