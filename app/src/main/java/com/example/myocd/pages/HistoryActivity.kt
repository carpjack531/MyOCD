package com.example.myocd.pages

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myocd.R
import com.example.myocd.databinding.ActivityAddEntryPageBinding
import com.example.myocd.databinding.ActivityHistoryPageBinding
import com.example.myocd.fragments.DisplayEntriesFragment
import com.example.myocd.viewmodels.EntryRepositoryViewModel
import com.example.myocd.viewmodels.HistoryViewModel

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryPageBinding;

    private val historyViewModel: HistoryViewModel by lazy{
        ViewModelProvider(this)[HistoryViewModel::class.java];
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHistoryPageBinding.inflate(layoutInflater);
        setContentView(binding.root);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Swap Fragments if a Date is selectable
        historyViewModel.readableSelectedDate.observe(this) { date ->
            supportFragmentManager.beginTransaction()
                .replace(binding.displayDaysFragment.id, DisplayEntriesFragment())
                .commit()
        }


    }
}