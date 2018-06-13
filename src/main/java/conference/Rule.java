package conference;

import java.util.List;

/**
 * 规划策略
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public interface Rule {

    /**
     * 计划策略
     * @param talks talks
     */
    List<Track> createTrack(List<Talk> talks);
}
