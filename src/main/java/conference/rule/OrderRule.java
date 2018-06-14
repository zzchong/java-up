package conference.rule;

import conference.Rule;
import conference.Talk;
import conference.track.Track;
import conference.support.TrackSupport;
import conference.util.TractTimeEnum;

import java.util.Arrays;
import java.util.List;

/**
 * 默认排序规则,按照输入顺序来完成talk plan
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public class OrderRule implements Rule {

    private Track[] tracks;

    @Override
    public List<Track> createTrack(List<Talk> talks) {
        initTrack(TrackSupport.amountTime(talks));
        doPlaning(talks);
        return Arrays.asList(tracks);
    }

    private void doPlaning(List<Talk> talks){
        for (Talk talk : talks) {
            int time = talk.getTime(), trackIndex = 0;
            while (trackIndex >= 0) {
                if (trackIndex >= tracks.length) {
                    resizeTracks();
                    tracks[trackIndex] = new Track();
                }
                Track track = tracks[trackIndex];
                if (track.getRestMorningTime() >= time) {
                    track.addMorningTalk(talk);
                    track.setRestMorningTime(track.getRestMorningTime() - time);
                    trackIndex = -1;
                } else if (track.getRestAfternoonMaxTime() >= time) {
                    track.addAfternoonTalk(talk);
                    track.setRestAfternoonMaxTime(track.getRestAfternoonMaxTime() - time);
                    trackIndex = -1;
                } else {
                    trackIndex++;
                }
            }
        }
    }

   private void  resizeTracks(){
        Track[] oldTracks = tracks;
        Track[] newTracks =  new Track[tracks.length+1];
        for(int i=0;i<oldTracks.length;i++){
            newTracks[i] = oldTracks[i];
        }
        tracks = newTracks;
    }

    private void initTrack(int amount){
        if(amount!=0) {
            int num = amount% TractTimeEnum.MAX_TIME.getTime()== 0 ? amount/ TractTimeEnum.MAX_TIME.getTime() : amount/ TractTimeEnum.MAX_TIME.getTime()+ 1;
            tracks = new Track[num];
            for(int i=0;i<num;i++){
                tracks[i]=new Track();
            }
        }
    }



}
