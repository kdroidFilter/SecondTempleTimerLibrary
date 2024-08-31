@file:OptIn(ExperimentalJsExport::class)

@JsModule("@js-joda/timezone")
@JsNonModule
external object JsJodaTimeZoneModule

@JsExport
class Provider {
    private val jsJodaTz = JsJodaTimeZoneModule

    private val provider = TimeIntervalProviderImpl()
    private val timeInterval = provider.calculateTimeIntervalSinceTempleDestruction()

    fun getTotalDays(): Int = provider.calculateDaysSinceTempleDestruction().toInt()

    fun getActualYears(): Int = timeInterval.years

    fun getActualMonths(): Int = timeInterval.months

    fun getActualDays(): Int = timeInterval.days

    fun getActualHours(): Int = timeInterval.hours

    fun getActualMinutes(): Int = timeInterval.minutes

    fun getActualSeconds(): Int = timeInterval.seconds
}
