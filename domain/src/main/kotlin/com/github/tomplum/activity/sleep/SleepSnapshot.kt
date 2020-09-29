package com.github.tomplum.activity.sleep

import java.time.LocalDate

data class SleepSnapshot(val date: LocalDate, val data: List<SleepSession>)