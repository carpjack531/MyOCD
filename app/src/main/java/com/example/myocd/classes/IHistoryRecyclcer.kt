package com.example.myocd.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myocd.databinding.FragmentDisplayDatesBinding
import com.example.myocd.viewmodels.HistoryViewModel

abstract class IHistoryRecycler : Fragment(){

    protected val historyViewModel: HistoryViewModel by lazy{
        ViewModelProvider(requireActivity())[HistoryViewModel::class.java];
    }
    protected var dataset = emptyList<String>();
    protected val dateAdapter = DisplayAdapter(dataset, ::clickAction);
    protected lateinit var binding: FragmentDisplayDatesBinding;

    abstract fun clickAction(data:String);

    abstract fun setObserveBehavior();


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDisplayDatesBinding.inflate(layoutInflater);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState);
        binding.data.layoutManager = LinearLayoutManager(requireContext());
        binding.data.adapter = dateAdapter;
        setObserveBehavior()


    }


}