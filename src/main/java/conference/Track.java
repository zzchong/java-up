package conference;
import java.util.LinkedList;

/**
 * Created by pc on 2018/6/12.
 * @author zzc
 */
public class Track {


    private LinkedList<Talk> talks = new LinkedList<>();

    public String print(){
        StringBuilder sb = new StringBuilder();
        if(talks==null || talks.size()>0){
            return  "no talks";
        }
        for (int i=0,len=talks.size();i<len;i++){
            Talk talk = talks.get(i);
            sb.append(talk.print()).append("\r\n");
        }
        return sb.toString();
    }
}
