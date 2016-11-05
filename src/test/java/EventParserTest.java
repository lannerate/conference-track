import conf.track.sys.DurationUnit;
import conf.track.sys.Event;
import conf.track.sys.uitls.EventParser;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apple on 11/5/16.
 */
public class EventParserTest {

    @Test
    public void testParseLine() {
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
}
