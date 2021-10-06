package com.example.simpletodolist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simpletodolist.R
import com.example.simpletodolist.adapter.BusyWorksAdapter
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.MemoItem
import com.example.simpletodolist.databinding.FragmentBusyworksBinding
import kotlinx.coroutines.*


class BusyWorksFragment : Fragment() {

    private lateinit var binding: FragmentBusyworksBinding
    lateinit var busyWorksAdapter: BusyWorksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBusyworksBinding.inflate(layoutInflater)

        readDB()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        readDB()
    }

    private fun readDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val item = AppDataBase.getInstance(requireContext())!!.memoItemDao().getBusyWorksMemo()
            busyWorksAdapter = BusyWorksAdapter(requireContext(),item)
            withContext(Dispatchers.Main){
                binding.busyWorksRecyclerview.adapter = busyWorksAdapter

                busyWorksAdapter.notifyDataSetChanged()
            }

        }

    }
}