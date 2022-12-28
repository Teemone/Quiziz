package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val congratulations = "Congratulations"
        val betterLuck = "Better luck next time"
        val username = intent.getStringExtra(Object.USERNAME)
        val totalQuestions = intent.getIntExtra(Object.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Object.CORRECT_ANSWERS, 0)


        if (correctAnswers<=1) {
            binding.tvCongratsName.text = getString(R.string.congratulations, betterLuck, username)
            binding.ivTrophy.setImageResource(R.drawable.sad_face)
        }
        else {
            binding.tvCongratsName.text =
                getString(R.string.congratulations, congratulations, username)
            binding.ivTrophy.setImageResource(R.drawable.trophy_2)

        }
        binding.tvScore.text = getString(R.string.your_score_is, correctAnswers, totalQuestions)
        binding.btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
}