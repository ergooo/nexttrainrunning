package jp.ergo.nexttrainrunning.domain.location;

import junit.framework.TestCase;

import jp.ergo.nexttrainrunning.domain.location.Mock.LocationMock;
import jp.ergo.nexttrainrunning.domain.location.util.LocationUtils;

/**
 * Created by masato on 2015/05/31.
 */
public class NtrLocationRangeTest extends TestCase {

    public void testIsOutOfRange() throws Exception {
        final NtrLocation location = LocationMock.自宅;
        final LocationRange sut = LocationUtils.getRangeAround(location, 20);


        assertFalse(sut.isOutOfRange(location));
        assertTrue(sut.toString(), sut.isOutOfRange(new NtrLocation(35.696678, location.getLongitude())));
        assertTrue(sut.isOutOfRange(new NtrLocation(location.getLatitude(), 139.735842)));
        assertTrue(sut.isOutOfRange(new NtrLocation(35.695668, 139.736129)));

    }

}