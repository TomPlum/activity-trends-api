package activity.reader.headers

enum class SleepDataHeaders(val header: String) : CsvHeader {
    START_TIME("Start Time"),
    END_TIME("End Time"),
    DURATION("Duration (mins)"),
    NAP("Nap"),
    SLEEP_QUALITY("Sleep Quality (%)"),
    TIME_AWAKE("Time Awake (mins)"),
    REM_SLEEP("Time in REM Slep (mins)"),
    LIGHT_SLEEP("Time in Light Sleep (mins)"),
    DEEP_SLEEP("Time In Deep Sleep (mins)"),
    SOUNDS_RECORDED("Sounds Recorded"),
    WAKE_UP_MOOD("Wake-up mood")
}