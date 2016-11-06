package conf.track.sys.util;

import conf.track.sys.DurationUnit;
import conf.track.sys.Event;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by apple on 11/5/16.
 */
public class EventParserTest {

    @Test
    public void testParseEventFromLine() {
        String line = "Common Ruby Errors 45min";
        Event event = EventParser.parseLine(line);
        assertNotNull(event);
        assertEquals("Common Ruby Errors", event.getDescription());
        assertEquals(45, event.getDuration());
        assertEquals(DurationUnit.MINUTES, event.getDurationUnit());

        line = "Rails for Python Developers lightning";
        event = EventParser.parseLine(line);
        assertNotNull(event);
        assertEquals("Rails for Python Developers", event.getDescription());
        assertEquals(1, event.getDuration());
        assertEquals(DurationUnit.LIGHTENING, event.getDurationUnit());

        line ="Rails for Python Developers";
        event = EventParser.parseLine(line);
        assertEquals(null,event);

        line ="";
        event = EventParser.parseLine(line);
        assertEquals(null, event);
    }

    @Test
    public void testParseEventsFromFile(){
        String filePath = getClass().getClassLoader().getResource("InputFile").getFile();
        try {
            List<Event> events = EventParser.parse(filePath);
            assertNotNull(events);
            assertTrue( !events.isEmpty() );
        } catch (Exception e) {
//            fail("parse events failed from the file:" + filePath );
        }
    }

}
