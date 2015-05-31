package jp.ergo.nexttrainrunning.domain.station.timetable;

import jp.ergo.nexttrainrunning.domain.station.Direction;
import jp.ergo.nexttrainrunning.domain.station.Line;
import jp.ergo.nexttrainrunning.domain.station.Station;
import jp.ergo.nexttrainrunning.domain.station.mock.TimetableMock;
import lombok.Data;

/**
 * Created by masato on 2015/05/30.
 */
@Data
public class TimetableRepository {

    public Timetable getTimetable(final Station station, final Line line, final Direction direction, final DayOfWeek dayOfWeek){
        return TimetableMock.市ヶ谷駅有楽町線のぼり平日;
    }
}
