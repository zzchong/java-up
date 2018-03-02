package design.patten.chain;

/**
 * Created by pc on 2018/3/2.
 * 具体处理1
 * @author zzc
 */
public class HandlerOne extends AbstractHandler{
    @Override
    protected String exec(String request) {
        System.out.println("handleOne 处理了该请求...");
        return null;
    }

    @Override
    protected String getHandlerLevel() {
        return "one";
    }
}
