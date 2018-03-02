package design.patten.chain;

/**
 * Created by pc on 2018/3/2.
 * 责任链模式
 *      定义：使对象都有机会处理请求，从而避免了请求的发送者与接收者之前的耦合，将这些对象连成一条链，并沿着这条链传递该请求，直到有对象处理它为止。
 *      角色：
 *          ·抽象处理者(AbstractHandler)
 *          ·具体抽象处理者(HandlerOne,HandlerTwo)
 *      优点：
 *          ·请求和处理分开
 *      缺点：
 *          ·性能问题：每个请求都是从链头到链尾
 *          ·调试不方便
 *      注意：控制节点
 * @author zzc
 */
public class ChainPattern {

    public static void main(String [] args){
        HandleRequest.exec("one");
        HandleRequest.exec("two");
        HandleRequest.exec("three");
    }
}
