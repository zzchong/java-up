package conference;

import conference.support.DefaultTrackManagement;

/**
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public class TrackManagementTest {

    public static void main(String[] args){
        TrackManagement trackManagement = new DefaultTrackManagement(null);
        System.out.println(trackManagement.startPlanProgramming());
    }
}
