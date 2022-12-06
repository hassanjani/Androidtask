package com.example.room.mvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.mvvm.R
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnInsert.setOnClickListener {
            val intent = Intent(this, InputScreen::class.java)
            startActivity(intent)
        }
        btnList.setOnClickListener {
            val intent = Intent(this, StoreListActivity::class.java)
            startActivity(intent)
        }
        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)

        }


    }
}