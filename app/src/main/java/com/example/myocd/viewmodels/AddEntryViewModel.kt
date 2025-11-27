package com.example.myocd.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myocd.models.Entry

class AddEntryViewModel: ViewModel(){
    var entry = MutableLiveData<Entry>(Entry());
    var page = MutableLiveData<Int>(0);

    fun setPage(page:Int){
        this.page.value = page;
    }

    }
