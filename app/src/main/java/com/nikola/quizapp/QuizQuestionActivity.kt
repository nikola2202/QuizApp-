package com.nikola.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.nikola.Constants

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var progressBar: ProgressBar
    lateinit var tv_progress: TextView
    lateinit var tv_question: TextView
    lateinit var canada: ImageView
    lateinit var optionOne: TextView
    lateinit var optionTwo: TextView
    lateinit var optionThree: TextView
    lateinit var optionFour: TextView
    lateinit var btn_submit: Button

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        initialization()

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        btn_submit.setOnClickListener(this)


    }

    private fun initialization(){
        progressBar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tv_progress)
        tv_question = findViewById(R.id.tv_question)
        canada = findViewById(R.id.canada_image)
        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)
        btn_submit = findViewById(R.id.btn_submit)
    }


    private  fun setQuestion() {

        val question = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = question!!.question
        canada.setImageResource(question.image)
        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour

    }
   private fun defaultOptionsView() {
       val options = ArrayList<TextView>()
       options.add(0,optionOne)
       options.add(1,optionTwo)
       options.add(2,optionThree)
       options.add(3,optionFour)

       for(option in options) {
           option.setTextColor(Color.parseColor("#7A8089"))
           option.typeface = Typeface.DEFAULT
           option.background = ContextCompat.getDrawable(
               this,R.drawable.default_option_border_bg
           )
       }

   }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.tv_option_one ->{
                selectedOptionView(optionOne,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(optionTwo,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(optionThree,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(optionFour,4)
            }
            R.id.btn_submit -> {
                if(mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }else ->{
                            Toast.makeText(this,
                                "You have successfully completed the quiz",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition -1)
                    if(question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }

                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size) {
                        btn_submit.text = "FINISH"
                    }else{
                        btn_submit.text = "GO TO THE NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }

    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                optionOne.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2 -> {
                optionTwo.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            3 -> {
                optionThree.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            4 -> {
                optionFour.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView,
                                  selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#362A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,R.drawable.selected_option_border_bg
        )
    }

}