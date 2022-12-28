package com.example.quizapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.quizapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_QuizApp)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener{
            if (binding.etName.text.isNullOrEmpty()){
                Snackbar.make(binding.sv, "Please enter a name first", Snackbar.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, QuestionSetActivity::class.java)
                intent.putExtra(Object.USERNAME, binding.etName.text?.toString())
                startActivity(intent)
            }
        }

    }

}