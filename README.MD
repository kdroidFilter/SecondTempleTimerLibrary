# SecondTempleTimer - Multiplatform Native Library

The `SecondTempleTimer` is a multiplatform library, generated using Kotlin, that can be compiled into native libraries for various platforms. 
It calculates the elapsed time since the destruction of the Second Temple, providing real-time updates in years, months, days, hours, minutes, and seconds. 
This library is designed to be versatile and can be integrated across different environments, including JavaScript, desktop, and mobile applications.

## Getting Started

### Build platform artifacts

#### Android aar

- Run `./gradlew :SecondTempleTimer:assembleRelease`
- Output: `/SecondTempleTimer/build/outputs/aar/SecondTempleTimer-release.aar`

#### JVM jar

- Run `./gradlew :SecondTempleTimer:jvmJar`
- Output: `/SecondTempleTimer/build/libs/SecondTempleTimer-jvm-1.0.jar`

#### iOS Framework

- Run `./gradlew :SecondTempleTimer:linkReleaseFrameworkIosArm64`
- Output: `/SecondTempleTimer/build/bin/iosArm64/releaseFramework/SecondTempleTimer.framework`

#### JS file

- Run `./gradlew :SecondTempleTimer:jsBrowserProductionWebpack`
- Output: `/SecondTempleTimer//build/kotlin-webpack/js/productionExecutable/SecondTempleTimer.js`

#### macOS Framework

- Run `./gradlew :SecondTempleTimer:linkReleaseFrameworkMacosArm64`
- Output: `/SecondTempleTimer/build/bin/macosArm64/releaseFramework/SecondTempleTimer.framework`

#### Linux static library

- Run `./gradlew :SecondTempleTimer:linkReleaseStaticLinuxX64`
- Output: `/SecondTempleTimer/build/bin/linuxX64/releaseStatic/libSecondTempleTimer.a`

#### Windows static library

- Run `./gradlew :SecondTempleTimer:linkReleaseStaticMingwX64`
- Output: `/SecondTempleTimer/build/bin/mingwX64/releaseStatic/libSecondTempleTimer.a`

#### Wasm binary file

- Run `./gradlew :SecondTempleTimer:wasmJsBrowserDistribution`
- Output: `/SecondTempleTimer/build/dist/wasmJs/productionExecutable/SecondTempleTimer-wasm-js.wasm`


### Example Usage in Kotlin (For Javascript Usage, see Below)

```kotlin
val provider = TimeIntervalProviderImpl()

// Get the total number of days since the destruction
val totalDays = provider.calculateDaysSinceTempleDestruction()

// Get the detailed time interval since the destruction
val timeInterval = provider.calculateTimeIntervalSinceTempleDestruction()

println("Total days: ${timeInterval.totalNumberOfDays}")
println("Years: ${timeInterval.years}, Months: ${timeInterval.months}, Days: ${timeInterval.days}")
println("Hours: ${timeInterval.hours}, Minutes: ${timeInterval.minutes}, Seconds: ${timeInterval.seconds}")
```


### Javascript Html Installation

To use the `SecondTempleTimer` library in your Javascript project, follow these steps:

1. **Generate the library:**
   Execute the following command in the root of the project:

- Run `./gradlew :SecondTempleTimer:jsBrowserProductionWebpack`

2. **Locate the output: :**

- Output: `/SecondTempleTimer//build/kotlin-webpack/js/productionExecutable/SecondTempleTimer.js`

3. **Include the library in your HTML file:: :**

```<script type="text/javascript" src="path/to/SecondTempleTimer.js"></script>'```

4. **Api Reference  (JS Only)**

The Provider class exposes several methods to retrieve different components of the time interval:

* getTotalDays(): Returns the total number of days since the destruction of the Second Temple.
* getActualYears(): Returns the number of years since the destruction.
* getActualMonths(): Returns the number of months since the last complete year.
* getActualDays(): Returns the number of days since the last complete month.
* getActualHours(): Returns the number of hours since the last complete day.
* getActualMinutes(): Returns the number of minutes since the last complete hour.
* getActualSeconds(): Returns the number of seconds since the last complete minute.

5. **Exemple Usage**

```javascript
<script type="text/javascript" src="https://github.com/kdroidFilter/SecondTempleTimerLibrary/releases/download/0.1/SecondTempleTimer.js"></script>
<script>
    function updateTimer() {
        var provider = new SecondTempleTimer.Provider();
        
        document.getElementById('totaldays').textContent = provider.getTotalDays();
        document.getElementById('years').textContent = provider.getActualYears();
        document.getElementById('months').textContent = provider.getActualMonths();
        document.getElementById('days').textContent = provider.getActualDays();
        document.getElementById('hours').textContent = provider.getActualHours();
        document.getElementById('minutes').textContent = provider.getActualMinutes();
        document.getElementById('seconds').textContent = provider.getActualSeconds();
    }

    // Update the timer every second
    setInterval(updateTimer, 1000);

    // Initial update
    updateTimer();
</script>
```

