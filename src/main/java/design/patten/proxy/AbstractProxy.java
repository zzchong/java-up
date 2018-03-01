package design.patten.proxy;

/**
 * Created by pc on 2018/3/1.
 * 代理类抽象接口
 * @author zzc
 */
public abstract class AbstractProxy  implements ISubject,IProxy {

    private ISubject subject;

    /**
     * 预处理
     */
    abstract  void before();

    /**
     * 善后处理
     */
    abstract  void after();

    /**
     * 公共预处理
     */
    private void baseBefore(){
        System.out.println("公共的预处理...");
    }

    /**
     * 公共善后处理
     */
    private void baseAfter(){
        System.out.println("公共的善后处理...");
    }

    @Override
    public void request() {
        this.baseBefore();
        this.before();
        this.subject.request();
        this.after();
        this.baseAfter();
    }

    void setSubject(ISubject subject){
        this.subject = subject;
    }

    public ISubject getSubject() {
        return subject;
    }
}
