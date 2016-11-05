package conf.track.sys;

import conf.track.sys.uitls.EventParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhang3 on 11/4/16.
 */
public class ConferenceApp {

    public static final int MORNING_SESSION_DURATION = 180; //minutes
    public static final int LUNCH_DURATION = 60; //minutes
    public static final int AFTERNOON_SESSION_DURATION = 240; //minutes

    public static final int MORNING_SESSION_START_TIME = 9 * 60;
    public static final int LUNCH_START_TIME = MORNING_SESSION_START_TIME + MORNING_SESSION_DURATION;
    public static final int AFTERNOON_SESSION_START_TIME = LUNCH_START_TIME + LUNCH_DURATION;


    public static void main(String args[]) {
//        1. read input files, parse files
        String inputFilePath = args[0];
        if (inputFilePath == null || inputFilePath.isEmpty()) {
            System.exit(1);
//            throw new IllegalArgumentException("Please enter the file path.");
        }
//        2. run conference schedule()
        Conference conference = schedule(inputFilePath);
//        3. print the result
        System.out.print(conference.getContent());
    }


    public static Conference schedule(String inputFilePath) {
//        1. parse events from input files
        List<Event> events = null;
        try {
            events = EventParser.parse(inputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (events==null || events.isEmpty()) { return null; }

//        2. process events
//        > 2.1 combine Events to Period
//        > 2.2 combine Periods to Track
//        > 2.3 combine Tracks to Conference
//
//         config conference periods
        List<Period> periods = initPeriods(events);

        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track(periods));

        Conference conference = new Conference(tracks);

        return conference;
    }

    public static List<Period> initPeriods(List<Event> events) {
        //         config conference schedule period
        List<Period> periods = new ArrayList<>();
        if ( events!=null && !events.isEmpty()) {
            Period morningPeriod = new Period(MORNING_SESSION_START_TIME, MORNING_SESSION_DURATION);
            periods.add(morningPeriod);

            Period lunchPeriod = new Period(LUNCH_START_TIME, LUNCH_DURATION);
            periods.add(lunchPeriod);

            Period afternoonPeriod = new Period(AFTERNOON_SESSION_START_TIME, AFTERNOON_SESSION_DURATION);
            periods.add(afternoonPeriod);
        }
        return periods;
    }
}
