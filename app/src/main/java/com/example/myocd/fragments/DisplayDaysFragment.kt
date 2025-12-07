package com.example.myocd.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myocd.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class DateAdapter(private val dates:Array<String>): RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Click Listener
        val textView: TextView = view.findViewById(R.id.textView)
    }
    //Creates new views for list items
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): DateAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.entry_view, viewGroup)
        return ViewHolder(view)
    }

    //Replaces contents of view
    override fun onBindViewHolder(viewHolder: DateAdapter.ViewHolder, position: Int) {
        viewHolder.textView.text = dates[position];
    }

    //Returns size of dataset
    override fun getItemCount(): Int {
        return dates.size;
    }

}
class DisplayEntriesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_dates, container, false)
    }



}