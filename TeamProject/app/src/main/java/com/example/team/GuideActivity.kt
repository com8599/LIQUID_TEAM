package com.example.team

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        leftroute_guide.setOnClickListener {
            val routeIntent = Intent(this, RouteResultActivity::class.java)
            startActivity(routeIntent)
        }
    }
}
