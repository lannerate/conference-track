package conf.track.sys.util;

import conf.track.sys.ConferenceApp;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by apple on 11/11/16.
 */
public class Configure {
    public static final int LUNCH_DURATION = 60; //minutes
    public static final int AFTERNOON_SESSION_DURATION = 240; //minutes
    public static final int NETWORK_EVENT_DURATION = 60; //minutes
    public static final int MORNING_SESSION_START_TIME = 9 * 60;
    public static final int LUNCH_START_TIME = MORNING_SESSION_START_TIME + ConferenceApp.MORNING_SESSION_DURATION;
    public static final int AFTERNOON_SESSION_START_TIME = LUNCH_START_TIME + LUNCH_DURATION;
    public static final int NETWORK_EVENT_START_TIME = (12 * 60) + (5 * 60); // 5 PM

    public static final int MAX_EVENT_DURATION = Collections.max(Arrays.asList(LUNCH_DURATION,AFTERNOON_SESSION_DURATION,NETWORK_EVENT_DURATION));
}
