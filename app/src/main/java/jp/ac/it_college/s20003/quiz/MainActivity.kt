package jp.ac.it_college.s20003.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jp.ac.it_college.s20003.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener { onStartButtonTapped(it) }
    }

    fun onStartButtonTapped(view: View?) {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }
}