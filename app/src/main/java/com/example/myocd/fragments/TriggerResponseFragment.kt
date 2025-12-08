package com.example.myocd.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.myocd.databinding.FragmentTriggerResponseBinding
import com.example.myocd.viewmodels.AddEntryViewModel



class TriggerResponseFragment : Fragment() {

    private lateinit var binding: FragmentTriggerResponseBinding;
    private val viewModel: AddEntryViewModel by lazy{
        ViewModelProvider(requireActivity())[AddEntryViewModel::class.java];
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTriggerResponseBinding.inflate(layoutInflater);
        return binding.root;


    }

    override fun onViewCreated(view:View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState);
        binding.apply {

                triggerResponseNextBtn.setOnClickListener {
                    val response =
                    viewModel.setTriggerResponse(
                        triggerInput.text.toString(),
                        responseInput.text.toString(),
                        responseSeverityInput.text.toString()
                    );

                    if(!response){
                        Toast.makeText(requireContext(), "Invalid input. Please check the fields and try again.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        viewModel.setPage(2);
                    }
                }
        }
    }

}
