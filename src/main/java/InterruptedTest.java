import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by pc on 2017/7/18.
 * interrupt（）中断线程
 *
 * 当一个线程运行时，另一个线程可以调用对应的Thread对象的interrupt（）方法来中断它，该方法只是在目标线程中设置一个标志，表示它已经被中断，并立即返回。
 * 这里需要注意的是，如果只是单纯的调用interrupt（）方法，线程并没有实际被中断，会继续往下执行。
 *
 * 使用isInterrupted（）方法判断中断状态
 *  可以在Thread对象上调用isInterrupted（）方法来检查任何线程的中断状态。这里需要注意：线程一旦被中断，isInterrupted（）方法便会返回true，而一旦sleep（）方法抛出异常，它将清空中断标志，此时isInterrupted（）方法将返回false。
 * Thread.interrupted（）判断中断状态
 *  它是静态方法，因此不能在特定的线程上使用，而只能报告调用它的线程的中断状态
 *  如果线程被中断，而且中断状态尚不清楚，那么，这个方法返回true。与isInterrupted（）不同，它将自动重置中断状态为false，第二次调用Thread.interrupted（）方法，总是返回false，除非中断了线程
 *
 *
 * join方法用线程对象调用，如果在一个线程A中调用另一个线程B的join方法，线程A将会等待线程B执行完毕后再执行。
 * yield可以直接用Thread类调用，yield让出CPU执行权给同等级的线程，如果没有相同级别的线程在等待CPU的执行权，则该线程继续执行。
 */
public class InterruptedTest {

    @Test
    public void  interruptTest1() throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->System.out.println("-----------------interrupt test finish-------------"));

        Thread thread1= new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"-----sleep 20 seconds");
                Thread.sleep(20000);
                System.out.println(Thread.currentThread().getName()+"-----after 20 seconds,do something");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"-------invoke interrupt()");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            System.out.println(Thread.currentThread().getName()+"-----some thing do finish");
        });
        Thread thread2 = new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"------will interrupt Thread1");
                Thread.sleep(2000);
                thread1.interrupt();
                System.out.println(Thread.currentThread().getName()+"------interrupt Thread1 success");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        Thread.sleep(200000);
    }



}
