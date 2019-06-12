package com.example.team

import android.content.Intent
import android.os.Bundle
import android.speech.SpeechRecognizer
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_arrive.*

class ArriveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrive)

        val recognizer = SpeechRecognizer.createSpeechRecognizer(this)
        // 음성 인식기를 생성!


        backImage_arrive.setOnClickListener {
            val backIntent = Intent(this, departActivity::class.java)
            startActivity(backIntent)
        }

        arriveImage_arrive.setOnClickListener {
            val searchIntent = Intent(this, RouteResultActivity::class.java)
            startActivity(searchIntent)
        }
    }

}
