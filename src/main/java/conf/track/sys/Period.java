package conf.track.sys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/3/16.
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

    public void addEvents(Event event){
        if( null != event ) {
            if(totalRemainingTime < event.getDurationMinutes()) throw
                new IllegalArgumentException("Not enough space time to take the event:" + event.getDescription());

            events.add(event);
            //consume this event, total remaining time need to minus this event duration time
            totalRemainingTime -= event.getDurationMinutes();
        }
    }



}
