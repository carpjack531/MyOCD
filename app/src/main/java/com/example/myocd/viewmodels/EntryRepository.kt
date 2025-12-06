package com.example.myocd.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myocd.models.Entry
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class EntryRepository: ViewModel(){
    private val operationComplete = MutableLiveData<Boolean>(false);
    public val _readableOperationComplete: LiveData<Boolean> =  operationComplete;
    private val db = Firebase.database;
    fun saveEntryToDatabase(entry:Entry){
        viewModelScope.launch {
            try {
                val currentTime = LocalTime.now();
                val formattedDate = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                val formattedTime = currentTime
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss"));


                var entryRef = db.reference.child(formattedDate);
                val snapshot = entryRef.get().await();
                if (!snapshot.exists()) {
                    entryRef.push().setValue(formattedDate).await();
                }
                entryRef = entryRef.child(formattedTime);
                entryRef.setValue(entry).await();
                operationComplete.value = true;

            } catch (e: Exception) {
                println("saveEntryException: " + e.message);
            }
        }
    }

    fun loadEntries() {

    }

    fun loadEntry() {

    }

    fun updateEntry(){

    }
 }
