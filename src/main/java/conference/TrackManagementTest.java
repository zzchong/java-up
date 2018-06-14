package conference;

import conference.talk.DefaultTalk;
import conference.trackManagement.DefaultTrackManagement;
import conference.rule.RandomRule;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public class TrackManagementTest {

    public static void main(String[] args){
        List<Talk> talks = Arrays.asList(
                new DefaultTalk("Writing Fast Tests Against Enterprise Rails",60),
                new DefaultTalk("Overdoing it in Python",45),
                new DefaultTalk("Lua for the Masses",30),
                new DefaultTalk("Ruby Errors from Mismatched Gem Versions",45),
                new DefaultTalk("Common Ruby Errors",45),
                new DefaultTalk("WRails for Python Developers",5),
                new DefaultTalk("Communicating Over Distance",60),
                new DefaultTalk("Accounting-Driven Development",45),
                new DefaultTalk("Woah ",30),
                new DefaultTalk("Sit Down and Write",30),
                new DefaultTalk("Pair Programming vs Noise",45),
                new DefaultTalk("Rails Magic",60),
                new DefaultTalk("Ruby on Rails: Why We Should Move On",60),
                new DefaultTalk("Clojure Ate Scala (on my project)",45),
                new DefaultTalk("Programming in the Boondocks of Seattle ",30),
                new DefaultTalk("Ruby vs. Clojure for Back-End Development",30),
                new DefaultTalk("Ruby on Rails Legacy App Maintenance",60),
                new DefaultTalk("A World Without HackerNews",30),
                new DefaultTalk("User Interface CSS in Rails Apps",30)

        );
        /*
            使用默认策略
         */
//        TrackManagement trackManagement = new DefaultTrackManagement(talks);

        /*
            使用随机策略
         */
        TrackManagement trackManagement = new DefaultTrackManagement(talks,new RandomRule());
        System.out.println(trackManagement.startPlanProgramming());
    }
}
