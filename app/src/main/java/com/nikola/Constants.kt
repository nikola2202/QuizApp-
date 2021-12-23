package com.nikola

import com.nikola.quizapp.Question
import com.nikola.quizapp.R

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(1,"What country does this flag belong to?",
            R.drawable.flag_of_canada,
            "Canada",
            "Australia",
            "Armenia",
            "Austria",
            1
        )

        questionsList.add(que1)

        val que2 = Question(1,"What country does this flag belong to?",
            R.drawable.flagofus,
            "Columbia",
            "Australia",
            "United States",
            "Portorico",
            3
        )

        questionsList.add(que2)
        return questionsList

    }
}