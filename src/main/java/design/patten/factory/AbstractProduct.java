package design.patten.factory;

/**
 * Created by pc on 2017/10/9.
 * 抽象产品类  被创建对象的抽象类
 * @author zz_chong
 */
public abstract class AbstractProduct {

    String memo;

    /**
     * 产品类的公共方法
     */
    public void common(){

    }

    /**
     * 产品类其他方法
     */
    public  void print(){
        System.out.println("我是通过---"+memo+"----生成的");
    }

    public String getMemo() {
        return memo;
    }

    void setMemo(String memo) {
        this.memo = memo;
    }
}
