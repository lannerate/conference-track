package conf.track.sys;

import conf.track.sys.uitls.EventParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by hzhang3 on 11/3/16.
 */
public class Conference {

    public List<Track> tracks;

    public Conference() {
        tracks = new ArrayList<>();
    }
    public Conference(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getContent(){
        StringBuilder builder = new StringBuilder();

        if( !tracks.isEmpty() ){
            for (Track track: tracks){
                builder.append(track.toString());
                for (Period period: track.periods){
                    for (Event event: period.getEvents()){
                        builder.append( event.toString() );
                    }
                }
            }
        }
        return builder.toString();
    }

}
