package design.patten.builder;

import java.util.ArrayList;

/**
 * Created by pc on 2018/2/23.
 * 抽象建造者
 * @author  zzc
 */
public abstract  class AbstractBuilder {

    /**
     * 设置产品不同部分以获得不同的产品（此处是根据做事顺序来获得不同的产品）
     */
    public abstract void setPart(ArrayList<String> list);

    /**
     * 建造产品
     * @return 返回产品
     */
    public abstract AbstractProduct builderProduct();
}
