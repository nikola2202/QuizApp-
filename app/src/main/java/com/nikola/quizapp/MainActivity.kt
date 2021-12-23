package com.nikola.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {

        lateinit var btn_start: Button
        lateinit var et_name: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btn_start = findViewById(R.id.btn_start)
        et_name = findViewById(R.id.et_name)

        btn_start.setOnClickListener {

            if(et_name.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter your name.",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,QuizQuestionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }
}