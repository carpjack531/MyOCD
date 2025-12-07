package com.example.myocd.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myocd.R
import com.example.myocd.databinding.FragmentDisplayDatesBinding
import com.example.myocd.viewmodels.EntryRepositoryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class DateAdapter(private var dates:List<String>): RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Click Listener
        val textView: TextView = view.findViewById(R.id.entry)

        init{
            textView.setOnClickListener {
                println("DateAdapter: Click Registered");
            }
        }

    }

    fun updateDates(dates:List<String>){
        this.dates = dates;
        notifyDataSetChanged();
    }
    //Creates new views for list items
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): DateAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.entry_view, viewGroup, false)
        return ViewHolder(view)
    }

    //Replaces contents of view
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.textView.text = dates[position];
    }




    //Returns size of dataset
    override fun getItemCount(): Int {
        return dates.size;
    }

}
class DisplayDaysFragment : Fragment() {
    private lateinit var binding: FragmentDisplayDatesBinding;

    private val entryRepo: EntryRepositoryViewModel by lazy{
        ViewModelProvider(requireActivity())[EntryRepositoryViewModel::class.java];
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDisplayDatesBinding.inflate(layoutInflater);
        return binding.root;
    }

    override fun onViewCreated(view:View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState);
        val dataSet = emptyList<String>();
        val dateAdapter = DateAdapter(dataSet);
        binding.dates.layoutManager = LinearLayoutManager(requireContext());
        binding.dates.adapter = dateAdapter;
        entryRepo.readableDates.observe(viewLifecycleOwner){dates->
            dateAdapter.updateDates(dates);
        }

    }



}