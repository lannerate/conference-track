package conf.track.sys;

/**
 * Created by hzhang3 on 11/3/16.
 */
public class Event {
    private String description;
    private DurationUnit durationUnit;
    private int duration;

    public Event(String description, int duration, DurationUnit durationUnit){
        this.description = description;
        this.duration = duration;
        this.durationUnit = durationUnit;
    }

    public int getDurationMinutes(){
        return durationUnit.toMinutes( duration );
    }

    @Override
    public String toString() {
        return description + " " + "-" + " " + duration + " " + durationUnit;
    }

    public String getDescription(){
        return description;
    }

    public int getDuration(){
        return duration;
    }

    public DurationUnit getDurationUnit(){
        return durationUnit;
    }
}

