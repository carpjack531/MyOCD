package com.example.myocd.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myocd.classes.Entry
import com.example.myocd.classes.EntryRepository
import com.example.myocd.classes.TimeEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HistoryViewModel: ViewModel() {
    private val repo =  EntryRepository();
    private val selectedDate = MutableLiveData<String>();
    private val selectedTime = MutableLiveData<String>();
    private val dataset = MutableLiveData<List<String>>(emptyList<String>())
    private val operationMessage = MutableLiveData<String>();
    public val readableDataset:LiveData<List<String>?> = dataset;
    public val readableOperationMessage:LiveData<String> = operationMessage;
    public val readableSelectedDate:LiveData<String> = selectedDate;
    public val readableSelectedTime:LiveData<String> = selectedTime;


    fun setSelectedDate(date:String){
        selectedDate.value = date;
    }

    fun setSelectedTime(time:String){
        selectedTime.value = time;
    }

    fun fetchDates(){
        viewModelScope.launch {
            val response = repo.getDates();
            if(response.isNullOrEmpty()){
                operationMessage.value="fetchDates Failed";
            }

            else{
                dataset.value = response;
            }

        }
    }

    fun fetchTimes(){
        viewModelScope.launch {
            val response = repo.getTimesByDate(selectedDate.value);
            if(response.isNullOrEmpty()){
                operationMessage.value="fetchTimesFromDate Failed";
            }

            else{
                dataset.value = response;
            }

        }
    }


}