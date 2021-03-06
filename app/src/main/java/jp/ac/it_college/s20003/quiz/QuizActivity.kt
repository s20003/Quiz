package jp.ac.it_college.s20003.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.opencsv.CSVIterator
import com.opencsv.CSVReader
import jp.ac.it_college.s20003.quiz.databinding.ActivityQuizBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import kotlin.collections.ArrayList

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private var quizData: ArrayList<String> = arrayListOf()
    //正解カウント
    private var i: Int = 0
    //問題数カウント
    private var q: Int = 0


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
        val timerView = binding.timer
        val choice1: Button = binding.button1
        val choice2: Button = binding.button2
        val choice3: Button = binding.button3
        val choice4: Button = binding.button4
        val next: Button = binding.nextButton

        next.isEnabled = false
        qusTitle.text = "問題${q + 1}"

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

        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val second = millisUntilFinished / 1000L % 60L
                timerView.text = second.toString()
            }

            override fun onFinish() {
                next.isEnabled = true
                AlertDialog.Builder(this@QuizActivity)
                    .setTitle("時間切れ")
                    .setPositiveButton("OK", null)
                    .show()

                choice1.isEnabled = false
                choice2.isEnabled = false
                choice3.isEnabled = false
                choice4.isEnabled = false
            }
        }.start()

        //問題文のセット
        qus.text = questionData[q][0]
        //選択肢シャッフル
        val list: List<Int> = listOf(1, 2, 3, 4)
        val num: List<Int> = list.shuffled()
        //表示
        choice1.text = questionData[q][num[0]]
        choice2.text = questionData[q][num[1]]
        choice3.text = questionData[q][num[2]]
        choice4.text = questionData[q][num[3]]

        //正解数セット
        //cnt.text = i.toString()

        choice1.setOnClickListener {
            next.isEnabled = true
            timer.cancel()
            if (choice1.text == questionData[q][1]) {
                correct()
            } else{
                incorrect()
            }
        }

        choice2.setOnClickListener {
            next.isEnabled = true
            timer.cancel()
            if (choice2.text == questionData[q][1]) {
                correct()
            } else{
                incorrect()
            }
        }

        choice3.setOnClickListener {
            next.isEnabled = true
            timer.cancel()
            if (choice3.text == questionData[q][1]) {
                correct()
            } else{
                incorrect()
            }
        }

        choice4.setOnClickListener {
            next.isEnabled = true
            timer.cancel()
            if (choice4.text == questionData[q][1]) {
                correct()
            } else{
                incorrect()
            }
        }

        next.setOnClickListener {
            //問題数カウント
            q++
            if (q == 10) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("ANSWER", i)
                startActivity(intent)
                finish()
            } else {

                timer.start()
                //qusTitleの表示
                qusTitle.text = "問題${q + 1}"

                //問題文の表示
                qus.text = questionData[q][0]

                val qusNext = list.shuffled()
                choice1.text = questionData[q][qusNext[0]]
                choice2.text = questionData[q][qusNext[1]]
                choice3.text = questionData[q][qusNext[2]]
                choice4.text = questionData[q][qusNext[3]]

                //選択肢の有効化
                choice1.isEnabled = true
                choice2.isEnabled = true
                choice3.isEnabled = true
                choice4.isEnabled = true
                next.isEnabled = false

            }
        }

    }

    private fun correct() {
        val choice1: Button = binding.button1
        val choice2: Button = binding.button2
        val choice3: Button = binding.button3
        val choice4: Button = binding.button4

        AlertDialog.Builder(this)
            .setTitle("正解!!")
            .setPositiveButton("OK", null)
            .show()

        ++i
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
}
