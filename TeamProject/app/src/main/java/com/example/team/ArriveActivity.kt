package com.example.team

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_arrive.*
import kotlinx.android.synthetic.main.activity_depart.*


class ArriveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrive)

        val recognizer = SpeechRecognizer.createSpeechRecognizer(this)
        // 음성 인식기를 생성!

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).
            apply{
                putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
                // packageName을 입력, 하단에서는 한국어 인식 정의
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR") }

        // 마이크 이미지를 눌러야 음성인식 시작
        imageViewmic2.setOnClickListener {
            recognizer.startListening(intent) // 인식 시작
        }

        backImage_arrive.setOnClickListener {
            val backIntent = Intent(this, departActivity::class.java)
            startActivity(backIntent)
        }

        arriveImage_arrive.setOnClickListener {
            val searchIntent = Intent(this, RouteResultActivity::class.java)
            startActivity(searchIntent)
        }
        recognizer.setRecognitionListener(object: RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                Toast.makeText(applicationContext,"음성인식이 시작되었습니다.말씀해주세요", Toast.LENGTH_SHORT).show()
            }
            // 음성 인식 준비 완료
            override fun onRmsChanged(rmsdB: Float) {}
            // 음성의 RMS가 바뀌었을 때
            override fun onBufferReceived(buffer: ByteArray?) {}
            // 음성 데이터의 buffer를 받을 수 있다.
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            // 사용자가 말하기 시작할 때
            override fun onEndOfSpeech() {}
            // 사용자의 말이 끝났을 때
            override fun onError(error: Int) {
                val message:String
                when (error) {
                    SpeechRecognizer.ERROR_AUDIO -> message = "오디오 에러"
                    SpeechRecognizer.ERROR_CLIENT -> message = "클라이언트 에러"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> message = "퍼미션 없음"
                    SpeechRecognizer.ERROR_NETWORK -> message = "네트워크 에러"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> message = "네트웍 타임아웃"
                    SpeechRecognizer.ERROR_NO_MATCH -> message = "찾을 수 없음"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> message = "RECOGNIZER가 바쁨"
                    SpeechRecognizer.ERROR_SERVER -> message = "서버가 이상함"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> message = "말하는 시간초과"
                    else -> message = "알 수 없는 오류임"
                }
                Toast.makeText(getApplicationContext(), "에러가 발생하였습니다 :" + message, Toast.LENGTH_SHORT).show()
            }
            // 오류가 발생했을 때
            override fun onResults(results: Bundle) {
                //결과 값을 받음
                // onResults에서 텍스트를 받는 방법
                val key = SpeechRecognizer.RESULTS_RECOGNITION
                val result = results.getStringArrayList(key)
                //result는 arraylist 안에 들어간 데이터는
                //0번째가 부드럽게 변형 된 데이터
                //이 후의 데이터는 띄어쓰기나 맞춤법이 어색한 데이터
                val arriveStr = result[0]
                textView.setText(arriveStr.toString()+"(이)가 맞으십니까?")


            }


        })

    }

}
