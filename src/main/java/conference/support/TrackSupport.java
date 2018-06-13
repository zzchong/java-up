package conference.support;

import conference.Talk;

import java.util.Comparator;
import java.util.List;

/**
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public class TrackSupport {

    public static List<Talk> order(List<Talk> talks){
        if(talks ==null || talks.size()==0) {
            return talks;
        }
        talks.sort(Comparator.comparingInt(Talk::getTime));
        return talks;
    }

}
