package design.patten.factory;

/**
 * Created by pc on 2018/2/7
 * 抽象工厂类
 * @author zz_chong.
 */
public abstract class AbstractFactory {

    /**
     * 创建一个产品对象，其输入参数类型可以自行设置，通常为String,Enum,class等，当然也可以为空
     * (本例子使用的是class)
     * @param c 具体产品class
     * @return 产品对象
     */
    public abstract Product createProduct(Class c);

//    public abstract <T extends ProductOne> T createProduct(Class<T> c);
}
