package design.patten.template;

/**
 * 抽象模板类，由基本方法和模板方法构成
 * Created by pc on 2018/2/8.
 * @author zz_chong
 */
public abstract class AbstractTemplate {

    /**
        基本方法
     */
     abstract void doSomething();

    /**
     * 基本方法
     */
     abstract void doAnything();


    /**
     * 模板方法
     * 为防止恶意操作，一般模板方法都加上final关键字，不允许被重写
     */
    final void templateMethod(){
        /*
            调用基本方法，完成相关逻辑
         */
        this.doSomething();

        if(hook()) {
            this.doAnything();
        }
    }

    /**
     * 钩子方法（子类可以重写）
     * 通过钩子方法来控制模板方法的执行
     * @return 默认为true
     */
    boolean hook(){
         return true;
    }
}
