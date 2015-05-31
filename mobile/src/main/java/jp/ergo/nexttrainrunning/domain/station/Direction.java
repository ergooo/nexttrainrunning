package jp.ergo.nexttrainrunning.domain.station;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by masato on 2015/05/30.
 * ～方面、～行きとかそういうの
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Direction {
    private final String displayName;
}
