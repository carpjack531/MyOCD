package com.example.myocd.pages

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myocd.R
import com.example.myocd.databinding.ActivityAddEntryPageBinding
import com.example.myocd.fragments.MenuBar
import com.example.myocd.fragments.ObessionCompulsion;
import com.example.myocd.models.Entry
import com.example.myocd.viewmodels.AddEntryViewModel

class AddEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEntryPageBinding;
    val viewModel: AddEntryViewModel by lazy{
        ViewModelProvider(this)[AddEntryViewModel::class.java];
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddEntryPageBinding.inflate(layoutInflater);
        setContentView(binding.root);



        val menuBarFragment = MenuBar.newInstance("Add Entry");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentMenuBar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportFragmentManager.beginTransaction()
            .add(binding.fragmentMenuBar.id, menuBarFragment)
            .commit()

        viewModel.page.observe(this){ newPage->
            if(newPage == 2){
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentTriggerResponse.id, ObessionCompulsion())
                    .commit()
            }
        }
    }


}