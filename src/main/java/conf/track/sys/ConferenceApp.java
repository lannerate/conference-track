package conf.track.sys;

import conf.track.sys.util.EventParser;

import java.util.Iterator;
import java.util.List;

/**
 * Created by hzhang3 on 11/4/16.
 */
public class ConferenceApp {

    public static final int MORNING_SESSION_DURATION = 180; //minutes
    public static final int LUNCH_DURATION = 60; //minutes
    public static final int AFTERNOON_SESSION_DURATION = 240; //minutes
    public static final int NETWORK_EVENT_DURATION = 60; //minutes

    public static final int MORNING_SESSION_START_TIME = 9 * 60;
    public static final int LUNCH_START_TIME = MORNING_SESSION_START_TIME + MORNING_SESSION_DURATION;
    public static final int AFTERNOON_SESSION_START_TIME = LUNCH_START_TIME + LUNCH_DURATION;
    public static final int NETWORK_EVENT_START_TIME = (12 * 60) + (5 * 60); // 5 PM

    public static void main(String args[]) {
//        1. read input files, parse files
        String inputFilePath = args[0];
        if (inputFilePath == null || inputFilePath.isEmpty()) {
            System.exit(1);
        }
//        2. run conference schedule()
        Conference conference = schedule(inputFilePath);
//        3. print the context of conference
        System.out.print(conference);
    }


    public static Conference schedule(String inputFilePath) {
//        1. parse events from input files
        List<Event> events = null;
        try {
            events = EventParser.parse(inputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (events == null || events.isEmpty()) {
            return null;
        }
//        2. process events
//        > configure for Morning/Lunch/Afternoon/networking period.
//        > populate Events to Period, consume events when the current period has enough space time.
//        > populate Tracks to Conference
        Conference conference = new Conference();
        processEvents(events, conference);

        return conference;
    }

    public static void processEvents(List<Event> events, Conference conference) {

        while (events != null && !events.isEmpty()) {
            //config periods
            Period morningPeriod = new Period(MORNING_SESSION_START_TIME, MORNING_SESSION_DURATION);
            populateEvents(morningPeriod, events);

            Period lunchPeriod = new Period(LUNCH_START_TIME, LUNCH_DURATION);
            lunchPeriod.addEvents(new Event("Lunch", LUNCH_DURATION, DurationUnit.MINUTES));

            Period afternoonPeriod = new Period(AFTERNOON_SESSION_START_TIME, AFTERNOON_SESSION_DURATION);
            populateEvents(afternoonPeriod, events);

            //adding networking period to afternoon period, specially handle the networking event.
            Period netWorkingPeriod = new Period(NETWORK_EVENT_START_TIME, NETWORK_EVENT_DURATION);
            netWorkingPeriod.addEvents(new Event("Networking Event", NETWORK_EVENT_DURATION, DurationUnit.MINUTES));
            afternoonPeriod.addOtherPeriod(netWorkingPeriod);

            Track track = new Track();
            track.addPeriod(morningPeriod);
            track.addPeriod(lunchPeriod);
            track.addPeriod(afternoonPeriod);

            conference.addTrack(track);
        }
    }

    private static void populateEvents(Period morningPeriod, List<Event> events) {
        for (Iterator<Event> it = events.iterator(); it.hasNext(); ) {
            Event event = it.next();
            if (morningPeriod.hasEnoughSpaceTime(event)) {
                morningPeriod.addEvents(event);
                it.remove();
            }
        }
    }


}
