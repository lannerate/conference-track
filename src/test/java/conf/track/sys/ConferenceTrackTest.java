package conf.track.sys;

import conf.track.sys.Conference;
import org.junit.Test;

/**
 * Created by apple on 11/3/16.
 */
public class ConferenceTrackTest {
    @Test
    public void testConferenceSchedule() {
//        Conference.schedule();
        String filePath = getClass().getClassLoader().getResource("InputFile").getFile();

        String content = ConferenceApp.schedule(filePath).getContent();
        System.out.println(content);
    }
}
