package design.patten.chain;

/**
 * Created by pc on 2018/3/2.
 * 抽象处理者
 * @author zzc
 */
public abstract class AbstractHandler {

    /**
     * 下一个处理者
     */
    private AbstractHandler nextHandler;

    /**
     * 处理级别
     *     可以不是String类型的
     * @return string
     */
    protected abstract String getHandlerLevel();

    /**
     * 处理业务逻辑
     * @param request 待处理请求
     * @return 返回string (可以是其他类型)
     */
    protected abstract String exec(String request);

    /**
     * 每个处理者都必须对请求做出处理
     * @param request （此处等同于处理级别level，可以是其他类型）
     * @return 处理结果
     */
    public final String handlerRequest(String request){
        String response = null;

        /**
         * 判断是否属于自己的处理级别
         */
        if(request.equals(getHandlerLevel())){
            response = this.exec(request);
        }else {

            /**
             * 不属于自己的处理级别，则传给下一个处理者
             */
            if(this.getNextHandler()!=null){
                response = this.getNextHandler().handlerRequest(request);
            }else {
                System.out.println("没有处理者处理此请求......");
            }
        }
        return  response;
    }

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public AbstractHandler getNextHandler() {
        return nextHandler;
    }
}
