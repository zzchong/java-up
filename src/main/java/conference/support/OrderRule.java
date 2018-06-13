package conference.support;

import conference.Rule;
import conference.Talk;
import conference.Track;

import java.util.LinkedList;
import java.util.List;

/**
 * 默认排序规则
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public class OrderRule implements Rule {

    LinkedList<Track> tracks = new LinkedList<>();

    int morning = MORING_TIME;

    int afternoon = MAX_TIME;

    @Override
    public List<Track> createTrack(List<Talk> talks) {

        return  null;
    }
}
