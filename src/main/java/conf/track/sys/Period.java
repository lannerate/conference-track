package conf.track.sys;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhang3 on 11/3/16.
 */
public class Period {
    private List<Event> events;
    private int startTime;
    private int totalRemainingTime;

    public Period(int startTime, int sessionDuration) {
        //firstly init period, that is morning session duration or afternoon session duration
        this.totalRemainingTime = sessionDuration;
        this.startTime = startTime;
        events = new ArrayList<>();
    }

    public void addEvents(Event event) {
        if (null != event) {
            if (totalRemainingTime < event.getDurationMinutes()) throw
                    new IllegalArgumentException("Not enough space time to take the event:" + event.getDescription());

            events.add(event);
            //consume this event, total remaining time need to minus this event duration time
            totalRemainingTime -= event.getDurationMinutes();
        }
    }

    public boolean hasEnoughSpaceTime(Event event) {
        return totalRemainingTime <= event.getDurationMinutes();
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Event event : events) {
            /**
             * format as below
             *
             * 11:15AM Ruby Errors from Mismatched Gem Versions 45min
             * 12:00PM Lunch
             * 01:00PM Ruby on Rails: Why We Should Move On 60min
             */
            String outputForEvent = formatTime( startTime ) + " " + event + "\n";
        }

        return builder.toString();
    }

    public static String format(int minutes){

        int hoursDisplay = minutes / 60; //since both are ints, you get an int
        int minutesDisplay = minutes % 60;

        String displayValue;
        if (hoursDisplay < 12) {
            displayValue = " AM";
        } else {
            displayValue = " PM";
        }

        return String.format("%d:%02d%s", hoursDisplay,minutesDisplay,displayValue);

    }



    public static String formatTime(int minutes) {
        int maxSupportedTimeInMinutes = (12 * 60 + 12 * 60) - 1;
        if (minutes > maxSupportedTimeInMinutes) {
            throw new IllegalArgumentException("Time in minutes cannot be greater than"
                    + maxSupportedTimeInMinutes + " minutes.");
        }

        int hours = minutes / 60;
        String hoursToDisplay = Integer.toString(hours);
        if (hours > 12) {
            hoursToDisplay = Integer.toString(hours - 12);
        }
        if (hoursToDisplay.length() == 1) {
            hoursToDisplay = "0" + hoursToDisplay;
        }

        minutes = minutes - (hours * 60);
        String minutesToDisplay = null;
        if (minutes == 0) {
            minutesToDisplay = "00";
        } else if (minutes < 10) {
            minutesToDisplay = "0" + minutes;
        } else {
            minutesToDisplay = "" + minutes;
        }

        String displayValue;
        if (hours < 12) {
            displayValue = " AM";
            if (hoursToDisplay.equals("00")) {
                hoursToDisplay = "12";
            }
        } else {
            displayValue = " PM";
        }
        displayValue = hoursToDisplay + ":" + minutesToDisplay + displayValue;

        return displayValue;
    }

}
