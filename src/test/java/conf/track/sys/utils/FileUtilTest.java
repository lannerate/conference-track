package conf.track.sys.utils;

import conf.track.sys.uitls.FileUtil;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by apple on 11/6/16.
 */
public class FileUtilTest {

    @Test(expected = FileNotFoundException.class)
    public void testReadFileNotFound() throws Exception{
        String fileNameNotExisting = "";
        FileUtil.readFile(fileNameNotExisting);
    }
}
