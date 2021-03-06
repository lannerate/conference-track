package conf.track.sys.util;

/**
 * Created by apple on 11/5/16.
 */
public class TimeFormater {

    private static Logger logger = Logger.getLogger();

    static final int maxSupportMinutes = 24 * 60 - 1; // only one day

    /**
     * formatted sample as below
     *
     * 11:15AM Ruby Errors from Mismatched Gem Versions 45min
     * 12:00PM Lunch
     * 01:00PM Ruby on Rails: Why We Should Move On 60min
     *
     * @param minutes
     * @return
     */
    public static String format(int minutes) {
        if (minutes > maxSupportMinutes){
            String errorMsg = "greater than max support minutes:" + maxSupportMinutes + "min";
            logger.error(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }

        int hoursDisplay = minutes / 60;
        int minutesDisplay = minutes % 60;

        String suffix = (hoursDisplay >= 12) ? "PM" : "AM";
        hoursDisplay = (hoursDisplay > 12) ? hoursDisplay - 12 : hoursDisplay;

        return String.format("%02d:%02d%s", hoursDisplay, minutesDisplay, suffix);

    }
}
