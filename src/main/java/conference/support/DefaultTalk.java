package conference.support;

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

    private Talk addPreviousTalk(Talk previousTalk) {
        if(previousTalks==null) {
            previousTalks = new LinkedList<>();
        }
        return this;
    }

    @Override
    public List getPreviousTalks() {
        return previousTalks;
    }

    /**
     * 是否有前继
     * @return true 有 false 没有
     */
    private boolean hasPrevious(){
        return previousTalks!=null && previousTalks.size()>0;
    }

}
