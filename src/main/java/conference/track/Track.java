package conference.track;
import conference.Talk;
import conference.support.TrackSupport;
import conference.util.TractTimeEnum;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pc on 2018/6/12.
 * @author zzc
 */
public class Track {

    private int restMorningTime = TractTimeEnum.MORNING_TIME.getTime();

    private int restAfternoonMaxTime = TractTimeEnum.MAX_AFTERNOON_TIME.getTime();

    private List<Talk> morningTalks = new LinkedList<>();

    private List<Talk> afternoonTalks = new LinkedList<>();

    public void  addMorningTalk(Talk talk){
        morningTalks.add(talk);
    }

    public void  addAfternoonTalk(Talk talk){
        afternoonTalks.add(talk);
    }

    public int getRestMorningTime() {
        return restMorningTime;
    }

    public void setRestMorningTime(int restMorningTime) {
        this.restMorningTime = restMorningTime;
    }

    public int getRestAfternoonMaxTime() {
        return restAfternoonMaxTime;
    }

    public void setRestAfternoonMaxTime(int restAfternoonMaxTime) {
        this.restAfternoonMaxTime = restAfternoonMaxTime;
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        if(morningTalks==null || morningTalks.size()==0){
            return  "no talks";
        }
        int morningHour=9,morningMin=0,afternoonHour = 1,afternoonMin=0;
        int morningLen=morningTalks.size(),afternoonLen = afternoonTalks.size();
        for (int i=0,len=morningLen+afternoonLen;i<len;i++){
            Talk talk;
            if(i<morningLen){
                sb.append(morningHour).append(":").append(TrackSupport.formatMin(morningMin)).append(" AM  ");
                talk  = morningTalks.get(i);
                morningHour = TrackSupport.getHour(morningHour,morningMin,talk.getTime());
                morningMin = TrackSupport.getMin(morningMin,talk.getTime());

            }else {
                if(i==morningLen){
                    sb.append("12:00PM  for lunch").append("\r\n");
                }
                sb.append(afternoonHour).append(":").append(TrackSupport.formatMin(afternoonMin)).append(" PM  ");
                talk = afternoonTalks.get(i-morningLen);
                afternoonHour = TrackSupport.getHour(afternoonHour,afternoonMin,talk.getTime());
                afternoonMin = TrackSupport.getMin(afternoonMin,talk.getTime());
            }
            sb.append(talk.print()).append("\r\n");
        }
        sb.append("05:00PM Networking Event").append("\r\n");
        return sb.toString();
    }


}
