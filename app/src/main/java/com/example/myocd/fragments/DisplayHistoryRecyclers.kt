package com.example.myocd.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myocd.classes.IHistoryRecycler;
import com.example.myocd.viewmodels.EntryRepositoryViewModel
import com.example.myocd.viewmodels.HistoryViewModel


class DisplayDateRecyclcer : IHistoryRecycler() {
    override fun clickAction(date:String){
        historyViewModel.setSelectedDate(date);
    }
    override fun setObserveBehavior(){
        entryRepo.readableDates.observe(viewLifecycleOwner){dates->
            dateAdapter.updateDates(dataset);
        }
    }
}

class DisplayTimeRecycler : IHistoryRecycler() {
    override fun clickAction(date:String){
        historyViewModel.setSelectedTime(date);
    }

    override fun setObserveBehavior(){
        entryRepo.readableDates.observe(viewLifecycleOwner){dates->
            dateAdapter.updateDates(dataset);
        }
    }


}