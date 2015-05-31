package jp.ergo.nexttrainrunning.domain.station.timetable;

import lombok.Data;

@Data
public class Time implements Comparable<Time> {
    private final Hour hour;
    private final Minute minute;

    public Time(final int hour, final int minute) {
        this.hour = Hour.of(hour);
        this.minute = Minute.of(minute);
    }

    @Override
    public int compareTo(Time other) {
        if (other.getHour().getHour() < hour.getHour()) {
            return 1;
        } else if (other.getHour().getHour() == hour.getHour()) {
            if (other.getMinute().getMinute() < minute.getMinute()) {
                return 1;
            } else if (other.getMinute().getMinute() == minute.getMinute()) {
                return 0;
            } else {
                return -1;
            }
        } else{
            return -1;
        }
    }

    /**
     *
     * @param other
     * @return otherより時間的に後であればtrue
     */
    public boolean isLaterThan(final Time other){
        return compareTo(other) > 0;
    }
    /**
     *
     * @param other
     * @return otherより時間的に前であればtrue
     */
    public boolean isEarierThan(final Time other){
        return compareTo(other) < 0;
    }

    @Override
    public String toString(){
        return String.format("%02d:%02d", hour.getHour(), minute.getMinute());
    }
}
