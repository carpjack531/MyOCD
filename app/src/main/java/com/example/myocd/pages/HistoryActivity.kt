package com.example.myocd.pages

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myocd.R
import com.example.myocd.databinding.ActivityHistoryPageBinding
import com.example.myocd.fragments.DisplayTimeRecycler
import com.example.myocd.viewmodels.HistoryViewModel

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryPageBinding;

    private val historyViewModel: HistoryViewModel by lazy{
        ViewModelProvider(this)[HistoryViewModel::class.java];
    }

    private var timeFragment = DisplayTimeRecycler();


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

        //Could prbbly make this one observable then have it all done in one observable statement

        //Swap Fragments if a Date is selected
        historyViewModel.readableSelectedDate.observe(this) {
            supportFragmentManager.beginTransaction()
                .replace(binding.displayDaysFragment.id, DisplayTimeRecycler())
                .commit()
        }

        //Swap Fragments if a Time is selected
        historyViewModel.readableSelectedTime.observe(this){
            supportFragmentManager.beginTransaction()
                .replace(timeFragment.id, DisplayTimeRecycler())
                .commit()
        }

        historyViewModel.readableOperationMessage.observe(this){msg->
            Toast.makeText(this@HistoryActivity, msg, Toast.LENGTH_SHORT)
                .show()
        }


    }
}