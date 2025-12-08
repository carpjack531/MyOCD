package com.example.myocd.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myocd.R
import com.example.myocd.databinding.FragmentDisplayEntriesBinding
import com.example.myocd.viewmodels.EntryRepositoryViewModel
import com.example.myocd.viewmodels.HistoryViewModel

class DisplayTimeFragment : Fragment() {
    private val entryRepo: EntryRepositoryViewModel by lazy{
        ViewModelProvider(requireActivity())[EntryRepositoryViewModel::class.java];
    }

    private val historyViewModel: HistoryViewModel by lazy{
        ViewModelProvider(requireActivity())[HistoryViewModel::class.java];
    }

    private lateinit var binding: FragmentDisplayEntriesBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_entries, container, false)
    }


}