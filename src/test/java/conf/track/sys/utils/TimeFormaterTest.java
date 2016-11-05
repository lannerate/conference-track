package conf.track.sys.utils;

import conf.track.sys.uitls.TimeFormater;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apple on 11/5/16.
 */
public class TimeFormaterTest {

    @Test
    public void testFormatTime() {
        assertEquals("09:00 AM", TimeFormater.format(9 * 60));
        assertEquals("09:45 AM", TimeFormater.format(9 * 60 + 45));
        assertEquals("12:00 PM", TimeFormater.format(12 * 60));
        assertEquals("01:45 PM", TimeFormater.format(13 * 60 + 45));
        assertEquals("11:59 PM", TimeFormater.format(24 * 60 - 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfMaxSupportMinutes() {
        assertEquals("00:00 AM", TimeFormater.format(24 * 60));
    }
}
