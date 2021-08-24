package jp.ac.it_college.s20003.quiz

import com.opencsv.bean.CsvBindByPosition

data class QuizRecord(
    @CsvBindByPosition(position = 0)
    val qus: String = "",

    @CsvBindByPosition(position = 1)
    val img: String? = null,

    @CsvBindByPosition(position = 2)
    val choice1: String = "",

    @CsvBindByPosition(position = 3)
    val choice2: String = "",

    @CsvBindByPosition(position = 4)
    val choice3: String = "",

    @CsvBindByPosition(position = 5)
    val choice4: String = ""

)
