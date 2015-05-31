package jp.ergo.nexttrainrunning.domain.station.timetable;

import lombok.Data;

/**
 * Created by masato on 2015/05/31.
 */
@Data
public class Minute {
    private final int minute;

    private Minute(final int minute){
        this.minute = minute;
    }

    public static Minute of(final int minute){
        return new Minute(minute);
    }
}
