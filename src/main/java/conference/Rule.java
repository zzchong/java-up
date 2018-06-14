package conference;

import java.util.List;

/**
 * 规划策略
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public interface Rule {

    /**
     * 计划安排talk有很多策略，(默认为OrderRule，根据输入的顺序依次来安排)
     * @param talks 所有的talk
     * @return 根据当前策略计划好的track
     */
    List<Track> createTrack(List<Talk> talks);


}
