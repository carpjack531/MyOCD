package com.example.myocd.models


public enum class EntryType{
    TRIGGER,
    RESP_DESC,
    RESP_SEV,
    OBSESSION,
    COMPULSION,
    ASSESSMENT,
    OUTCOME,
}

public data class Entry (
    var trigger: String = "",
    var respDesc: String = "", var respSeverity:Int = -1,
    var obsession:String ="",
    var compulsion:String="",
    var assessment:String="",
    var outcome:Int=0,
)

public class EntryWrapper {
    var entry: Entry = Entry();
    fun setValue(data: String, type: EntryType): Boolean {

        if (data.isNotEmpty()) {
            when (type) {
                EntryType.TRIGGER -> entry.trigger = data
                EntryType.COMPULSION -> entry.compulsion = data
                EntryType.RESP_DESC -> entry.respDesc = data
                EntryType.ASSESSMENT -> entry.assessment = data;
                EntryType.RESP_SEV -> {
                    val num = Integer.parseInt(data);
                    if(num in 0..100)
                        entry.respSeverity = num;
                    else
                        return false;
                }
                EntryType.OUTCOME -> {
                    val num = Integer.parseInt(data);
                    if(num in 0..100)
                        entry.respSeverity = num;
                    else
                        return false;
                }
                EntryType.OBSESSION -> entry.obsession = data
                else -> return false;

            }

            return true;
        }
        return false;
    }

    fun isInRange(data:String):Boolean{
        return Integer.parseInt(data) in 0..100
    }
}





