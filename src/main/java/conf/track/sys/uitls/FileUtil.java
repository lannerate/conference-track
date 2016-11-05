package conf.track.sys.uitls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by hzhang3 on 11/4/2016.
 */
public class FileUtil {

    public static BufferedReader readFile(String filePath) throws FileNotFoundException{
        if( filePath == null || filePath.isEmpty() ) throw new FileNotFoundException("file path is empty, can not find the file");

        File file = new File(filePath);
        return new BufferedReader( new FileReader(file ));
    }
}
