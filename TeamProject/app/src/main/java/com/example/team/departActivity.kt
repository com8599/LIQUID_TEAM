package com.example.team

import android.content.Intent
import android.os.Bundle
import android.speech.SpeechRecognizer
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_depart.*

class departActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depart)

        val recognizer = SpeechRecognizer.createSpeechRecognizer(this)
        // 음성 인식기를 생성!


            backImage_depart.setOnClickListener {
                val backIntent = Intent(this, MainActivity::class.java)
                startActivity(backIntent)
            }

            searchImage_depart.setOnClickListener {
                val arriveIntent = Intent(this, ArriveActivity::class.java)
                startActivity(arriveIntent)
            }



    }
}
