package spring;

import org.springframework.context.ApplicationEvent;

/**
 * Created by pc on 2017/10/19.
 * 定义自定义事件
 */
public class ContentEvent  extends ApplicationEvent{
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ContentEvent(Object source) {
        super(source);
    }
}
