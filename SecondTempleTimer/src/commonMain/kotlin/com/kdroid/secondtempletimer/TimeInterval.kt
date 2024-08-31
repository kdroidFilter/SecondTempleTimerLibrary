
import kotlinx.serialization.Serializable

@Serializable
data class TimeInterval(
    val totalNumberOfDays: Int,
    val years: Int,
    val months: Int,
    val days: Int,
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
)
