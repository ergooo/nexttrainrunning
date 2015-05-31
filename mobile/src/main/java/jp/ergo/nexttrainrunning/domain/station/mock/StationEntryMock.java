package jp.ergo.nexttrainrunning.domain.station.mock;

import jp.ergo.nexttrainrunning.domain.station.Direction;
import jp.ergo.nexttrainrunning.domain.station.Line;
import jp.ergo.nexttrainrunning.domain.location.NtrLocation;
import jp.ergo.nexttrainrunning.domain.station.Station;
import jp.ergo.nexttrainrunning.domain.station.StationEntry;
import jp.ergo.nexttrainrunning.domain.station.timetable.DayOfWeek;

/**
 * Created by masato on 2015/05/31.
 */
public class StationEntryMock {
    public static final StationEntry 市ヶ谷駅有楽町線のぼり平日 = new StationEntry(Station.市ヶ谷, Line.有楽町線, new NtrLocation(1000, 1000), new Direction("のぼり"),
            DayOfWeek.WEEK_DAY);
}
