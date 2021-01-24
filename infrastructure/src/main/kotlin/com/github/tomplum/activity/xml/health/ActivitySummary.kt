package com.github.tomplum.activity.xml.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("ActivitySummary")
data class ActivitySummary (
    @JacksonXmlProperty(isAttribute = true, localName = "dateComponents")
    var dateComponents: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "activeEnergyBurned")
    var activeEnergyBurned: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "activeEnergyBurnedGoal")
    var activeEnergyBurnedGoal: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "activeEnergyBurnedUnit")
    var activeEnergyBurnedUnit: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "appleMoveTime")
    var appleMoveTime: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "appleMoveTimeGoal")
    var appleMoveTimeGoal: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "appleExerciseTime")
    var appleExerciseTime: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "appleExerciseTimeGoal")
    var appleExerciseTimeGoal: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "appleStandHours")
    var appleStandHours: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "appleStandHoursGoal")
    var appleStandHoursGoal: String?,
)