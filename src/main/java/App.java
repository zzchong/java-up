import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import spring.ContentEvent;

import java.util.concurrent.*;

/**
 * Created by pc on 2017/10/19.
 * @author zz_chong
 */
@SpringBootApplication
@ComponentScan(value = "spring")
public class App {

    @Autowired
    private  static  ApplicationContext context;
    public static void main(String [] args){
        ApplicationContext applicationContext = SpringApplication.run(App.class, args);
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("pool-&d").daemon(true).build());
        executorService.execute(()->{
            try {
                Thread.sleep(2000);
                System.out.println("执行事件");
                System.out.println(context);
                applicationContext.publishEvent(new ContentEvent("dddddd"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }
}
