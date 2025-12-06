package com.example.myocd.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myocd.models.Entry

//Main issue is the pages, they work fine-but they could 100% be a seperate viewmodel
class AddEntryViewModel : ViewModel() {

    private val entry = MutableLiveData<Entry>(Entry());
    private val page = MutableLiveData<Int>(1);
    val _readablePage: LiveData<Int> = page;
    val _readableEntry: LiveData<Entry> = entry;

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




    fun printEntryString(){
        entry.value.apply{
            println("Trigger: ${trigger}" +
                    "\nResponse Desc: ${respDesc}" +
                    "\nResponse Sev: ${respSev}" +
                    "\nObsession: ${obsession}" +
                    "$\nCompulsion: ${compulsion}" +
                    "\nAssessment: ${assessment}" +
                    "\nOutcome: ${outcome}")
        }

    }


}