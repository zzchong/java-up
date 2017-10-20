package spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by pc on 2017/10/19.
 * 自定义监听器
 * 有序监听器
 */
@Component
public class OrderCustomerListener2 implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType==ContentEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType==String.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("孙六在王五之后收到新的内容:"+event.getSource());
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
