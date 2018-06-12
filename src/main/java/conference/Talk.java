package conference;

/**
 * Created by pc on 2018/6/12.
 * Talk 分为多种类型（比如MorningTalk ，AfternoonTalk等）
 * @author zzc
 */
public interface Talk {

    /**
     * talk时长
     * @return 时长
     */
    int getTime();

    /**
     * talk之间有可能有顺序，所以维护一个Talk链表
     * @return 下一个Talk
     */
    Talk getNext();
}
