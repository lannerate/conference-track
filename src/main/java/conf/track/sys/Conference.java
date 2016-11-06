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

    public void addTrack(Track track){
        tracks.add(track);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        if( tracks!=null && !tracks.isEmpty() ){
            for (int i =0; i< tracks.size(); i++){
                Track track = tracks.get(i);
                builder.append("Track " + (i+1) +":" +"\n");
                builder.append(track);
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
