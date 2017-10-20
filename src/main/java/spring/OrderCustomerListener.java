package spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by pc on 2017/10/19.
 * 自定义监听器
 * 有序监听器
 * supportsEventType:用于指定支持的事件类型，只有支持的才调用onApplicationEvent
 * supportsSourceType:支持的目标类型，只有支持的才调用onApplicationEvent
 * order:顺序，越小优先级越高
 */
@Component
public class OrderCustomerListener implements SmartApplicationListener {

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
        System.out.println("王五在孙六之前收到新的内容:"+event.getSource());
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
