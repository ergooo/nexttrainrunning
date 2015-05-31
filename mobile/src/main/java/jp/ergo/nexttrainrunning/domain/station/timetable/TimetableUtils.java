package jp.ergo.nexttrainrunning.domain.station.timetable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by masato on 2015/05/31.
 */
public class TimetableUtils {

    /**
     * @param timetable
     * @param current   任意の時間。
     * @return timetable内にある時間の中で、current移行最も近い時間
     */
    public static Time findNext(final Timetable timetable, final Time current) {
        if (timetable == null || timetable.isEmpty()) {
            throw new IllegalArgumentException("Timetable should not be empty.");
        }
        for (final Time time : timetable) {
            if (current.isEarierThan(time)) {
                return time;
            }
        }

        return timetable.get(0);
    }

    public static Time findNext(final Timetable timetable, final long current) {
        return findNext(timetable, convertToTime(current));
    }

    private static Time convertToTime(final long current) {
        final Date date = new Date(current);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        return new Time(hour, minute);
    }

}
