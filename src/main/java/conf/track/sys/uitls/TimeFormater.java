package conf.track.sys.uitls;

/**
 * Created by apple on 11/5/16.
 */
public class TimeFormater {
    static final int maxSupportMinutes = 24 * 60 - 1; // only one day

    public static String format(int minutes) {
        if (minutes > maxSupportMinutes)
            throw new IllegalArgumentException("greater than max support minutes:" + maxSupportMinutes + "min");

        int hoursDisplay = minutes / 60;
        int minutesDisplay = minutes % 60;

        String suffix = (hoursDisplay >= 12) ? " PM" : " AM";
        hoursDisplay = (hoursDisplay > 12) ? hoursDisplay - 12 : hoursDisplay;

        return String.format("%02d:%02d%s", hoursDisplay, minutesDisplay, suffix);

    }
}
