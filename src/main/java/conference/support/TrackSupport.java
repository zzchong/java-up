package conference.support;

import conference.Talk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public class TrackSupport {

    public static List<Talk> order(List<Talk> talks){
        List<Talk> orderTalks = new ArrayList<>();

        if(talks ==null || talks.size()==0) {
            return orderTalks;
        }
        talks.sort((o1, o2) -> o2.getTime()-o1.getTime());
        orderTalks.addAll(talks);
        return orderTalks;
    }


    public static int getHour(int hour,int min,int addMin){
        return addMin+min>=60?hour+1:hour;
    }

    public static int getMin(int min,int addMin){
        return addMin+min>=60?addMin+min-60:addMin+min;
    }

    public static String formatMin(int min){
        if(min==0){
            return  "00";
        }
        if(min<10){
            return  "0"+min;
        }
        return min+"";
    }


    public static int amountTime(List<Talk> talks){
        return talks.stream().map(Talk::getTime).reduce((time, count) -> time+=count).orElse(0);
    }

}
