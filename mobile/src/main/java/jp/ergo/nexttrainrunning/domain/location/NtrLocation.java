package jp.ergo.nexttrainrunning.domain.location;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by masato on 2015/05/30.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class NtrLocation {
    private final double latitude;
    private final double longitude;
}
