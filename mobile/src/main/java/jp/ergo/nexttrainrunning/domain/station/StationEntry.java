package jp.ergo.nexttrainrunning.domain.station;


import jp.ergo.nexttrainrunning.domain.location.NtrLocation;
import jp.ergo.nexttrainrunning.domain.station.timetable.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by masato on 2015/05/30.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class StationEntry {
    private final Station station;
    private final Line line;
    private final NtrLocation location;
    private final Direction direction;
    private final DayOfWeek dayOfWeek;
}
