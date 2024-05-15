package com.example.to_dolistapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
   private lateinit var dataBase: DataBase
   private lateinit var textViewTodo:TextView
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        textViewTodo=findViewById(R.id.textViewTodo)

        dataBase = Room.databaseBuilder(
            applicationContext, DataBase::class.java, "To_Do"
        ).build()

        GlobalScope.launch {
            dataObject.listdata=dataBase.dao().getData() as MutableList<CardInfo>
        }


       val alphaAnimation = AnimationUtils.loadAnimation(applicationContext,R.anim.splash_anim)
        textViewTodo.startAnimation(alphaAnimation)

       textViewTodo.animate().translationY(-1400F).setDuration(2700).startDelay = 0
//        lottie.animate().translationX(2000F).setDuration(2000).startDelay= 2900
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            val intent = Intent(this@SplashScreen,MainActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}