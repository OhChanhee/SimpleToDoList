package com.example.simpletodolist.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simpletodolist.adapter.FreeWorksAdapter
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.databinding.FragmentFreeworksBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FreeWorksFragment : Fragment() {

    private lateinit var binding : FragmentFreeworksBinding
    lateinit var freeWorksAdapter: FreeWorksAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFreeworksBinding.inflate(layoutInflater)

        readDB()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        readDB()
    }

    private fun readDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val item = AppDataBase.getInstance(requireContext())!!.memoItemDao().getFreeWorksMemo()
            freeWorksAdapter = FreeWorksAdapter(requireContext(),item)
            withContext(Dispatchers.Main){
                binding.freeWorksRecyclerview.adapter = freeWorksAdapter

                freeWorksAdapter.notifyDataSetChanged()
            }

        }

    }
}