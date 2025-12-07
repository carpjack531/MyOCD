package com.example.myocd.models


import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.Dispatchers


class EntryRepository {
    private val db = Firebase.database;
    suspend fun saveEntryToDatabase(entry:Entry): Boolean{
        withContext(Dispatchers.IO) {
            try {
                val currentTime = LocalTime.now();
                val formattedDate = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                val formattedTime = currentTime
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss"));


                var entryRef = db.reference
                    .child("entries")
                    .child(formattedDate)
                    .child(formattedTime);

                entryRef = entryRef.child(formattedTime);
                entryRef.setValue(entry).await();

            } catch (e: Exception) {
                println("saveEntryToDatabase Error: ${e.message}")
                return@withContext false;
            }
        }

        return true;
    }
    suspend fun getAllEntries(): Boolean{
        withContext(Dispatchers.IO){
            try{

            }
            catch(e:Exception){
                println("getAllEntries Error: ${e.message}")
                return@withContext false;
            }
        }

        return true;
    }

    fun getEntriesByDateTime(){

    }

    fun getEntriesByDate(){

    }




}
