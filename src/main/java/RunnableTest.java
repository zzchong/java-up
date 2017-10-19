import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by pc on 2017/6/2.
 *
 * Synchronized  内置锁获得锁和释放锁是隐式的，进入synchronized修饰的代码就获得锁，走出相应的代码就释放锁。
 *               与Synchronized配套使用的通信方法通常有wait(),notify()。
 *               wait()方法会立即释放当前锁，并进入等待状态，等待到相应的notify并重新获得锁过后才能继续执行；notify()不会立刻立刻释放锁，必须要等notify()所在线程执行完synchronized块中的所有代码才会释放。
 *               内置锁在进入同步块时，采取的是无限等待的策略，一旦开始等待，就既不能中断也不能取消，容易产生饥饿与死锁的问题
 *               在线程调用notify方法时，会随机选择相应对象的等待队列的一个线程将其唤醒，而不是按照FIFO的方式，如果有强烈的公平性要求，比如FIFO就无法满足
 *ReentrantLock  ReentrantLock是显示锁，需要显示进行 lock 以及 unlock 操作
 *               与ReentrantLock搭配的通信方式是Condition
 *               Condition是被绑定到Lock上的，必须使用lock.newCondition()才能创建一个Condition。
 *               Condition的优秀之处在于它可以为多个线程间建立不同的Condition。
 *               编码
 *               Lock lock = new ReentrantLock();
 *               Condition condition = lock.newCondition();
 *               //condition.await();//this.wait(); condition.signal();//this.notify(); condition.signalAll();//this.notifyAll();
 *               lock.lock();
 *               try{
 *
 *               }finally{
 *                  lock.unlock();
 *               }
 *
 *               lock.lockInterruptibly() 可以使得线程在等待锁是支持响应中断；
 *               lock.tryLock() 可以使得线程在等待一段时间过后如果还未获得锁就停止等待而非一直等待。
 *               有了这两种机制就可以更好的制定获得锁的重试机制，而非盲目一直等待，可以更好的避免饥饿和死锁问题
 *
 *               ReentrantLock还提供了一种非互斥的读写锁，
 *               也就是不强制每次最多只有一个线程能持有锁，它会避免“读/写”冲突，“写/写”冲突，但是不会排除“读/读”冲突，
 *               因为“读/读”并不影响数据的完整性，所以可以多个读线程同时持有锁，这样在读写比较高的情况下，性能会有很大的提升
 */
public class RunnableTest {

    static ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();

    public static void main(String [] args){

        Integer c = new Integer(100);
        Integer e =  100;
        int f = 100;
        System.out.println(c.equals(e));
        System.out.println(c==e);
        System.out.println(f==e);
        System.out.println(e.equals(f));

//
//        Thread t1 = new Thread(new AddMapValue());
//        Thread t2 = new Thread(new AddMapValue());
//        t1.start();
//        t2.start();

        //******** Synchronized test**********//
//        List list = new ArrayList();
//        Thread r = new Thread(new ReadList(list));
//        Thread w = new Thread(new WriteList(list));
//        r.start();
//        w.start();
    }
    static class  AddMapValue implements Runnable{


        public void run() {
          for (int i=0;i<1000;i++){
              addString("key");
          }
        }
    }

    static class  ReadList implements Runnable{

        private final List list;

        private ReadList(List list) {
            this.list = list;
        }

        public void run() {
            System.out.println("ReadList begin at "+System.currentTimeMillis());
            synchronized (list){
                try {
                    Thread.sleep(1000);
                    System.out.println("list.wait() begin at "+System.currentTimeMillis());
                    list.wait();
                    System.out.println("list.wait() end at "+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ReadList end at "+System.currentTimeMillis());
        }
    }

   static class  WriteList implements Runnable{

        private final List list;

        private WriteList(List list) {
            this.list = list;
        }

        public void run() {
            System.out.println("WriteList  begin at "+System.currentTimeMillis());
            synchronized (list){
                System.out.println("get lock at "+System.currentTimeMillis());
                list.notify();
                System.out.println("list.notify() at "+System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get out of block at "+System.currentTimeMillis());
            }
            System.out.println("WriteList end at "+System.currentTimeMillis());
        }
    }


    static class RWLocakList{

        private final List list;

        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Lock readLock = lock.readLock();
        private final Lock writeLock = lock.writeLock();
        public RWLocakList(List list) {
            this.list = list;
        }
    }


     static synchronized void  addString(String key){

         map.put(key,map.containsKey(key)?map.get(key)+1:1);
         System.out.println(Thread.currentThread().getName()+"-----------------"+map.get("key"));

    }

}
