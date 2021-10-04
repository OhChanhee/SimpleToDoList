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
import com.example.simpletodolist.adapter.FreeWorksAdapter
import com.example.simpletodolist.adapter.WishWorksAdapter
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.databinding.FragmentWishworksBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishWorksFragment : Fragment() {

    private lateinit var binding : FragmentWishworksBinding
    lateinit var wishWorksAdapter: WishWorksAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?):
            View {

        binding = FragmentWishworksBinding.inflate(layoutInflater)
        readDB()

        return binding.root
    }

    private fun readDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val item = AppDataBase.getInstance(requireContext())!!.memoItemDao().getWishWorksMemo()
            wishWorksAdapter = WishWorksAdapter(requireContext(),item)
            withContext(Dispatchers.Main){
                binding.wishWorksRecyclerview.adapter = wishWorksAdapter

                wishWorksAdapter.notifyDataSetChanged()
            }

        }

    }

}