package jp.ergo.nexttrainrunning.domain.location;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by masato on 2015/05/31.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class LocationRange {

    private final NtrLocation minLocation;
    private final NtrLocation maxLocation;

    public double getMinLatitude() {
        return minLocation.getLatitude();
    }

    public double getMaxLatitude() {
        return maxLocation.getLatitude();
    }

    public double getMinLongitude() {
        return minLocation.getLongitude();
    }

    public double getMaxLongitude() {
        return maxLocation.getLongitude();
    }

    /**
     * 渡された緯度・経度が範囲外であるかどうか
     *
     * @param location
     * @return
     */
    public boolean isOutOfRange(final NtrLocation location) {
        return (getMinLatitude() > location.getLatitude()
                || getMaxLatitude() < location.getLatitude()
                || getMinLongitude() > location.getLongitude()
                || getMaxLongitude() < location.getLongitude());
    }

}
