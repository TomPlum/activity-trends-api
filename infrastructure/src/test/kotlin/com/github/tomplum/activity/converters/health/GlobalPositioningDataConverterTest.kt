package com.github.tomplum.activity.converters.health

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomplum.activity.workout.route.ExerciseRoute
import com.github.tomplum.activity.workout.route.HealthDataSource
import com.github.tomplum.activity.workout.route.Route
import com.github.tomplum.activity.xml.health.WorkoutRoute
import com.github.tomplum.activity.xml.health.route.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class GlobalPositioningDataConverterTest {
    private val converter = GlobalPositioningDataConverter()

    @Test
    fun `Valid`() {
        val source = getValidData()
        val response = converter.convert(source)
        assertThat(response).isEqualTo(getExpectedExerciseRoute())
    }

    private fun getExpectedExerciseRoute() = ExerciseRoute(
        Route(emptyList()), LocalDateTime.now(), HealthDataSource("Apple Health Export", "1.1")
    )

    private fun getValidData() = GlobalPositioningData(
        version = "1.1",
        creator = "Apple Health Export",
        metadata = MetaData(time="2020-08-23T17:40:02Z"),
        track = Track(
            name="Route 2020-07-29 3:13pm",
            segment= TrackSegment(
                parts= mutableListOf(
                    TrackPart(
                        longitude="-2.538323",
                        latitude="53.252917",
                        elevation="21.476473",
                        time="2020-07-29T13:51:19Z",
                        extensions=Extensions(
                            speed="1.642115",
                            course="-1.000000",
                            horizontalAcceleration="3.723220",
                            verticalAcceleration="2.774929"
                        )
                    ),
                    TrackPart(
                        longitude="-2.538320",
                        latitude="53.252903",
                        elevation="22.036276",
                        time="2020-07-29T13:51:20Z",
                        extensions=Extensions(
                            speed="1.641947",
                            course="-1.000000",
                            horizontalAcceleration="3.388743",
                            verticalAcceleration="2.516615"
                        )
                    )
                )
            )
        )
    )
}