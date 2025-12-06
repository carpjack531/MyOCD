package com.example.myocd.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myocd.R
import com.example.myocd.databinding.FragmentObsessionCompulsionBinding
import com.example.myocd.databinding.FragmentOutcomeBinding
import com.example.myocd.viewmodels.EntryViewModel

class OutcomeFragment : Fragment() {

    private lateinit var binding: FragmentOutcomeBinding;
    private val viewModel: EntryViewModel by lazy{
        ViewModelProvider(requireActivity())[EntryViewModel::class.java];
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOutcomeBinding.inflate(layoutInflater);
        return binding.root;
    }

    override fun onViewCreated(view:View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState);


        binding.apply {

            submitEntryBtn.setOnClickListener {
                val response =
                    viewModel.setOutcome(outcomeInput.text.toString());

                if(!response){
                    Toast.makeText(requireContext(), "Invalid input. Please check the fields and try again.", Toast.LENGTH_SHORT).show()
                }
                else {
                    viewModel.saveEntryToDatabase();
                }
            }
        }
    }

}
