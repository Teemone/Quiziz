package com.example.quizapp

data class Question(
    val id: Int,
    val question: String = "What's the name of the vehicle brand logo?",
    val answers: List<String>,
    val vehicleLogo: Int,
    val correctAnswer: String
)