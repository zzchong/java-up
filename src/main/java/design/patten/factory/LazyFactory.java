package design.patten.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟加载的工厂类
 * 通过定义一个map容器，将已有的对象存入其中，当再次使用时，从map中直接取出。
 * 扩展：
 *      可以限制产品类的最大实例化数量（比如JDBC连接数据库的最大连接数量MaxConnections）
 * Created by pc on 2018/2/8.
 * @author zz_chong
 */
public class LazyFactory {

    private static final Map<String,AbstractProduct> MAP = new HashMap<>();

    /**
        有线程安全问题 所以加上synchronized
     */
    public static synchronized AbstractProduct createProduct(String type){

        AbstractProduct product = null;

        if(MAP.containsKey(type)){
            product = MAP.get(type);
        }else {
            product = new Product();
            product.setMemo("延迟加载的工厂类");
            MAP.put(type,product);
        }
        return  product;
    }
}
