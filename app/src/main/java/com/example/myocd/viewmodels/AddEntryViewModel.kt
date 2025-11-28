package com.example.myocd.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myocd.models.Entry
import com.example.myocd.models.EntryType
import com.example.myocd.models.EntryWrapper

class AddEntryViewModel: ViewModel(){
    var entry = MutableLiveData<EntryWrapper>(EntryWrapper());
    var page = MutableLiveData<Int>(0);

    fun setValue(){

    }
    fun setPage(page:Int){
        this.page.value = page;
    }

    fun setValue(value:String, type: EntryType): Boolean{
        return entry.value!!.setValue(value, type);
    }

    }
