package conf.track.sys;

import conf.track.sys.util.Configure;
import conf.track.sys.util.EventParser;

import java.util.Iterator;
import java.util.List;

import conf.track.sys.util.Logger;

/**
 * Created by hzhang3 on 11/4/16.
 */
public class ConferenceApp {

    public static final int MORNING_SESSION_DURATION = 180; //minutes

    private static Logger logger = Logger.getLogger();

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
            logger.error( e.getMessage() );
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
            Period morningPeriod = new Period(Configure.MORNING_SESSION_START_TIME, MORNING_SESSION_DURATION);
            populateEvents(morningPeriod, events);

            Period lunchPeriod = new Period(Configure.LUNCH_START_TIME, Configure.LUNCH_DURATION);
            lunchPeriod.addEvents(new Event("Lunch", Configure.LUNCH_DURATION, DurationUnit.MINUTES));

            Period afternoonPeriod = new Period(Configure.AFTERNOON_SESSION_START_TIME, Configure.AFTERNOON_SESSION_DURATION);
            populateEvents(afternoonPeriod, events);

            //adding networking period to afternoon period, specially handle the networking event.
            Period netWorkingPeriod = new Period(Configure.NETWORK_EVENT_START_TIME, Configure.NETWORK_EVENT_DURATION);
            netWorkingPeriod.addEvents(new Event("Networking Event", Configure.NETWORK_EVENT_DURATION, DurationUnit.MINUTES));
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
