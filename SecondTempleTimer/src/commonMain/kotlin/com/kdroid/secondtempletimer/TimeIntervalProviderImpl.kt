import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import sternbach.software.kosherkotlin.ComplexZmanimCalendar
import sternbach.software.kosherkotlin.hebrewcalendar.HebrewMonth
import sternbach.software.kosherkotlin.hebrewcalendar.JewishDate
import sternbach.software.kosherkotlin.util.GeoLocation

class TimeIntervalProviderImpl : TimeIntervalProvider {
    override fun getCurrentJewishDate(): JewishDate {
        return JewishDate()
    }

    override fun getSecondTempleDestructionDate(): JewishDate {
        return JewishDate(3830, HebrewMonth.AV, 9)
    }

    private fun getJerusalemSunset(dayOffset: Boolean): Long {

        // Jerusalem coordinates
        val latitude = 31.7683
        val longitude = 35.2137
        val elevation = 800.0 // in meters, approximate

        val timeZone = TimeZone.of("Asia/Jerusalem")

        val jerusalem = GeoLocation("Jerusalem", latitude, longitude, elevation, timeZone)

        val sunset = ComplexZmanimCalendar(jerusalem).sunset!!.toEpochMilliseconds()
        // Apply the day offset
        if (dayOffset) {
            return sunset
        } else
            return sunset - (1000 * 60 * 60 * 24)
    }

    override fun getTodayJerusalemSunset(): Long {
        return getJerusalemSunset(true)
    }

    override fun getYesterdayJerusalemSunset(): Long {
        return getJerusalemSunset(false)
    }

    override fun getCurrentTimeMillis(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }

}