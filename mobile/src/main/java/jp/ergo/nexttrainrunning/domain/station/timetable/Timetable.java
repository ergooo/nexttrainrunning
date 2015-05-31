package jp.ergo.nexttrainrunning.domain.station.timetable;

import java.util.ArrayList;

import jp.ergo.nexttrainrunning.domain.station.Direction;
import jp.ergo.nexttrainrunning.domain.station.Line;
import jp.ergo.nexttrainrunning.domain.station.Station;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by masato on 2015/05/30.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Timetable extends ArrayList<Time> {
    private final Station station;
    private final Line line;
    private final Direction direction;
    private final DayOfWeek dayOfWeek;


}
