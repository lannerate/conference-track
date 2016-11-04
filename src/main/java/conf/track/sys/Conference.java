package conf.track.sys;

import conf.track.sys.uitls.EventParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by apple on 11/3/16.
 */
public class Conference {
    public static final int MORNING_SESSION_DURATION = 180; //minutes
    public static final int LUNCH_DURATION = 60; //minutes
    public static final int AFTERNOON_SESSION_DURATION = 240; //minutes

    public static final int MORNING_SESSION_START_TIME = 9 * 60;
    public static final int LUNCH_START_TIME = MORNING_SESSION_START_TIME + MORNING_SESSION_DURATION;
    public static final int AFTERNOON_SESSION_START_TIME = LUNCH_START_TIME + LUNCH_DURATION;

    public List<Track> tracks;

    public Conference() {
        tracks = new ArrayList<>();
    }


    public static void schedule() {
//        1. parse events from input files
          List<Event> events = EventParser.parse("filePath");


//        2. process events
//        > 2.1 combine Events to Period
//        > 2.2 combine Periods to Track
//        > 2.3 combine Tracks to Conference
//
//         config conference periods


//        3.print tracks and events

    }

    public static List<Period> initPeroids(List<Event> events){
        //         config conference schedule period
        List<Period> periods = new ArrayList<>();
        if( !events.isEmpty() ){
            Period morningPeriod = new Period(MORNING_SESSION_START_TIME,MORNING_SESSION_DURATION);
            periods.add(morningPeriod);

            Period lunchPeriod = new Period(LUNCH_START_TIME, LUNCH_DURATION);
            periods.add(lunchPeriod);

            Period afternoonPeriod = new Period(AFTERNOON_SESSION_START_TIME, AFTERNOON_SESSION_DURATION);
            periods.add(afternoonPeriod);
        }
        return periods;
    }
}
