package com.example.quizapp

object Object {
    const val USERNAME: String = "username"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    const val KEY_PROGRESS_BAR_COUNT: String = "progress_count"
    const val KEY_SELECTED_OPTION: String = "selected_option"
    const val KEY_QUESTION_LIST_OBJECT: String = "question_list_object"

    fun allQuestionsList(): List<Question> {
        return listOf<Question>(
            Question(1, correctAnswer = "Toyota", vehicleLogo = R.drawable.toyota , answers = listOf("Mazda", "Mitsubishi", "Chevrolet", "Toyota")),
            Question(3, correctAnswer = "Hyundai", vehicleLogo = R.drawable.hyundai, answers = listOf("Mazda", "Maserati", "Hyundai", "Subaru")),
            Question(4, correctAnswer = "Bentley", vehicleLogo = R.drawable.bentley, answers = listOf("Bentley", "Renault", "Chevrolet", "Great wall")),
            Question(5, correctAnswer = "Audi", vehicleLogo = R.drawable.audi, answers = listOf("Dodge", "Audi", "Chevrolet", "Toyota")),
            Question(6, correctAnswer = "Acura", vehicleLogo = R.drawable.acura, answers = listOf("Corolla", "Mitsubishi", "Acura", "Corona")),
            Question(7, correctAnswer = "Lexus", vehicleLogo = R.drawable.lexus, answers = listOf("Dodge viper", "Lexus", "Chevrolet", "Subaru")),
            Question(8, correctAnswer = "Dodge viper", vehicleLogo = R.drawable.dodge, answers = listOf("Peugeot", "Dodge viper", "Chevrolet", "Tata")),
            Question(9, correctAnswer = "Great wall", vehicleLogo = R.drawable.great_wall, answers = listOf("Mazda", "Tesla", "Great wall", "Camry")),
            Question(10, correctAnswer = "Corvette", vehicleLogo = R.drawable.corvette, answers = listOf("Corvette", "Mitsubishi", "Chevrolet", "Toyota")),
            Question(11, correctAnswer = "Koenigsegg", vehicleLogo = R.drawable.koenigsegg, answers = listOf("Subaru", "Mitsubishi", "Koenigsegg", "Bentley")),
            Question(12, correctAnswer = "Maserati", vehicleLogo = R.drawable.maserati, answers = listOf("Mazda", "Renault", "Maserati", "Toyota")),
        )
    }
}