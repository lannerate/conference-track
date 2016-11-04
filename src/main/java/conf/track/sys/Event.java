package conf.track.sys;

/**
 * Created by apple on 11/3/16.
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
        return durationUnit.toMintues( duration );
    }

    @Override
    public String toString() {
        return description + "-" + duration + " " + durationUnit;
    }

    public String getDescription(){
        return description;
    }
}

enum DurationUnit {
    MINUTES(1,"min"),
    LIGHTENING(5, "lightning");

    private int base;
    private String name;

    DurationUnit(int base, String name){
        this.base= base;
        this.name = name;
    }

    public int toMintues(int duration){
        return duration * base;
    }

    @Override
    public String toString() {
        return name;
    }
}