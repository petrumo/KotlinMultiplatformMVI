package com.emv.kotlinmultiplatformmvi


//fun Schedules.startTime(): Long {
//    val time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(start)
//    val cal = Calendar.getInstance()
//    cal.set(Calendar.HOUR, time.hours)
//    cal.set(Calendar.MINUTE, time.minutes)
//    cal.set(Calendar.SECOND, time.seconds)
//    return cal.timeInMillis
//}
//
//fun Schedules.endTime(): Long {
//    val time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(end)
//    val cal = Calendar.getInstance()
//    cal.set(Calendar.HOUR, time.hours)
//    cal.set(Calendar.MINUTE, time.minutes)
//    cal.set(Calendar.SECOND, time.seconds)
//    return cal.timeInMillis
//}
//
//fun Channels.getCurrent(now: Long): Schedules? {
//    return this.schedules.findLast { s -> s.startTime() <= now && s.endTime() > now }
//}


class ChannelUtil {

    companion object {

        //"2018-10-26T00:00:00+02:00"
//        private val of: DateFormat = SimpleDateFormat("HH:mm")
//
//
//        @JvmStatic
//        fun getSchedule(schedule: Schedules?): String {
//            if (schedule != null) {
//                val start = of.format(schedule.startTime())
//                val end = of.format(schedule.endTime())
//                return "$start - $end"
//            } else {
//                return ""
//            }
//        }
//
//        @JvmStatic
//        fun getProgress(schedule: Schedules?): Int {
//            if (schedule != null) {
//                val start = schedule.startTime()
//                val end = schedule.endTime()
//                val now = System.currentTimeMillis()
//                val p = ((now - start).toDouble() / (end - start).toDouble() * 100.0).toInt()
//                return p
//            } else {
//                return 0
//            }
//        }
//
//        @JvmStatic
//        fun getStar(context: Context, channel: Channels?): Drawable? {
//            if (channel != null) {
//                return AppCompatResources.getDrawable(
//                    context,
//                    if (channel.favorite) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp
//                )
//            }
//            return AppCompatResources.getDrawable(context, R.drawable.ic_star_border_black_24dp)
//        }
    }
}