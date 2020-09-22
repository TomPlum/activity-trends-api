package com.github.tomplum.activity

class DataFactory {
    companion object {
        fun sleepDataCsvResponse(): List<MutableMap<String, String?>> = listOf(mutableMapOf(
                Pair("Start Time", "2018-08-21 21:44:03 +0000"),
                Pair("End Time", "2018-08-22 06:00:56 +0000"),
                Pair("Duration (mins)", "497"),
                Pair("Nap", "NO"),
                Pair("Sleep Quality (%)", "60"),
                Pair("Time Awake (mins)", "127"),
                Pair("Time in REM Slep (mins)", "70"),
                Pair("Time in Light Sleep (mins)", "150"),
                Pair("Time In Deep Sleep (mins)", "150"),
                Pair("Sounds Recorded", "11"),
                Pair("Wake-up mood", "Good")
        ))
    }
}