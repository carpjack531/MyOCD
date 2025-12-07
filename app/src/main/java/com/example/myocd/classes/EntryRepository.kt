package com.example.myocd.classes


import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlin.collections.emptyList


class EntryRepository {
    private val db = Firebase.database;
    private val entriesRef = db.reference.child("entries");

    suspend fun saveEntryToDatabase(entry: Entry): String {
        withContext(Dispatchers.IO) {
            try {
                val currentTime = LocalTime.now();
                val formattedDate = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                val formattedTime = currentTime
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                //Automatically creates entry if one dosent exist
                val dateTimeSnapshot = entriesRef
                    .child(formattedDate)
                    .child(formattedTime);

                dateTimeSnapshot.setValue(entry).await();

            } catch (e: Exception) {
                return@withContext ("saveEntryToDatabase Error: ${e.message}")

            }
        }

        return "Entry saved successfully";
    }

    suspend fun getAllEntries(): List<Entry?> {
        var entryList: List<Entry?>;
        withContext(Dispatchers.IO) {
            try {
                val entriesSnapshot = entriesRef.get().await();
                if (!entriesSnapshot.exists()) {
                    throw Exception("Entries does not exist");
                }

                entryList = entriesSnapshot.children.map {
                    it.getValue(Entry::class.java)
                }

            } catch (e: Exception) {
                println("getAllEntries Error: ${e.message}")
                entryList = emptyList<Entry>();
            }
        }

        return entryList;
    }



    suspend fun getEntryDates(): List<String>?{
        var entryDates: List<String>? = null;
        withContext(Dispatchers.IO){
            try{
                val entriesSnapshot = entriesRef.get().await();
                if(!entriesSnapshot.exists()){
                    throw Exception("Entries does not exist, or has no dates");
                }

                entryDates =  entriesSnapshot.children.map{
                    it.key.toString();
                }
            }catch(e: Exception){

            }
        }

        return entryDates;
    }


    suspend fun getEntriesByDate(date: String): List<TimeEntry>? {
        //hopefully dosent cause issues
        var entriesInDate: List<TimeEntry>? = null;
        withContext(Dispatchers.IO) {
            try {
                val dateSnapshot = entriesRef
                    .child(date)
                    .get().await();

                if (!dateSnapshot.exists()) {
                    throw Exception("Date \"${date}\" does not exist");
                }

                entriesInDate = dateSnapshot.children.map {
                    val key: String = it.key.toString();
                    val value = it.getValue(Entry::class.java)

                    TimeEntry(key, value);

                }
                println("entryDates: ${entriesInDate.toString()}");

            } catch (e: Exception) {
                println("getEntriesByDate Error: ${e.message}");
                return@withContext emptyList<String>();
            }
        }

        return entriesInDate;
    }

    //Should return entry, for now dosen't
    suspend fun getEntryByDateTime(date: String, time: String): Entry? {
        withContext(Dispatchers.IO) {
            try {
                val dateTimeSnapshot = entriesRef
                    .child(date)
                    .child(time)
                    .get().await();

                //Doesn't tell you which failed, small issue for error readability
                if (!dateTimeSnapshot.exists()) {
                    throw Exception(
                        "dateTimeSnapshot (getEntryByDateTime):\n" +
                                "\"${date} ${time}\" does not exist"
                    );
                }

                return@withContext dateTimeSnapshot.getValue(Entry::class.java);

            } catch (e: Exception) {
                println("getEntryByDateTime Error: ${e.message}");

            }
        }

        return null;
    }
}

