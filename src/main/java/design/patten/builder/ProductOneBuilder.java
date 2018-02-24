package design.patten.builder;

import java.util.ArrayList;

/**
 * Created by pc on 2018/2/23.
 * 具体建造者（具体产品类一）
 *      ·注意：如果有多个产品类就有几个具体的建造者，而且这多个产品类具有相同的接口或抽象类
 * @author zzc
 */
public class ProductOneBuilder extends AbstractBuilder{

    private ProductOne product = new ProductOne();

    /**
     * 设置产品零件（此处为根据做事顺序来构建不同的产品类）
     */
    @Override
    public void setPart(ArrayList<String> list) {

        product.setList(list);

        /**
         * 产品类内的逻辑处理（）
         */
    }

    /**
     *
     * @return 返回建造的产品
     */
    @Override
    public ProductOne builderProduct() {
        return product;
    }
}
