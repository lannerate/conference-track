package conf.track.sys;

import conf.track.sys.util.Logger;
import conf.track.sys.util.TimeFormater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhang3 on 11/3/16.
 */
public class Period {
    public static final String NEW_LINE = System.lineSeparator();
    private static Logger logger = Logger.getLogger();

    private List<Event> events;
    private int startTime;
    private int totalRemainingTime;
    private Period otherPeriod;

    public Period(int startTime, int sessionDuration) {
        //firstly init period, for morning session period, lunch period, afternoon session period, and networking period.
        this.totalRemainingTime = sessionDuration;
        this.startTime = startTime;
        events = new ArrayList<>();
    }

    public void addEvents(Event event) {
        if (null != event) {
            if (totalRemainingTime < event.getDurationMinutes()) {
                String illegalArgMsg = "Not enough space time to take the event:" + event.getDescription();
                logger.error(illegalArgMsg);
                throw new IllegalArgumentException(illegalArgMsg);
            }

            events.add(event);
            //consume this event, total remaining time need to minus this event duration time
            totalRemainingTime -= event.getDurationMinutes();
        }
    }

    public boolean hasEnoughSpaceTime(Event event) {
        return totalRemainingTime >= event.getDurationMinutes();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addOtherPeriod(Period otherPeriod) {
        this.otherPeriod = otherPeriod;
    }

    @Override
    public String toString() {
        StringBuilder collectedResult = new StringBuilder();
        int nextStartTime = addEventSchedule(events, startTime, collectedResult);

        //if have networking period for this problem, need to re-calculate next start time.
        if (otherPeriod != null) {
            int otherStartTime = otherPeriod.startTime;
            if (nextStartTime > otherStartTime) {
                otherStartTime = nextStartTime;
            }
            nextStartTime = addEventSchedule(otherPeriod.events, otherStartTime, collectedResult);
        }
        return collectedResult.toString();
    }

    private int addEventSchedule(List<Event> events, int startTime, StringBuilder collectedResult) {
        int nextStartTime = startTime;
        for (Event event : events) {
            //format the output for each event.
            String outputForEvent = TimeFormater.format(nextStartTime) + " " + event + NEW_LINE;
            collectedResult.append(outputForEvent);
            //add the current event duration to next start time
            nextStartTime += event.getDurationMinutes();
        }

        return nextStartTime;
    }
}
