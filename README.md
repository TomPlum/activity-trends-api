# :running: Activity Trends API

## About
See the ReactJS front-end [here](https://github.com/TomPlum/com.github.tomplum.activity-trends)

## Apple Health Data
The native iOS Health App produces an XML file containing all the data recorded from relevant devices (Phone, Watch etc.).
My export was ~1GB and ~3million lines, making it slow to de-serialise.

#### Record
Various `<Record/>` elements store data such as your height, body mass, heart rate etc.

    <Record
        type="HKQuantityTypeIdentifierHeight"
        sourceName="Tom’s iPhone X"
        sourceVersion="11.1"
        unit="ft"
        creationDate="2017-10-03 21:35:31 +0100"
        startDate="2017-10-03 21:35:31 +0100"
        endDate="2017-10-03 21:35:31 +0100"
        value="5.67585"
    />

    <Record
        type="HKQuantityTypeIdentifierBodyMass"
        sourceName="MyFitnessPal"
        sourceVersion="29814"
        unit="kg"
        creationDate="2020-05-15 15:13:45 +0100"
        startDate="2020-05-15 15:13:00 +0100"
        endDate="2020-05-15 15:13:00 +0100"
        value="80.1498"
    />

    <Record
        type="HKQuantityTypeIdentifierHeartRate"
        sourceName="Tom’s Apple Watch"
        sourceVersion="4.0"
        device="<<HKDevice: 0x280d6bac0>, name:Apple Watch, manufacturer:Apple, model:Watch, hardware:Watch3,4, software:4.0>"
        unit="count/min"
        creationDate="2017-09-28 13:48:11 +0100"
        startDate="2017-09-28 13:23:19 +0100"
        endDate="2017-09-28 13:23:19 +0100"
        value="80"
    >
        <MetadataEntry key="HKMetadataKeyHeartRateMotionContext" value="0"/>
    </Record>

#### Workout
Workout records store most of their data in the `<Workout/>` element as attributes, but there are optional
`<MetadataEntry/>` elements nested inside subject to the `workoutActivityType` attribute.

    <Workout 
        workoutActivityType="HKWorkoutActivityTypeElliptical"
        duration="16.36445068319638"
        durationUnit="min"
        totalDistance="0"
        totalDistanceUnit="km"
        totalEnergyBurned="177.234"
        totalEnergyBurnedUnit="kcal"
        sourceName="Tom’s Apple Watch"
        sourceVersion="4.0"
        creationDate="2017-10-02 20:10:35 +0100"
        startDate="2017-10-02 19:54:13 +0100"
        endDate="2017-10-02 20:10:35 +0100"
    >
        <MetadataEntry key="HKTimeZone" value="Europe/London"/>
        <MetadataEntry key="HKWeatherTemperature" value="53 degF"/>
        <MetadataEntry key="HKWeatherHumidity" value="8300 %"/>
    </Workout>

#### Global Positioning Service Exchange Format (GPX)
Outdoor workouts record the route you took and stores the data in a
[GPS Exchange Format](https://en.wikipedia.org/wiki/GPS_Exchange_Format) `.gpx` file. An example file is shown below
with only the first 2 track parts included for brevity.

    <?xml version="1.0" encoding="UTF-8"?>
    <gpx version="1.1" creator="Apple Health Export" xmlns="http://www.topografix.com/GPX/1/1"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd">
        <metadata>
            <time>2020-08-23T17:40:02Z</time>
        </metadata>
        <trk>
            <name>Route 2020-07-29 3:13pm</name>
            <trkseg>
                <trkpt lon="-2.538323" lat="53.252917">
                    <ele>21.476473</ele>
                    <time>2020-07-29T13:51:19Z</time>
                    <extensions>
                        <speed>1.642115</speed>
                        <course>-1.000000</course>
                        <hAcc>3.723220</hAcc>
                        <vAcc>2.774929</vAcc>
                    </extensions>
                </trkpt>
                <trkpt lon="-2.538320" lat="53.252903">
                    <ele>22.036276</ele>
                    <time>2020-07-29T13:51:20Z</time>
                        <extensions>
                            <speed>1.641947</speed>
                            <course>-1.000000</course>
                            <hAcc>3.388743</hAcc>
                            <vAcc>2.516615</vAcc>
                        </extensions>
                </trkpt>

                ...
                    
            </trkseg>
        </trk>
    </gpx>