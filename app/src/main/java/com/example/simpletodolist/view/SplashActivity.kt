package com.example.simpletodolist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpletodolist.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private var delayTiem : Long = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashScreen()
    }

    private fun loadSplashScreen(){
        CoroutineScope(Dispatchers.IO).launch {
            delay(delayTiem)
            val intent = Intent(applicationContext,TreeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}