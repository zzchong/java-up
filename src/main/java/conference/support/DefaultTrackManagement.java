package conference.support;

import conference.Rule;
import conference.Talk;
import conference.Track;
import conference.TrackManagement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public class DefaultTrackManagement implements TrackManagement {


    private Rule rule;

    private List<Track> tracks = new LinkedList<>();

    private LinkedList<Talk> talks = null;

    public DefaultTrackManagement(LinkedList<Talk> talks) {
        this(talks,new OrderRule());
    }

    public DefaultTrackManagement(LinkedList<Talk> talks,Rule rule) {
        this.talks = talks;
        this.rule = rule;
    }

    @Override
    public String startPlanProgramming() {
        if(talks==null || talks.size()==0){
            return "plan fail,NO TALKING!";
        }
        tracks = rule.createTrack(talks);
        return print();
    }

    private String print(){
        StringBuilder sb = new StringBuilder();
        if(tracks==null || tracks.size()>0){
            return  "no tracks";
        }
        for (int i=0,len=tracks.size();i<len;i++){
            int trackNo = i+1;
            Track track = tracks.get(i);
            sb.append("Track").append(trackNo).append(":\r\n").append(track.print());
        }
        return sb.toString();
    }

}
