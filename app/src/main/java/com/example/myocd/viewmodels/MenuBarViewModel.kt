package com.example.myocd.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuBarViewModel: ViewModel(){
    private val title = MutableLiveData<String>("Default");
    public fun setString(title:String)
    {
        this.title.value = title;
    }
}