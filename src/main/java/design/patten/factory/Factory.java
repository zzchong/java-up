package design.patten.factory;

/**
 * Created by pc on 2018/2/7.
 * 具体工厂类
 * @author zz_chong
 */
public class Factory extends AbstractFactory {

    @Override
    public Product createProduct(Class c) {
        Product product = null;
        try {
            product = (Product)Class.forName(c.getName()).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }
}
