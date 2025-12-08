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

class DisplayEntriesFragment : Fragment() {
    private val entryRepo: EntryRepositoryViewModel by lazy{
        ViewModelProvider(requireActivity())[EntryRepositoryViewModel::class.java];
    }

    private val historyViewModel: HistoryViewModel by lazy{
        ViewModelProvider(requireActivity())[HistoryViewModel::class.java];
    }

    private inner class TimeAdapter(private var times:List<String>): RecyclerView.Adapter<DisplayTimesFragment.Ti.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            //Click Listener
            val textView: TextView = view.findViewById(R.id.entry)
            init{
                textView.setOnClickListener {
                    historyViewModel.setSelectedDate(textView.text.toString());
                }
            }

        }

        fun updateTimes(times:List<String>){
            this.times = times;
            notifyDataSetChanged();
        }

        //Creates new views for list items
        override fun onCreateViewHolder(
            viewGroup: ViewGroup,
            viewType: Int
        ): TimeAdapter.ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.entry_view, viewGroup, false)
            return ViewHolder(view)
        }

        //Replaces contents of view
        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
            holder.textView.text = times[position];
        }

        //Returns size of dataset
        override fun getItemCount(): Int {
            return times.size;
        }

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