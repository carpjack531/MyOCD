package com.example.myocd.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button;
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.myocd.R
import com.example.myocd.databinding.FragmentTriggerResponseBinding
import com.example.myocd.pages.MainActivity
import com.example.myocd.viewmodels.AddEntryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TriggerResponse.newInstance] factory method to
 * create an instance of this fragment.
 */
class TriggerResponse : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: AddEntryViewModel;
    private lateinit var binding: FragmentTriggerResponseBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        viewModel = ViewModelProvider(requireActivity())[AddEntryViewModel::class.java];
        binding.apply{
            nextBtn.setOnClickListener {


                if(!validateInput()){
                    //Change this to be less robotic
                   val toast =  Toast.makeText(
                       requireContext(),
                       "Invalid Inputs Detected!!!",
                       Toast.LENGTH_SHORT
                   )
                    toast.show();
                }
                else{
                    viewModel.setPage(2);
                }
            }
        }

    }

    //bad way to do this, not null safe, also ugly syntax
    fun validateInput():Boolean{
        val sevText = binding.responseSeverityInput.text.toString();
        val sev = if (sevText.isEmpty()) -1 else Integer.parseInt(sevText);
        return(binding.triggerInput.text.isNotEmpty()
                &&binding.responseInput.text.isNotEmpty()
                &&binding.responseSeverityInput.text.isNotEmpty()
                &&binding.responseSeverityInput.text.isNotEmpty()
                &&sev in 0..100
                );
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TriggerResponse.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TriggerResponse().apply {
                arguments = Bundle().apply {

                }
            }
    }
}