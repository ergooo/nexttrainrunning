package jp.ergo.nexttrainrunning.domain.location.util;

import jp.ergo.nexttrainrunning.domain.location.LocationRange;
import jp.ergo.nexttrainrunning.domain.location.NtrLocation;

/**
 * Created by masato on 2015/05/31.
 */
public class LocationUtils {

    /**
     * 基準となるLocationからrangeInMeterの範囲を求める
     *
     * @param location     基準となるLocation
     * @param rangeInMeter 範囲（メートル）
     * @return
     */
    public static LocationRange getRangeAround(final NtrLocation location, final int rangeInMeter) {
        return new LocationRange(getMinLocation(location, rangeInMeter), getMaxLocation(location, rangeInMeter));
    }

    /**
     * rangeInMeter足した緯度・経度を求める
     * 基準の緯度 + (範囲(m) ÷ 1秒当たりの緯度 × 1秒当たりの度)
     * 基準の経度 + (範囲(m) ÷ 1秒当たりの緯度 × 1秒当たりの度)
     *
     * @param location
     * @param rangeInMeter
     * @return
     */
    private static NtrLocation getMaxLocation(final NtrLocation location, final int rangeInMeter) {
        final double latitude = location.getLatitude() + (rangeInMeter / 30.8184 * 0.000277778);
        final double longitude = location.getLongitude() + (rangeInMeter / 25.2450 * 0.000277778);
        return new NtrLocation(latitude, longitude);
    }


    /**
     * rangeInMeter引いた緯度・経度を求める
     * 基準の緯度 - (範囲(m) ÷ 1秒当たりの緯度 × 1秒当たりの度)
     * 基準の経度 - (範囲(m) ÷ 1秒当たりの緯度 × 1秒当たりの度)
     *
     * @param location
     * @param rangeInMeter
     * @return
     */
    private static NtrLocation getMinLocation(final NtrLocation location, final int rangeInMeter) {
        final double latitude = location.getLatitude() - (rangeInMeter / 30.8184 * 0.000277778);
        final double longitude = location.getLongitude() - (rangeInMeter / 25.2450 * 0.000277778);
        return new NtrLocation(latitude, longitude);
    }

}
