package com.example.myocd.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myocd.R

class DisplayAdapter(private var dataset:List<String>, private val clickAction:(String)->Unit): RecyclerView.Adapter<DisplayAdapter.ViewHolder>() {
       inner class ViewHolder(view: View, ) : RecyclerView.ViewHolder(view) {
        //Click Listener
        val textView: TextView = view.findViewById(R.id.entry)
        init{
            textView.setOnClickListener {
                clickAction(textView.text.toString())
            }
        }

    }

    fun updateDates(dates:List<String>){
        this.dataset = dates;
        notifyDataSetChanged();
    }

    //Creates new views for list items
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): DisplayAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.entry_view, viewGroup, false)
        return ViewHolder(view)
    }

    //Replaces contents of view
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.textView.text = dataset[position];
    }

    //Returns size of dataset
    override fun getItemCount(): Int {
        return dataset.size;
    }

}