package jp.ac.it_college.s20003.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.opencsv.CSVIterator
import com.opencsv.CSVReader
import jp.ac.it_college.s20003.quiz.databinding.ActivityQuizBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.time.temporal.TemporalAdjusters.next
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private var quizData: ArrayList<String> = arrayListOf()
    private var i: Int = 0
    //問題数カウント
    private var j: Int = 0
    private var ans: String = ""




    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val input = InputStreamReader(assets.open("QuizData.csv"))
        val reader = BufferedReader(input)
        val str = reader.readText()
        val strReader = StringReader(str)
        val csv = CSVIterator(CSVReader(strReader))

        for (row in csv) {
            for (col in row) {
                quizData.add(col)
            }
        }

        val qusTitle: TextView = binding.questionTitle
        val qus: TextView = binding.question
        val cnt: TextView = binding.count
        val choice1: Button = binding.button1
        val choice2: Button = binding.button2
        val choice3: Button = binding.button3
        val choice4: Button = binding.button4
        val next: Button = binding.nextButton

        qusTitle.text = "問題${j + 1}"

        val questionData = arrayOf(
            arrayOf(quizData[6], quizData[8], quizData[9], quizData[10], quizData[11]),
            arrayOf(quizData[12], quizData[14], quizData[15], quizData[16], quizData[17]),
            arrayOf(quizData[18], quizData[20], quizData[21], quizData[22], quizData[23]),
            arrayOf(quizData[24], quizData[26], quizData[27], quizData[28], quizData[29]),
            arrayOf(quizData[30], quizData[32], quizData[33], quizData[34], quizData[35]),
            arrayOf(quizData[36], quizData[38], quizData[39], quizData[40], quizData[41]),
            arrayOf(quizData[42], quizData[44], quizData[45], quizData[46], quizData[47]),
            arrayOf(quizData[48], quizData[50], quizData[51], quizData[52], quizData[53]),
            arrayOf(quizData[54], quizData[56], quizData[57], quizData[58], quizData[59]),
            arrayOf(quizData[60], quizData[62], quizData[63], quizData[64], quizData[65]),
        )

        //qus.text = titles[0]

        val randomNum = Random.nextInt(questionData.size)
        val quiz: Array<String> = questionData[randomNum]

        //問題分のセット
        qus.text = quiz[0]
        //正解のセット
        ans = quiz[1]
        //問題の消去
        quiz.drop(0)
        //選択肢シャッフル
        val list: List<Int> = listOf(4, 1, 2, 3)
        val n: List<Int> = list.shuffled()
        //表示
        choice1.text = quiz[n[0]]
        choice2.text = quiz[n[1]]
        choice3.text = quiz[n[2]]
        choice4.text = quiz[n[3]]

        questionData.drop(randomNum)

        //正解数セット
        cnt.text = i.toString()

        choice1.setOnClickListener {
            if (choice1.text == ans) {
                correct()
            } else{
                incorrect()
            }
        }

        choice2.setOnClickListener {
            if (choice2.text == ans) {
                correct()
            } else{
                incorrect()
            }
        }

        choice3.setOnClickListener {
            if (choice3.text == ans) {
                correct()
            } else{
                incorrect()
            }
        }

        choice4.setOnClickListener {
            if (choice4.text == ans) {
                correct()
            } else{
                incorrect()
            }
        }

        binding.nextButton.setOnClickListener { onNextButtonTapped(it) }
    }

    /*
    fun checkAnswer(view: View?) {
        val ansBtn: Button = findViewById(view?.id!!)
        val btnText = ansBtn.text.toString()

        val alertTitle: String
        if (btnText == ans) {
            alertTitle = "正解"
            i++
        } else {
            alertTitle = "不正解"
        }

        //ダイアログ作成
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("答え : $ans")
            .setPositiveButton("OK") { _, _ ->
                if (i == 10) {

                } else {
                    i++
                }
            }
            .setCancelable(false)
            .show()


    }

     */

    private fun correct() {
        val cnt: TextView = binding.count
        val choice1: Button = binding.button1
        val choice2: Button = binding.button2
        val choice3: Button = binding.button3
        val choice4: Button = binding.button4

        AlertDialog.Builder(this)
            .setTitle("正解!!")
            .setPositiveButton("OK", null)
            .show()

        ++i
        cnt.text = i.toString()
        choice1.isEnabled = false
        choice2.isEnabled = false
        choice3.isEnabled = false
        choice4.isEnabled = false
    }

    private fun incorrect() {
        val choice1: Button = binding.button1
        val choice2: Button = binding.button2
        val choice3: Button = binding.button3
        val choice4: Button = binding.button4

        AlertDialog.Builder(this)
            .setTitle("不正解...")
            .setPositiveButton("OK", null)
            .show()

        choice1.isEnabled = false
        choice2.isEnabled = false
        choice3.isEnabled = false
        choice4.isEnabled = false
    }

    private fun onNextButtonTapped(view: View?) {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }
}
