import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import sternbach.software.kosherkotlin.hebrewcalendar.JewishDate
import kotlin.time.Duration.Companion.milliseconds

interface TimeIntervalProvider {
    fun getCurrentJewishDate(): JewishDate

    fun getSecondTempleDestructionDate(): JewishDate

    fun getTodayJerusalemSunset(): Long

    fun getYesterdayJerusalemSunset(): Long

    fun getCurrentTimeMillis(): Long

    private fun LocalDate.getTimeInMillis(timeZone: TimeZone = TimeZone.currentSystemDefault()): Long {
        val instant = this.atStartOfDayIn(timeZone)
        return instant.toEpochMilliseconds()
    }

    fun calculateDaysSinceTempleDestruction(): Long {
        val diffInMillis =
            getCurrentJewishDate().getGregorianDate().getTimeInMillis() -
                getSecondTempleDestructionDate().getGregorianDate().getTimeInMillis()
        return diffInMillis.milliseconds.inWholeDays
    }

    /**
     * Converts the number of days since the destruction of the Second Temple into Hebrew years, months, and days.
     *
     * @return A [TimeInterval] representing the difference in years, months, and days.
     */
    fun calculateTimeIntervalSinceTempleDestruction(): TimeInterval {
        val currentDate = getCurrentJewishDate()
        val templeDestructionDate = getSecondTempleDestructionDate()

        var years = currentDate.jewishYear - templeDestructionDate.jewishYear //  Calculate the difference in years between the current date and the temple Destruction date
        var months = currentDate.jewishMonth.value - templeDestructionDate.jewishMonth.value // Calculate the difference in months between the current date and the temple Destruction date
        var daysOfMonth = currentDate.jewishDayOfMonth - templeDestructionDate.jewishDayOfMonth // Calculate the difference in days of the month between the current date and the temple Destruction date

        val isLastDayOfMonth = currentDate.daysInJewishMonth == currentDate.jewishDayOfMonth

        val isSunsetPassed = getTodayJerusalemSunset() < getCurrentTimeMillis()

        println("isSunsetPassed $isSunsetPassed")
        if (isSunsetPassed) {
            if (isLastDayOfMonth) {
                // If it's the last day of the month and sunset has passed, move to the next month
                months += 1
                daysOfMonth = 1
            } else {
                // If sunset has passed but it's not the last day of the month, increment the day
                daysOfMonth += 1
            }
        }

        // Adjust days if the current day is before the start day
        if (daysOfMonth < 0) {
            // Move to the previous month and adjust days accordingly
            months -= 1
            val tempDate = JewishDate(currentDate.getGregorianDate().getTimeInMillis())
            tempDate.jewishMonth = tempDate.jewishMonth.previousMonth
            daysOfMonth += tempDate.daysInJewishMonth
        }

        // Adjust months if the current month is before the start month
        if (months < 0) {
            // Move to the previous year and adjust months accordingly
            years -= 1
            months += 12
        }

        /* Calculates the time difference between the current time and sunset
         If the current time is after today's sunset, calculates the difference with today's sunset.
         Otherwise, calculates the difference with yesterday's sunset. */
        val todaySunsetTime = getTodayJerusalemSunset()
        val yesterdaySunsetTime = getYesterdayJerusalemSunset()
        val currentTime = getCurrentTimeMillis()

        // Determines whether the difference should be calculated relative to today's or yesterday's sunset
        val diffInMillis = if (currentTime > todaySunsetTime) currentTime - todaySunsetTime else currentTime - yesterdaySunsetTime

        // Convert the difference to hours, minutes and seconds
        val hours = (diffInMillis.milliseconds.inWholeHours).toInt()
        val minutes = ((diffInMillis.milliseconds.inWholeMinutes) % 60).toInt()
        val seconds = ((diffInMillis.milliseconds.inWholeSeconds) % 60).toInt()

        return TimeInterval(calculateDaysSinceTempleDestruction().toInt(), years.toInt(), months, daysOfMonth, hours, minutes, seconds)
    }
}
