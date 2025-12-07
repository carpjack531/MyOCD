package com.example.myocd.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myocd.models.Entry
import com.example.myocd.models.EntryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EntryRepositoryViewModel: ViewModel(){
    private val repo =  EntryRepository();

    private val operationSuccessful = MutableLiveData<Boolean?>(null);
    private val dates = MutableLiveData<MutableList<String>>(mutableListOf<String>())
    private val entries = MutableLiveData<MutableList<Entry>>(mutableListOf<Entry>())

    public val readableOperationSuccessful: LiveData<Boolean?> =  operationSuccessful;
    public val readableDates:LiveData<MutableList<String>> = dates;
    public val readableEntries:LiveData<MutableList<Entry>> = entries;



    fun saveEntryToDatabase(entry:Entry){
        viewModelScope.launch {
            val response = repo.saveEntryToDatabase(entry);
            operationSuccessful.value = response;


        }
    }



    fun getEntriesByDate(date:String){

    }

    fun getEntryByTime(date:String){

    }

    fun updateEntry(){

    }
 }
