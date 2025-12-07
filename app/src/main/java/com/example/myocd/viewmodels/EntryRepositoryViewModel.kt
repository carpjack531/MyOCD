package com.example.myocd.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myocd.classes.Entry
import com.example.myocd.classes.EntryRepository
import com.example.myocd.classes.TimeEntry
import kotlinx.coroutines.launch


class EntryRepositoryViewModel: ViewModel(){
    private val repo =  EntryRepository();

    private val operationSuccessful = MutableLiveData<String>("");
    private val dates = MutableLiveData<List<String>>(mutableListOf<String>())
    private val entries = MutableLiveData<List<TimeEntry>>(mutableListOf<TimeEntry>())

    public val readableOperationSuccessful: LiveData<String> =  operationSuccessful;
    public val readableEntries:LiveData<List<TimeEntry>> = entries;
    public val readableDates:LiveData<List<String>> = dates;

    //Should return entry, for now dosent



    fun saveEntryToDatabase(entry:Entry){
        viewModelScope.launch {
            operationSuccessful.value = repo.saveEntryToDatabase(entry);
        }
    }

    //Expensive, should be cached
    fun getEntryDates(){
            viewModelScope.launch {
                try {
                    val response = repo.getEntryDates();
                    if (response == null) {
                        throw Exception("Get Entry Dates Failed");
                    }

                    dates.value= response;
                }
                    catch(e:Exception) {
                        operationSuccessful.value = "${e.message}";
                        return@launch;
                    }
            }
    }

    fun getEntriesByDates(date: String){
        viewModelScope.launch{
            try {
                val response = repo.getEntriesByDate(date);
                if (response == null){
                    throw Exception("Get Entries By Dates Failed");
                }
            }
            catch (e:Exception){
                operationSuccessful.value = "${e.message}";
                return@launch;
            }
        }
    }

    fun getEntryByDateTime(date:String, time:String){
        viewModelScope.launch{

        }
    }

 }
