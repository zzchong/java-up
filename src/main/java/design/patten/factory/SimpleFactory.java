package design.patten.factory;

/**
 * Created by pc on 2018/2/7.
 * 简单工厂模式/静态工厂模式
 * 缺点：工厂类扩展比较困难，不符合开闭原则，但是仍然是一个非常实用的设计模式
 * @author zz_chong
 */
public class SimpleFactory {

    public static AbstractProduct createProduct(Class c) {
        AbstractProduct product = null;
        try {
            product = (Product)Class.forName(c.getName()).newInstance();
            product.setMemo("简单工厂模式");
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }
}
