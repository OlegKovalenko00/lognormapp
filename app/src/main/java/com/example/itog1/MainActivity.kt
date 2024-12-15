package com.example.itog1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.exp
import kotlin.math.sqrt
import java.util.Random

class MainActivity : AppCompatActivity() {

    private var lastGeneratedNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meanEditText = findViewById<EditText>(R.id.mean_val)
        val varianceEditText = findViewById<EditText>(R.id.variance_value)
        val generateButton = findViewById<Button>(R.id.get_random_num)
        val resultTextView = findViewById<TextView>(R.id.random_number_result)

        generateButton.setOnClickListener {
            try {
                val meanText = meanEditText.text.toString()
                val varianceText = varianceEditText.text.toString()

                if (meanText.isEmpty() || varianceText.isEmpty()) {
                    resultTextView.text = "Пожалуйста, заполните оба поля."
                    return@setOnClickListener
                }

                val mean = meanText.toDoubleOrNull()
                val variance = varianceText.toDoubleOrNull()

                if (mean == null || variance == null || variance < 0) {
                    resultTextView.text = "Ошибка: некорректные значения µ или σ²."
                    return@setOnClickListener
                }

                val randomValue = generateLogNormal(mean, sqrt(variance))
                resultTextView.text = "$randomValue"

                lastGeneratedNumber = resultTextView.text.toString()

            } catch (e: NumberFormatException) {
                // Исправлено: добавлена конкретная обработка ошибки при некорректном формате данных
                resultTextView.text = "Ошибка: некорректный формат данных."
            } catch (e: IllegalArgumentException) {
                // Исправлено: добавлена обработка ошибки при некорректных аргументах
                resultTextView.text = "Ошибка: некорректные аргументы."
            } catch (e: Exception) {
                // Исправлено: выводим стек ошибки для диагностики
                e.printStackTrace()  // Это позволяет отслеживать точную причину ошибки
                resultTextView.text = "Произошла ошибка: ${e.localizedMessage}"
            }
        }
    }

    private fun generateLogNormal(mean: Double, stdDev: Double): Double {
        val normalValue = Random().nextGaussian() * stdDev + mean
        return exp(normalValue)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("LAST_NUMBER", lastGeneratedNumber)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastGeneratedNumber = savedInstanceState.getString("LAST_NUMBER")
        val resultTextView = findViewById<TextView>(R.id.random_number_result)
        resultTextView.text = lastGeneratedNumber ?: "Результат будет здесь"
    }
}
