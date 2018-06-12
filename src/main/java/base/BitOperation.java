package base;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pc on 2018/5/7.
 * 位运算
 * @author zzc
 */
public class BitOperation {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(2 | 2);
        AtomicInteger count = new AtomicInteger(20);
        int c= -1;
        c = count.getAndIncrement();
        System.out.println(count);
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(20);
        blockingQueue.put("aaa");
        ExecutorService executorService = new ThreadPoolExecutor(2,2,2L,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        executorService.submit(()->{
            try {
                blockingQueue.put("bbb");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        });
        executorService.submit(()->{
            try {
                blockingQueue.put("ccc");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        });
//        ConcurrentHashMap<Object,String> map = new ConcurrentHashMap<>();
//        for (int i=0;i<100;i++){
//            if(i>60){
//                map.put(new Key("key"+i),"value");
//            }else {
//                map.put(new Key("key"+i),"value1");
//            }
//        }
    }
}
