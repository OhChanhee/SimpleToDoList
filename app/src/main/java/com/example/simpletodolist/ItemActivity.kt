package com.example.simpletodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.simpletodolist.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {
    private lateinit var binding:ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_action_bar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.app_bar_back)
        {
            openMainActivity()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun openMainActivity()
    {
        startActivity(Intent(this,MainActivity::class.java))
    }
}