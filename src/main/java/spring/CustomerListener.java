package spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by pc on 2017/10/19.
 * 自定义监听器
 * 无序监听器
 */
@Component
public class CustomerListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContentEvent){
            System.out.println("李四收到了新内容:"+event.getSource());
        }
    }
}
