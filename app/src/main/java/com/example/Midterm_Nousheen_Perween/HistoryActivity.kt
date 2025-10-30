package com.example.midterm_taha_ali

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listView = findViewById(R.id.historyListView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, MainActivity.historyNumbers.toList())
        listView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == R.id.menu_clear_all) {
            AlertDialog.Builder(this)
                .setTitle("Clear All?")
                .setMessage("Do you want to delete all history?")
                .setPositiveButton("Yes") { _, _ ->
                    MainActivity.historyNumbers.clear()
                    adapter.clear()
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "All rows cleared", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}
