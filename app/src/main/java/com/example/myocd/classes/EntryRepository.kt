@file:Suppress("RemoveCurlyBracesFromTemplate")

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


@Suppress("RemoveCurlyBracesFromTemplate")
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



    suspend fun getDates(): List<String>?{
        var dates: List<String>? = null;
        withContext(Dispatchers.IO){
            try{
                val entriesSnapshot = entriesRef.get().await();
                if(!entriesSnapshot.exists()){
                    throw Exception("Entries does not exist, or has no dates");
                }

                dates =  entriesSnapshot.children.map{
                    it.key.toString();
                }
            }
            catch(e: Exception){
                println("getEntryDates Error: ${e.message}");
            }
        }

        return dates;
    }

    suspend fun getTimesByDate(date:String): List<String>?{
        var times:List<String>? = null;
        withContext(Dispatchers.IO){
            try{
                val entriesSnapshot = entriesRef.child(date).get().await();
                if(!entriesSnapshot.exists()){
                    throw Exception("Entries does not exist, or has no dates");
                }

                times =  entriesSnapshot.children.map{
                    it.key.toString();
                }
            }
            catch(e: Exception){
                println("getEntryDates Error: ${e.message}");
            }

        }
        return times;
    }

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

