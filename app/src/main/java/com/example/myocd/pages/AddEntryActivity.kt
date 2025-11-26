package com.example.myocd.pages

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myocd.R
import com.example.myocd.fragments.MenuBar
import com.example.myocd.models.Entry

class AddEntryActivity : AppCompatActivity() {
    private lateinit var entry: Entry;
    val menuBarFragment = MenuBar.newInstance("Home")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_entry_page)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentMenuBar, menuBarFragment)
            .commit()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentMenuBar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}