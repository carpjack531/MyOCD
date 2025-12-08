package com.example.myocd.fragments

import com.example.myocd.classes.IHistoryRecycler;



class DisplayDatesRecycler : IHistoryRecycler() {
    override fun clickAction(date:String){
        historyViewModel.setSelectedDate(date);
    }
    override fun setObserveBehavior(){
        historyViewModel.readableDataset.observe(viewLifecycleOwner){data->
            dateAdapter.updateDataset(data?: emptyList());
        }
       historyViewModel.fetchDates();
    }

}

class DisplayTimeRecycler : IHistoryRecycler() {
    override fun clickAction(date:String){
        historyViewModel.setSelectedTime(date);
    }

    override fun setObserveBehavior(){
        historyViewModel.readableDataset.observe(viewLifecycleOwner){data-> ;
            dateAdapter.updateDataset(data?: emptyList());
        }
        historyViewModel.fetchTimes();
    }


}