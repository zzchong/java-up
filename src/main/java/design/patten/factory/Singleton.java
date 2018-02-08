package design.patten.factory;

/**
 * Created by pc on 2018/2/8.
 * 单例类
 * @author zz_chong
 */
public class Singleton {

    private String memo;

    private Singleton(){}

    public void print(){
        System.out.println("我是通过---"+memo+"----");
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
