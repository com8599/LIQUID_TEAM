package com.example.team

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        val video :Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.konkuk)
        video_guide.setMediaController(MediaController(this));
        video_guide.setVideoURI(video)

        video_guide.start();

        leftroute_guide.setOnClickListener {
            val routeIntent = Intent(this, RouteResultActivity::class.java)
            startActivity(routeIntent)
        }
    }
}
