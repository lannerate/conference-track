package conf.track.sys;

public enum DurationUnit {
    MINUTES(1,"min"),
    LIGHTENING(5, "lightning");

    private int base;
    private String name;

    DurationUnit(int base, String name){
        this.base= base;
        this.name = name;
    }

    public int toMinutes(int duration){
        return duration * base;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
