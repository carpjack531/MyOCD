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

class EntryViewModel : ViewModel() {
    private val db = Firebase.database;
    private val entry = MutableLiveData<Entry>(Entry());
    private val page = MutableLiveData<Int>(1);
    private val operationComplete = MutableLiveData<Boolean>(false);
    public  val _readablePage: LiveData<Int> = page;
    public val _readableOperationComplete: LiveData<Boolean> =  operationComplete;




    fun setPage(newPage: Int) {
        println("setPage: $page");
        page.value = newPage;
    }

    fun setTriggerResponse(trigger: String, resp: String, respSev: String): Boolean {
        if (trigger.isBlank() && resp.isBlank() && respSev.isBlank()) {
            return false;
        }

        val sevNumber = respSev.toInt()
        if (sevNumber !in 0..100) {
            return false;
        }

        entry.value = entry.value?.copy(
            trigger = trigger,
            respSev = sevNumber,
            respDesc = resp
        );
        return true;
    }

    fun setObsessionCompulsion(obs: String, comp: String): Boolean {
        if (obs.isBlank() && comp.isBlank()) {
            return false;
        }

        entry.value = entry.value?.copy(obsession = obs, compulsion = comp);
        return true;
    }

    fun setAssessment(assess: String): Boolean {
        if (assess.isBlank()) {
            return false;
        }

        entry.value = entry.value?.copy(assessment = assess);
        return true;
    }

    fun setOutcome(out: String): Boolean {
        if (out.isBlank()) {
            return false;
        }

        val outNumber = out.toInt()
        if(outNumber !in 0..100){
            return false;
        }

        entry.value = entry.value?.copy(outcome = outNumber);
        return true
    }

    fun fetchEntries() {

    }

    fun fetchEntry() {

    }

    fun saveEntryToDatabase(){
        viewModelScope.launch {
            try {
                val currentTime = LocalTime.now();
                val formattedDate = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("yy-MM-dd"));

                var entryRef = db.reference.child(formattedDate);
                val snapshot = entryRef.get().await();
                if (!snapshot.exists()) {
                    entryRef.push().setValue(formattedDate).await();
                }
                entryRef = entryRef.child(formattedDate);
                entryRef.push().setValue(entry.value).await();
                operationComplete.value = true;

            } catch (e: Exception) {
                println("saveEntryException: " + e.message);
            }
        }
    }
}