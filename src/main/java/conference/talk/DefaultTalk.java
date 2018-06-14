package conference.talk;

import conference.Talk;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pc on 2018/6/13.
 * @author  zzc
 */
public class DefaultTalk implements Talk{

    private String title;

    private int time;

    private LinkedList<Talk> previousTalks = null;

    public DefaultTalk(String title, int time) {
        this.title = title;
        this.time = time;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getTime() {
        return time;
    }

    public Talk addPreviousTalk(Talk previousTalk) {
        if(previousTalks==null) {
            previousTalks = new LinkedList<>();
        }
        previousTalks.add(previousTalk);
        return this;
    }

    @Override
    public List getPreviousTalks() {
        return previousTalks;
    }



}
