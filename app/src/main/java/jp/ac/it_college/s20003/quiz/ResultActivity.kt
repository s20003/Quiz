package jp.ac.it_college.s20003.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.s20003.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ansCnt = intent.getIntExtra("ANSWER", 0)
        val ansView = ansCnt.toString()

        val resultView = binding.resultView

        resultView.text = ansView

    }
}