package conf.track.sys;

import conf.track.sys.util.FileUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hzhang3 on 11/3/16.
 */
public class ConferenceTrackTest {
    @Test
    public void testConferenceSchedule() throws Exception {
        List<String> linesActual = getActualLinesAfterScheduled("InputFile");
        List<String> linesExcepted = getExceptedLines("OutputFile");

        //compare the size of excepted output file with the size of actual lines after scheduled.
        assertNotNull(linesActual);
        assertNotNull(linesExcepted);
        assertTrue(linesActual.size() == linesExcepted.size());

        //Is each line equals
        for (int i = 0; i < linesActual.size(); i++) {
            String lineActual = linesActual.get(i);
            String lineExcepted = linesExcepted.get(i);
            assertEquals(lineExcepted, lineActual);
        }

    }

    private List<String> getActualLinesAfterScheduled(String inputFile) {
        Conference conference = ConferenceApp.schedule(findResourceFile(inputFile));
        return Arrays.asList(conference.toString().split("\n"));
    }

    private List<String> getExceptedLines(String exceptedFile) throws FileNotFoundException, IOException {
        List<String> linesExcepted = new ArrayList<>();

        BufferedReader outputReader = FileUtil.readFile(findResourceFile(exceptedFile));
        for (String lineExcepted; (lineExcepted = outputReader.readLine()) != null; ) {
            linesExcepted.add(lineExcepted);
        }
        return linesExcepted;
    }

    private String findResourceFile(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getFile();
    }
}
