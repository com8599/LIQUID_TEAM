package com.example.team

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_route_result.*

class RouteResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_result)

        searchView_routeresult.setOnClickListener {
            val searchIntent = Intent(this, MainActivity::class.java)
            startActivity(searchIntent)
        }

        guideView_routeresult.setOnClickListener {
            val guideIntent = Intent(this, GuideActivity::class.java)
            startActivity(guideIntent)
        }
    }
}
