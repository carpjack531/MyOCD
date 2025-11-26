package com.example.myocd.pages

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myocd.R
import com.example.myocd.databinding.ActivityMainBinding

enum class  ACTIVITIES{
    ADD_ENTRY,
    HISTORY,
    SETTINGS,
    VIEW_ENTRIES,

}

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;

    private val intents by lazy {
        mapOf(
            ACTIVITIES.ADD_ENTRY to Intent(this, AddEntryActivity::class.java),
            ACTIVITIES.HISTORY to Intent(this, HistoryActivity::class.java),
            ACTIVITIES.SETTINGS to Intent(this, SettingsActivity::class.java),
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.apply{
            addBtn.setOnClickListener {
                startActivity(intents[ACTIVITIES.ADD_ENTRY]);

            }

            historyBtn.setOnClickListener{
                startActivity(intents[ACTIVITIES.HISTORY]);
            }

            settingsBtn.setOnClickListener{
                startActivity(intents[ACTIVITIES.SETTINGS]);
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



    }
}