package jp.ergo.nexttrainrunning.domain.location;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import jp.ergo.nexttrainrunning.domain.location.Mock.LocationMock;
import jp.ergo.nexttrainrunning.domain.location.util.LocationUtils;
import jp.ergo.nexttrainrunning.domain.station.StationEntry;
import jp.ergo.nexttrainrunning.domain.station.mock.StationEntryMock;
import jp.ergo.nexttrainrunning.domain.station.timetable.Time;
import jp.ergo.nexttrainrunning.domain.station.timetable.Timetable;
import jp.ergo.nexttrainrunning.domain.station.timetable.TimetableRepository;
import jp.ergo.nexttrainrunning.domain.station.timetable.TimetableUtils;
import jp.ergo.nexttrainrunning.infrastructure.notification.Notifier;

public class LocationService extends Service implements LocationListener {

    private static final String TAG = "LocationService";
    private LocationManager mLocationManager;

    private NtrLocation previousLocation;


    @Override
    public void onCreate() {
        Toast.makeText(this, "バックグラウンドサービスを開始しました。", Toast.LENGTH_SHORT).show();
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand Received start id " + startId + ": " + intent);
//        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, this);
        //明示的にサービスの起動、停止が決められる場合の返り値
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        mLocationManager.removeUpdates(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
//        Toast.makeText(this, "位置情報を更新", Toast.LENGTH_SHORT).show();
        final NtrLocation currentLocation = new NtrLocation(location.getLatitude(), location.getLongitude());

        if (previousLocation != null) {
            // 前回自宅範囲内で今回範囲外だったら通知する
            final LocationRange locationRange = LocationUtils.getRangeAround(LocationMock.自宅, 20);
            final boolean isPrevInRange = !locationRange.isOutOfRange(previousLocation);
            final boolean isCurrentOutOfRange = locationRange.isOutOfRange(currentLocation);
            if(isPrevInRange && isCurrentOutOfRange){

                final StationEntry stationEntry = StationEntryMock.市ヶ谷駅有楽町線のぼり平日;
                final TimetableRepository timetableRepository = new TimetableRepository();
                final Timetable timetable = timetableRepository.getTimetable(stationEntry.getStation(),
                        stationEntry.getLine(), stationEntry.getDirection(), stationEntry.getDayOfWeek());

                final Time next = TimetableUtils.findNext(timetable, System.currentTimeMillis());
                final Time nextAfterNext = TimetableUtils.findNext(timetable, next);
                Notifier.notify(this, "次" + next.toString(), "その次" + nextAfterNext.toString());
            }
        }
        previousLocation = currentLocation;
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

}