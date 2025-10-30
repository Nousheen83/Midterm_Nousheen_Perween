package com.example.midterm_taha_ali

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var numberInput: EditText
    private lateinit var generateButton: Button
    private lateinit var listView: ListView
    private lateinit var historyButton: Button

    private lateinit var adapter: ArrayAdapter<String>
    private val tableList = mutableListOf<String>()

    companion object {
        val historyNumbers = mutableSetOf<Int>()  // To track generated numbers
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberInput = findViewById(R.id.etNumber)
        generateButton = findViewById(R.id.btnGenerate)
        listView = findViewById(R.id.listView)
        historyButton = findViewById(R.id.btnHistory)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tableList)
        listView.adapter = adapter

        generateButton.setOnClickListener {
            val numberText = numberInput.text.toString()
            if (numberText.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val number = numberText.toInt()
            generateTable(number)
            historyNumbers.add(number)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val item = tableList[position]
            AlertDialog.Builder(this)
                .setTitle("Delete Item")
                .setMessage("Do you want to delete this row?\n$item")
                .setPositiveButton("Yes") { _, _ ->
                    tableList.removeAt(position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Deleted: $item", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()
        }

        historyButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun generateTable(number: Int) {
        tableList.clear()
        for (i in 1..10) {
            tableList.add("$number × $i = ${number * i}")
        }
        adapter.notifyDataSetChanged()
    }
}
