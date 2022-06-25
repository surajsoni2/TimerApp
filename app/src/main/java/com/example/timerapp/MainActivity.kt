package com.example.timerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.timerapp.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    //variable for timer which will be initialized later
    private var countDownTimer: CountDownTimer? = null
    //Duration of timer in millisecond
    private  var timerDuration: Long =10000
    //pauseOffset = timeDuration - time left
    private var pauseOffset: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
//        setSupportActionBar(binding?.toolbar)
        binding?.tvTimer?.text = (timerDuration/1000).toString()
        binding?.btnStart?.setOnClickListener{
            startTimer(pauseOffset)
        }
        binding?.btnPause?.setOnClickListener{
            pauseTimer()
        }
        binding?.btnReset?.setOnClickListener {

        }
        binding?.btnReset?.setOnClickListener {
            resetTimer()
        }
    }

    private fun resetTimer() {
        if(countDownTimer != null){
            countDownTimer!!.cancel()
            binding?.tvTimer?.text = (timerDuration/1000).toString()
            countDownTimer = null
            pauseOffset = 0

        }
    }

    private fun pauseTimer() {
        if(countDownTimer != null){
            countDownTimer!!.cancel()
        }
    }

    private fun startTimer(pauseOffsetL: Long) {

        countDownTimer = object : CountDownTimer(timerDuration-pauseOffsetL,1000){

            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
//                Log.i("pause value left $millisUntilFinished",pauseOffset.toString())
                binding?.tvTimer?.text =
                    (millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                Toast.makeText(this@MainActivity,"timer is finished.",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}