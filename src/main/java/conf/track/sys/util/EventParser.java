package conf.track.sys.util;

import conf.track.sys.DurationUnit;
import conf.track.sys.Event;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static conf.track.sys.util.Configure.MAX_EVENT_DURATION;

/**
 * Created by hzhang3 on 11/4/2016.
 */
public class EventParser {

    private static Logger logger = Logger.getLogger();

    public static final int INDEX_EVENET_DESC = 1;
    public static final int INDEX_EVENET_DURATION = 2;
    public static final int INDEX_EVENET_DURATION_UNIT = 3;


    public static List<Event> parse(String filePath) throws Exception {
        List<Event> events = new ArrayList<>();

        BufferedReader reader = FileUtil.readFile(filePath);
        for (String line; (line = reader.readLine()) != null; ) {
            Event event = parseLine(line);
            if (event != null) events.add(event);
        }

        return events;
    }

    public static Event parseLine(String line) {

        if (line == null || line.isEmpty()) return null;

        /** the sample
         *  Common Ruby Errors 45min
         *  Rails for Python Developers lightning
         *  Communicating Over Distance 60min
         */

//       Using the regex expression to parse event's fields.
//      ^(.+)\               : event description
//      (\d+)?               : event duration
//      ((min)|(lightning))$ : event duration unit

        Matcher matcher = Pattern.compile("^(.+)\\s(\\d+)?\\s?((min)|(lightning))$").matcher(line);

        if (!matcher.find()) {
            logger.warn("invalid line -" + line);
            return null;
        }

        String durationUnitStr = matcher.group(INDEX_EVENET_DURATION_UNIT);
        if (durationUnitStr == null || durationUnitStr.isEmpty()) {
            logger.warn("invalid duration unit");
            return null;
        }

        String description = matcher.group(INDEX_EVENET_DESC);
        String durationStr = matcher.group(INDEX_EVENET_DURATION);

        int duration = durationStr == null || durationStr.isEmpty() ? 1 : Integer.parseInt(durationStr);

        if (duration > MAX_EVENT_DURATION){
            logger.warn("the current duration is greater than max event duration:" + MAX_EVENT_DURATION +" MIN");
            return null;
        }

            return new Event(
                    description,
                    duration,
                    durationUnitStr.equalsIgnoreCase(DurationUnit.MINUTES.getName()) ? DurationUnit.MINUTES : DurationUnit.LIGHTENING
            );
    }

}
