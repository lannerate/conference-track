package conf.track.sys;

import conf.track.sys.Conference;
import org.junit.Test;

/**
 * Created by apple on 11/3/16.
 */
public class ConferenceTrackTest {
    @Test
    public void testConferenceSchedule() {
        String filePath = getClass().getClassLoader().getResource("InputFile").getFile();

        Conference conference = ConferenceApp.schedule(filePath);
        System.out.println( conference );
    }
}
