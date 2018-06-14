package conference.rule;

import conference.Rule;
import conference.Talk;
import conference.Track;
import conference.support.TrackSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在可选范围内随机安排talk
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public class RandomRule implements Rule {

    private List<Track> tracks = new ArrayList<>();

    private Track currentTrack;

    private boolean isTrackOver = true;

    @Override
    public List<Track> createTrack(List<Talk> talks) {
        List<Talk> orderTalk = TrackSupport.order(talks);
        doPlaning(orderTalk);
        return tracks;
    }
    private Track getCurrentTrack(){
        if(isTrackOver) {
            currentTrack = new Track();
            isTrackOver = false;
        }
        return currentTrack;
    }

    private void doPlaning(List<Talk> talks){
        int len;
        while ((len=talks.size())>0){
            Track track = getCurrentTrack();
            int restMorningTime = track.getRestMorningTime(),
                restAfternoonTime = track.getRestAfternoonMaxTime();
            int morningCriticalIndex = criticalIndex(talks,restMorningTime);
            if(morningCriticalIndex!=-1){
                int num = morningCriticalIndex+(int)(Math.random()*(len-morningCriticalIndex));
                Talk talk = talks.get(num);
                track.addMorningTalk(talk);
                track.setRestMorningTime(restMorningTime-talk.getTime());
                talks.remove(num);
            }else {
                int afternoonCriticalIndex = criticalIndex(talks,restAfternoonTime);
                if(afternoonCriticalIndex!=-1){
                    int num = afternoonCriticalIndex+(int)(Math.random()*(len-afternoonCriticalIndex));
                    Talk talk = talks.get(num);
                    track.addAfternoonTalk(talk);
                    track.setRestAfternoonMaxTime(restAfternoonTime-talk.getTime());
                    talks.remove(num);
                }else {
                    tracks.add(currentTrack);
                    isTrackOver = true;
                    currentTrack = getCurrentTrack();
                }
            }
            if(talks.size()==0){
                tracks.add(currentTrack);
            }
        }
    }

    /**
     * 临界值索引
     * @return 临界值索引
     */
    private int  criticalIndex(List<Talk> talks,int restTime){
        for(int i=0,len=talks.size();i<len;i++){
            int time = talks.get(i).getTime();
            if(time<=restTime){
                return i;
            }
        }
        return -1;
    }

}
