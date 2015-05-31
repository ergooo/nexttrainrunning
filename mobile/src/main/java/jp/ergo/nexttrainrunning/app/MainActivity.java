package jp.ergo.nexttrainrunning.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import jp.ergo.nexttrainrunning.domain.location.LocationService;
import jp.ergo.nexttrainrunning.domain.station.StationEntry;
import jp.ergo.nexttrainrunning.domain.station.mock.StationEntryMock;
import jp.ergo.nexttrainrunning.domain.station.timetable.Time;
import jp.ergo.nexttrainrunning.domain.station.timetable.Timetable;
import jp.ergo.nexttrainrunning.domain.station.timetable.TimetableRepository;
import jp.ergo.nexttrainrunning.domain.station.timetable.TimetableUtils;
import jp.ergo.nexttrainrunning.infrastructure.notification.Notifier;

/**
 * Created by masato on 2015/05/30.
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final LinearLayout layout = new LinearLayout(this);
        setContentView(layout);
        final Button button = new Button(this);
        button.setText("notify");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StationEntry stationEntry = StationEntryMock.市ヶ谷駅有楽町線のぼり平日;
                final TimetableRepository timetableRepository = new TimetableRepository();
                final Timetable timetable = timetableRepository.getTimetable(stationEntry.getStation(),
                        stationEntry.getLine(), stationEntry.getDirection(), stationEntry.getDayOfWeek());

                final Time next = TimetableUtils.findNext(timetable, System.currentTimeMillis());
                final Time nextAfterNext = TimetableUtils.findNext(timetable, next);
                Notifier.notify(MainActivity.this, "次" + next.toString(), "その次" + nextAfterNext.toString());

            }
        });
        layout.addView(button);

        final Button service = new Button(this);
        service.setText("service");
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, LocationService.class));

            }
        });
        layout.addView(service);

    }
}
