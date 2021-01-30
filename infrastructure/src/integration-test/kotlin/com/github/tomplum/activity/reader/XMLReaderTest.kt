package com.github.tomplum.activity.reader

import com.github.tomplum.activity.IntegrationTest
import com.github.tomplum.activity.xml.health.*
import com.github.tomplum.activity.xml.health.route.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.nio.file.Paths

@IntegrationTest
class XMLReaderTest @Autowired constructor(private val reader: XMLReader) {
    @Test
    fun example() {
        val path = Paths.get(this.javaClass.getResource("/example-health-export.xml").toURI()).toFile().path
        val data = reader.read<AppleHealthData>(path)
        assertThat(data).isEqualTo(
            AppleHealthData(
                exportDate = ExportDate(value = "2020-08-23 18:37:51 +0100"),
                personalData = PersonalData(
                    dateOfBirth = "1997-03-02",
                    sex = "HKBiologicalSexMale",
                    bloodType = "HKBloodTypeNotSet",
                    skinType = "HKFitzpatrickSkinTypeNotSet"
                ),
                records = mutableListOf(
                    HealthRecord(
                        type = "HKQuantityTypeIdentifierDietaryWater",
                        sourceName = "\uD83D\uDCA7Aqueous",
                        sourceVersion = "3.4.144",
                        unit = "mL",
                        creationDate = "2018-06-26 12:54:42 +0100",
                        startDate = "2018-06-26 12:54:42 +0100",
                        endDate = "2018-06-26 12:54:42 +0100",
                        value = "500"
                    ),
                    HealthRecord(
                        type = "HKQuantityTypeIdentifierHeight",
                        sourceName = "Tom’s iPhone X",
                        sourceVersion = "11.1",
                        unit = "ft",
                        creationDate = "2017-10-03 21:35:31 +0100",
                        startDate = "2017-10-03 21:35:31 +0100",
                        endDate = "2017-10-03 21:35:31 +0100",
                        value = "5.67585"
                    ),
                    HealthRecord(
                        type = "HKQuantityTypeIdentifierBodyMass",
                        sourceName = "MyFitnessPal",
                        sourceVersion = "29814",
                        unit = "kg",
                        creationDate="2020-05-15 15:13:45 +0100",
                        startDate="2020-05-15 15:13:00 +0100",
                        endDate="2020-05-15 15:13:00 +0100",
                        value="80.1498"
                    ),
                    HealthRecord(
                        type="HKQuantityTypeIdentifierHeartRate",
                        sourceName="Tom’s Apple Watch",
                        sourceVersion="4.0",
                        device="<<HKDevice: 0x280d6bac0>, name:Apple Watch, manufacturer:Apple, model:Watch, hardware:Watch3,4, software:4.0>",
                        unit="count/min",
                        creationDate="2017-09-28 13:48:11 +0100",
                        startDate="2017-09-28 13:23:19 +0100",
                        endDate="2017-09-28 13:23:19 +0100",
                        value="80",
                        metadata = mutableListOf(
                            MetadataEntry(key="HKMetadataKeyHeartRateMotionContext", value="0")
                        )
                    )
                ),
                workouts = mutableListOf(
                    Workout(
                        type ="HKWorkoutActivityTypeElliptical",
                        duration="16.36445068319638",
                        durationUnit="min",
                        totalDistance="0",
                        totalDistanceUnit="km",
                        totalEnergyBurned="177.234",
                        totalEnergyBurnedUnit="kcal",
                        sourceName="Tom’s Apple Watch",
                        sourceVersion="4.0",
                        creationDate="2017-10-02 20:10:35 +0100",
                        startDate="2017-10-02 19:54:13 +0100",
                        endDate="2017-10-02 20:10:35 +0100",
                        metedata = mutableListOf(
                            MetadataEntry(key = "HKTimeZone", value = "Europe/London"),
                            MetadataEntry(key = "HKWeatherTemperature", value = "53 degF"),
                            MetadataEntry(key = "HKWeatherHumidity", value = "8300 %")
                        )
                    )
                )
            )
        )
    }

    @Test
    fun workoutRoute() {
        val path = Paths.get(this.javaClass.getResource("/example-route.gpx").toURI()).toFile().path
        val data = reader.read<GlobalPositioningData>(path)
        assertThat(data).isEqualTo(
            GlobalPositioningData(
                version = "1.1",
                creator = "Apple Health Export",
                metadata = MetaData(time = "2020-08-23T17:40:02Z"),
                track = Track(
                    name = "Route 2020-07-29 3:13pm",
                    segment = TrackSegment(
                        parts = mutableListOf(
                            TrackPart(
                                elevation = "21.476473",
                                time = "2020-07-29T13:51:19Z",
                                extensions = Extensions(
                                    speed = "1.642115",
                                    course = "-1.000000",
                                    horizontalAcceleration = "3.723220",
                                    verticalAcceleration = "2.774929"
                                ),
                                longitude = "-2.538323",
                                latitude = "53.252917"
                            ),
                            TrackPart(
                                elevation = "22.036276",
                                time = "2020-07-29T13:51:20Z",
                                extensions = Extensions(
                                    speed = "1.641947",
                                    course = "-1.000000",
                                    horizontalAcceleration = "3.388743",
                                    verticalAcceleration = "2.516615"
                                ),
                                longitude = "-2.538320",
                                latitude = "53.252903"
                            )
                        )
                    )
                )
            )
        )
    }
}