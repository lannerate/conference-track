package conf.track.sys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhang3 on 11/3/16.
 */
public class Track {
    public List<Period> periods;

    public Track() {
        periods = new ArrayList<>();
    }

    public Track(List<Period> periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        periods.forEach(e -> builder.append(e));

        return builder.toString();
    }
}
