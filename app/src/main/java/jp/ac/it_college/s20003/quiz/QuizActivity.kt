package jp.ac.it_college.s20003.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jp.ac.it_college.s20003.quiz.databinding.ActivityMainBinding
import jp.ac.it_college.s20003.quiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var quiz: QuizFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener { onStartButtonTapped(it) }
    }
    private fun onStartButtonTapped(view: View?) {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }
}
// フラグメントを使う