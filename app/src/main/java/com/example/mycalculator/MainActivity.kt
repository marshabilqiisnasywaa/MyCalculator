package com.example.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.R

class MainActivity : AppCompatActivity() {

    private lateinit var calculationDisplay: TextView
    private lateinit var resultDisplay: TextView

    private var input: String = ""
    private var operator: Char = ' '
    private var firstNumber: Int = 0
    private var secondNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        calculationDisplay = findViewById(R.id.calculationDisplay)
        resultDisplay = findViewById(R.id.tvResult)

        // Set up number buttons
        val numberButtons = listOf(
            findViewById<Button>(R.id.btn0), findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2), findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4), findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6), findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8), findViewById<Button>(R.id.btn9)
        )

        // Set up operator buttons
        val addButton: Button = findViewById(R.id.btnAddition)
        val subtractButton: Button = findViewById(R.id.btnSubtraction)
        val multiplyButton: Button = findViewById(R.id.btnMultiplication)
        val divideButton: Button = findViewById(R.id.btnDivision)
        val equalsButton: Button = findViewById(R.id.btnEquals)
        val acButton: Button = findViewById(R.id.btnAC)
        val decimalButton: Button = findViewById(R.id.btnDecimal)

        // Mengatur number button listeners
        numberButtons.forEach { button ->
            button.setOnClickListener { appendNumber(button.text.toString()) }
        }

        // Mengatur operator button listeners
        addButton.setOnClickListener { setOperator('+') }
        subtractButton.setOnClickListener { setOperator('-') }
        multiplyButton.setOnClickListener { setOperator('*') }
        divideButton.setOnClickListener { setOperator('/') }
        decimalButton.setOnClickListener { appendDecimal() }

        // Clear inputan
        acButton.setOnClickListener { resetCalculator() }

        // Menghitung hasil
        equalsButton.setOnClickListener { calculateResult() }
    }

    private fun appendNumber(number: String) {
        input += number
        calculationDisplay.text = input
    }

    private fun appendDecimal() {
        // mengatur angka decimal, tetapi menghindari hasil decimal di hasil integer
        if (!input.contains(".")) {
            input += "."
            calculationDisplay.text = input
        }
    }

    private fun setOperator(op: Char) {
        if (input.isNotEmpty()) {
            firstNumber = input.toInt()
            operator = op
            input = ""
            calculationDisplay.text = "$firstNumber $operator"
        }
    }

    private fun calculateResult() {
        if (input.isNotEmpty()) {
            secondNumber = input.toInt()
            val result = when (operator) {
                '+' -> firstNumber + secondNumber
                '-' -> firstNumber - secondNumber
                '*' -> firstNumber * secondNumber
                '/' -> firstNumber / secondNumber
                else -> 0
            }
            resultDisplay.text = result.toString()
            calculationDisplay.text = "$firstNumber $operator $secondNumber"
            input = ""
        }
    }

    private fun resetCalculator() {
        input = ""
        operator = ' '
        firstNumber = 0
        secondNumber = 0
        calculationDisplay.text = ""
        resultDisplay.text = "0"
    }
}
