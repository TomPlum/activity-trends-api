package com.github.tomplum.activity.sleep

import java.time.LocalDateTime

data class SleepSnapshot(val date: LocalDateTime, val data: List<SleepSession>)