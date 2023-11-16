package com.example.sweatcircle

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var textViewDate: TextView
    private lateinit var editTextExercise: EditText
    private lateinit var editTextSetsReps: EditText
    private lateinit var buttonPickDate: Button
    private lateinit var buttonLogWorkout: Button
    private lateinit var selectedDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewDate = findViewById(R.id.textViewDate)
        editTextExercise = findViewById(R.id.editTextExercise)
        editTextSetsReps = findViewById(R.id.editTextSetsReps)
        buttonPickDate = findViewById(R.id.buttonPickDate)
        buttonLogWorkout = findViewById(R.id.buttonLogWorkout)

        selectedDate = Calendar.getInstance()

        buttonPickDate.setOnClickListener {
            showDatePickerDialog()
        }

        buttonLogWorkout.setOnClickListener {
            logWorkout()
        }
    }

    private fun showDatePickerDialog() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            selectedDate.set(Calendar.YEAR, year)
            selectedDate.set(Calendar.MONTH, month)
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate()
        }

        DatePickerDialog(
            this,
            dateSetListener,
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDate() {
        val dateFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.US)
        textViewDate.text = "Date: " + sdf.format(selectedDate.time)
    }

    private fun logWorkout() {
        val exercise = editTextExercise.text.toString()
        val setsReps = editTextSetsReps.text.toString()

        // You can save the workout details (exercise, sets/reps, date) to a database or perform any other desired action.
        // For simplicity, let's just print the details for now.
        val workoutDetails =
            "Exercise: $exercise\nSets/Reps: $setsReps\nDate: ${textViewDate.text}"
        println(workoutDetails)

        // You may want to clear the input fields after logging the workout.
        editTextExercise.text.clear()
        editTextSetsReps.text.clear()
    }
}