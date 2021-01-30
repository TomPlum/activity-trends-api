package com.github.tomplum.activity.reader

import com.github.tomplum.activity.IntegrationTest
import com.github.tomplum.activity.xml.health.AppleHealthData
import com.github.tomplum.activity.xml.health.route.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.nio.file.Paths

@IntegrationTest
class XMLReaderTest @Autowired constructor(private val reader: XMLReader) {
    @Test
    fun example() {
        val path = "/media/tom/T.Plumpton 2TB Ext HDD/My Documents/Apple Health Exports/export/apple_health_export/export.xml"
        val data = reader.read<AppleHealthData>(path)
        assertThat(data).isNotNull()
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