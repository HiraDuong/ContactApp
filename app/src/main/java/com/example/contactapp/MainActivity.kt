package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    var items = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Tạm thời đang để listView để test
        repeat(50) { items.add("Item $it") }
        val listView = findViewById<ListView>(R.id.recyclerview)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        // chuyển màn hình khi nhấn vào một item
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
            val infoIntent = Intent(this, info_activity::class.java)
            startActivity(infoIntent)
        }

    }


}