package ng.sampleproject.sampleproject.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by USER on 10/08/2019.
 */
object DateUtils {


    private fun getTime(date: String): String {
        var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val d = dateFormat.parse(date)
        dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(d)
    }


    private fun getTimeDefault(date: Date): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(date)
    }


    fun getMatchTime(startTime: String, status: String): String {
        val cal = Calendar.getInstance()
        cal.timeZone = TimeZone.getTimeZone("UTC")
        val matchTime: Date = cal.time
        val start = getTime(startTime)
        val current = getTimeDefault(matchTime)
        val time = getMinutes(current) - getMinutes(start)
        return if (time > 90 || status=="FINISHED")
            "FT"
        else
            "$time"
    }

    private fun getMinutes(date: String): Int {
        return date.substring(0, 2).toInt() * 60 + date.substring(3).toInt()
    }


    fun convertDate(date: String): String {
        var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val dateFormatted = dateFormat.parse(date)
        dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(dateFormatted)
    }
}