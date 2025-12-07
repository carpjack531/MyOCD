package com.example.myocd.pages

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myocd.R
import com.example.myocd.databinding.ActivityAddEntryPageBinding
import com.example.myocd.fragments.MenuBar
import com.example.myocd.fragments.ObsessionCompulsionFragment
import com.example.myocd.fragments.OutcomeFragment
import com.example.myocd.viewmodels.AddEntryViewModel
import com.example.myocd.viewmodels.EntryRepositoryViewModel
class AddEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEntryPageBinding;
    private lateinit var obsessionCompulsion: ObsessionCompulsionFragment;
    val entryViewModel: AddEntryViewModel by lazy{
        ViewModelProvider(this)[AddEntryViewModel::class.java];
    }
    val entryRepo: EntryRepositoryViewModel by lazy{
        ViewModelProvider(this)[EntryRepositoryViewModel::class.java];
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddEntryPageBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val menuBarFragment = MenuBar.newInstance("Add Entry");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(savedInstanceState==null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentMenuBar.id, menuBarFragment)
                .commit()
        }

        //Observable: Check if a page swap is called
        entryViewModel.readablePage.observe(this){ newPage->
            if(newPage == 2){
                obsessionCompulsion = ObsessionCompulsionFragment();
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentTriggerResponse.id, obsessionCompulsion)
                    .commit()
            }

             if(newPage == 3){
                supportFragmentManager.beginTransaction()
                    .replace(obsessionCompulsion.id, OutcomeFragment())
                    .commit()
            }
        }

        //Observable: Check if operation is complete
        entryRepo.readableOperationSuccessful.observe(this){operationSuccessful->
            if(operationSuccessful == true){
                Toast.makeText(this@AddEntryActivity, "Entry saved successfully", Toast.LENGTH_SHORT).show()
                finish();
            }
            else if(operationSuccessful==false){
                Toast.makeText(this@AddEntryActivity, "Entry saved error", Toast.LENGTH_SHORT).show()
            }
        }
    }


}