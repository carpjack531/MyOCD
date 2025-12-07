package com.example.myocd.classes

 data class Entry(
    var trigger: String = "",
    var respDesc: String = "",
    var respSev:Int = -1,
    var obsession:String ="",
    var compulsion:String="",
    var assessment:String="",
    var outcome:Int=0,
)

data class TimeEntry(
   val time: String,
   val entry: Entry?,

)







