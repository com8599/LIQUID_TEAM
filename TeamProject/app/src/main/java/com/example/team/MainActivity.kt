package com.example.team

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        micButton.setOnClickListener{
            val departIntent = Intent(this, departActivity::class.java)
            startActivity(departIntent)
        }

        searchButton.setOnClickListener {
            val searchIntent = Intent(this, RouteResultActivity::class.java)
            startActivity(searchIntent)
        }
    }
}
