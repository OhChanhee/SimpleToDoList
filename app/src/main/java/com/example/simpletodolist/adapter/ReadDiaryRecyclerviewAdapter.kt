package com.example.simpletodolist.adapter

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.R

class ReadDiaryRecyclerviewAdapter :RecyclerView.Adapter<ReadDiaryRecyclerviewAdapter.ViewHolder>() {
    var data = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position,data)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val image : ImageView = itemView.findViewById(R.id.image_item_iv)
        fun bind(position: Int,items:List<String>){
            val imageBytes = Base64.decode(items[position], Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            image.setImageBitmap(decodedImage)
        }
    }


}