package com.example.asus.kotlin

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.random
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    var score:Int=0
    var handler:Handler= Handler()
    var runnable:Runnable= Runnable {  }
    var ImageArray = ArrayList<ImageView>()
    var rndm = Random()
    var location:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Timer
        object :CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                textTime.text="Time : "+millisUntilFinished/1000
            }

            override fun onFinish() {
                textTime.text="FİNİSH ! "
                handler.removeCallbacks(runnable)
                makeInvisible()

            }

        }.start()

        //Collect all in an array
        ImageArray = arrayListOf(imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9)

        //All of them INVISIBLE
        makeInvisible()

        //Moving
        runnable = object : Runnable {
            override fun run() {
                ImageArray.get(location).visibility = View.INVISIBLE
                location=rndm.nextInt((8 - 0) + 0)
                ImageArray.get(location).visibility = View.VISIBLE
                handler.postDelayed(this, 500)
            }
        }
        handler.post(runnable)

    }

    //Score
    fun score(view:View){
        score++;
        textScore.text="Score : $score"

    }

    fun makeInvisible(){
        for (item: ImageView in ImageArray) {
            item.visibility = View.INVISIBLE
        }
    }


}

