package conference.util;

/**
 * Created by pc on 2018/6/13.
 * @author zzc
 */
public enum TractTimeEnum {
    /**
        最大时间
     */
    MAX_TIME(420),

    /**
     * 最小时间
     */
    MIN_TIME(360),

    /**
     * 早上时间
     */
    MORNING_TIME(180),

    /**
     最大下午时间
     */
    MAX_AFTERNOON_TIME(240),

    /**
     * 最小下午时间
     */
    MIN_AFTERNOON_TIME(180),
    ;

    TractTimeEnum(int time) {
        this.time = time;
    }

    int time;

    public int getTime() {
        return time;
    }

}
