package conf.track.sys.uitls;

import conf.track.sys.DurationUnit;
import conf.track.sys.Event;

import java.io.BufferedReader;
import java.io.File;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hzhang3 on 11/4/2016.
 */
public class EventParser {
    public static final int INDEX_EVENET_DESC = 1;
    public static final int INDEX_EVENET_DURATION = 2;
    public static final int INDEX_EVENET_DURATION_UNIT = 3;


    public static List<Event> parse(String filePath) throws Exception {
        List<Event> events = new ArrayList<>();

        BufferedReader reader = FileUtil.readFile(filePath);
        for (String line; !(line = reader.readLine()).isEmpty(); ) {
            Event event = parseLine(line);
            events.add(event);
        }

        return events;
    }

    public static Event parseLine(String line) {

        if (line == null || line.isEmpty()) return null;
        /**
         *  Common Ruby Errors 45min
         *  Rails for Python Developers lightning
         *  Communicating Over Distance 60min
         */
        //using regex expression to parse event's fields.

        Matcher matcher = Pattern.compile("^(.+)\\s(\\d+)?\\s?((min)|(lightning))$").matcher(line);

        if (!matcher.find()) {
            return null;
        }

        String durationUnitStr = matcher.group(INDEX_EVENET_DURATION_UNIT);
        if (durationUnitStr.isEmpty()) {
            return null;
        }

        String description = matcher.group(INDEX_EVENET_DESC);
        String duration = matcher.group(INDEX_EVENET_DURATION);

        return new Event(
                description,
                duration == null || duration.isEmpty() ? 1 : Integer.parseInt(duration),
                durationUnitStr.equalsIgnoreCase(DurationUnit.MINUTES.getName()) ? DurationUnit.MINUTES : DurationUnit.LIGHTENING
        );
    }
}
