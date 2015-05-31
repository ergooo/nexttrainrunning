package jp.ergo.nexttrainrunning.domain.station;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by masato on 2015/05/30.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class StationInfo {
    private final String displayName;
    private final List<Line> lines;

}
