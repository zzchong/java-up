package design.patten.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/2/23.
 * 产品类抽象（模板方法模式）
 * @author zzc
 */
public abstract class AbstractProduct {

    private final static String ONE = "ONE";

    private final static String TWO = "TWO";

    /**
     * 执行顺序
     */
    private List<String> list = new ArrayList<>();

    /**
     * 基本方法
     */
     abstract void doSomething1();

    /**
     * 基本方法
     */
     abstract void doSomething2();

     protected final void start(){
        list.forEach(s -> {
            if(ONE.equals(s)){
                doSomething1();
            }
            if(TWO.equals(s)){
                doSomething2();
            }
        });
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
