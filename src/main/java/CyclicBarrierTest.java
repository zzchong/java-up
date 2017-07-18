import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by pc on 2017/7/18.
 * CountDownLatch里的线程是到了运行的目标后继续干自己的其他事情
 * CyclicBarrier需要等待其他线程后才能继续完成下面的工作
 *
 * CyclicBarrier 的字面意思是可循环使用(Cyclic)的屏障(Barrier)。
 * 它要做的事情是，让一组线程到达一个屏障(也可以叫同步点)时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
 *
 * CyclicBarrier和CountDownLatch的区别
 *  CountDownLatch: 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行
 *  CyclicBarrier: N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。
 *
 *  CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。所以CyclicBarrier能处理更为复杂的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
 *
 *  CountDownLatch：减计数方式，CyclicBarrier：加计数方式
 *
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        // CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, ()-> System.out.println("所有玩家进入第二关！"));

        for (int i = 0; i < 4; i++) {
            new Thread(new Player(cyclicBarrier, i)).start();
        }
    }

    static class Player implements Runnable{

        private CyclicBarrier cyclicBarrier;
        private int id;

        public Player(CyclicBarrier cyclicBarrier, int id) {
            this.cyclicBarrier = cyclicBarrier;
            this.id = id;
        }

        public void run() {
            try {
                System.out.println("玩家" + id + "正在玩第一关...");
                cyclicBarrier.await();
                System.out.println("玩家" + id + "进入第二关...");
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
        }
    }
}
