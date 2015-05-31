package jp.ergo.nexttrainrunning.domain.station.timetable;

import lombok.Data;

/**
 * Created by masato on 2015/05/31.
 */
@Data
public class Hour {
    private final int hour;

    private Hour(final int hour){
        this.hour = hour;
    }
    public static Hour of(final int hour){
        return new Hour(hour);
    }

}
