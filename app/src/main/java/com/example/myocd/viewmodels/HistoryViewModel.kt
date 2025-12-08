package com.example.myocd.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel: ViewModel() {
    private val selectedDate: MutableLiveData<String> = MutableLiveData<String>();
    private val selectedTime: MutableLiveData<String> = MutableLiveData<String>();

    public val readableSelectedDate: LiveData<String> = selectedDate;
    public val readableSelectedTime: LiveData<String> = selectedTime;

    fun setSelectedDate(date:String){
        //should add check to see if date is a valid string
        this.selectedDate.value = date;
        println("setDate: ${this.selectedDate.value}");
    }

    fun setSelectedTime(selectedTime:String){
        this.selectedTime.value = selectedTime;
        println("setSelectedTime: ${this.selectedTime.value}")
    }
}