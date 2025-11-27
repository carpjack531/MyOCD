package com.example.myocd.models




public data class Entry (
    private var trigger: String = "",
    private var respDesc: String = "",
    private var respSeverity:Int = -1,
    var obsession:String ="",
    var compulsion:String="",
    var assessment:String="",
    var outcome:Int=0,

)


