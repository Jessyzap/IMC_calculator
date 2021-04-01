package com.example.calculadoraimc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var weightEdit: EditText
    private lateinit var hightEdit: EditText
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)


        resultText = findViewById(R.id.resultTXT)
        weightEdit = findViewById(R.id.weightEDT)
        hightEdit = findViewById(R.id.hightEDT)
        calculateButton = findViewById(R.id.calculateBTN)


        calculateButton.setOnClickListener {

            when {
                weightEdit.text.isEmpty() -> weightEdit.error = "Insira o peso!"
                hightEdit.text.isEmpty() -> hightEdit.error = "Insira a altura!"

                else -> {
                    calculate(weightEdit.text.toString(), hightEdit.text.toString())
                    weightEdit.text.clear()
                    hightEdit.text.clear()
                    weightEdit.requestFocus()
                }
            }
        }
    }


    private fun calculate(weight: String, hight: String) {

        val imc = weight.toDouble().div(hight.toDouble().pow(2))

        hightEdit.visibility = View.INVISIBLE
        weightEdit.visibility = View.INVISIBLE
        resultText.visibility = View.VISIBLE

        resultText.setOnClickListener {
            hightEdit.visibility = View.VISIBLE
            weightEdit.visibility = View.VISIBLE
            resultText.visibility = View.INVISIBLE
        }


        when (imc) {

            in 18.5..24.9 -> resultText.text = "IMC: ${"%.2f".format(imc)}\n" +
                    "PESO NORMAL \nIMC entre 18,5 e 24,9"

            in 25.0..29.9 -> resultText.text = "IMC: ${"%.2f".format(imc)}\n" +
                    "SOBREPESO \nIMC entre 25 e 29,9"

            in 30.0..34.9 -> resultText.text = "IMC: ${"%.2f".format(imc)}\n" +
                    "OBESIDADE 1 \nIMC entre 30 e 34,9"

            in 35.0..39.9 -> resultText.text = "IMC: ${"%.2f".format(imc)}\n" +
                    "OBESIDADE 2 \nIMC entre 35 e 39,9"

            else -> {
                when {
                    imc < 18.5 -> {
                        resultText.text = "IMC: ${"%.2f".format(imc)}\n" +
                                "ABAIXO DO PESO \nIMC abaixo de 18,5"
                    }
                    imc > 40 -> {
                        resultText.text = "IMC: ${"%.2f".format(imc)}\n" +
                                "OBESIDADE 3 \nIMC acima de 40"
                    }
                }
            }
        }
    }
}

