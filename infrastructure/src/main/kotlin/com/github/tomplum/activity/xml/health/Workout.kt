package com.github.tomplum.activity.xml.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("Workout")
data class Workout (
    @JacksonXmlProperty(isAttribute = true, localName = "workoutActivityType")
    var type: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "duration")
    var duration: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "durationUnit")
    var durationUnit: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "totalDistance")
    var totalDistance: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "totalDistanceUnit")
    var totalDistanceUnit: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "totalEnergyBurned")
    var totalEnergyBurned: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "totalEnergyBurnedUnit")
    var totalEnergyBurnedUnit: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "sourceName")
    var sourceName: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "sourceVersion")
    var sourceVersion: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "device")
    var device: String? = null,

    @JacksonXmlProperty(isAttribute = true, localName = "creationDate")
    var creationDate: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "startDate")
    var startDate: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "endDate")
    var endDate: String?,

    @JacksonXmlProperty(localName = "WorkoutEvent")
    var events: List<WorkoutEvent> = emptyList(),

    @JacksonXmlProperty(localName = "WorkoutRoute")
    var route: WorkoutRoute?,

    @JacksonXmlProperty(localName = "MetadataEntry")
    var metedata: List<MetadataEntry> = emptyList()
)