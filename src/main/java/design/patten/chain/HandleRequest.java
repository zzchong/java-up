package design.patten.chain;

/**
 * Created by pc on 2018/3/2.
 * 封装类
 * @author zzc
 */
public class HandleRequest {
    private static  AbstractHandler handlerOne = new HandlerOne();
    private static  AbstractHandler handlerTwo = new HandlerTwo();
    static {
        handlerOne.setNextHandler(handlerTwo);
    }
    public static String exec(String request){
        return  handlerOne.handlerRequest(request);
    }
}
