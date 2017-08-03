package jdk8.Thread;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by pc on 2017/8/3.
 */
public class Index {

    @Test
    public void  runnableTest(){

        Thread thread = new Thread(
                ()->{System.out.println("Thread name is "+Thread.currentThread().getName());}
                );
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);//Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Done!");
        /**
         * ExecutorService
         * Executors are capable of running asynchronous tasks and typically manage a pool of threads
         *
         * shutdown()
         *  the java process never stops! Executors have to be stopped explicitly - otherwise they keep listening for new tasks
         * An ExecutorService provides two methods for that purpose: shutdown() waits for currently running tasks to finish
         * while shutdownNow() interrupts all running tasks and shut the executor down immediately.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            executorService
                    .submit(
                            ()->{
                                System.out.println("Thread name is "+Thread.currentThread().getName());
                            }
                    );
            executorService.shutdown();
            executorService.awaitTermination(5,TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(!executorService.isTerminated()){
                System.out.println("cancel non-finished tasks");
            }
            executorService.shutdownNow();
            System.out.println("shutdown now");
        }

        /**
         * Callables
         * In addition to Runnable executors support another kind of task named Callable
         * Callables are functional interfaces just like runnables but instead of being void they return a value
         * Since submit() doesn't wait until the task completes,
         * the executor service cannot return the result of the callable directly.
         * Instead the executor returns a special result of type Future which can be used to retrieve the actual result at a later point in time.
         */
        Callable<Integer> task = ()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }catch (InterruptedException e){
                throw new IllegalStateException("task interrupted",e);
            }
        };
        executorService = Executors.newFixedThreadPool(5);
        Future<Integer> future = executorService
                                             .submit(task);

        System.out.println("future done ?  "+future.isDone());
        Integer result = null;
        try {
            /**
             * Calling the method get() blocks the current thread and waits until the callable completes
             */
            result = future.get();
            System.out.println("future done ?  "+future.isDone());
            System.out.println("result : "+result);
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            if(!executorService.isTerminated()){

            }
            executorService.shutdownNow();
            System.out.println("shutdown now");
        }

        /**
         * future.get()
         * Any call to future.get() will block and wait until the underlying callable has been terminated.
         * In the worst case a callable runs forever - thus making your application unresponsive.
         * You can simply counteract those scenarios by passing a timeout:
         */
        executorService = Executors.newFixedThreadPool(5);
        Future<Integer> future1 = executorService.submit(
                ()->{
                    try {
                       TimeUnit.SECONDS.sleep(2);
                        return 123;
                    }catch (InterruptedException e){
                        throw new IllegalStateException("task interrupted", e);
                    }
                }
        );
        try {
            future1.get(1,TimeUnit.SECONDS);
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }finally {
            if(!executorService.isTerminated()){

            }
            executorService.shutdownNow();
            System.out.println("shutdown now");
        }
        /**
         *  invokeAll()
         * Executors support batch submitting of multiple callables at once via invokeAll().
         * This method accepts a collection of callables and returns a list of futures.
         *
         * newWorkStealingPool()
         * The above example uses yet another type of executor created via newWorkStealingPool().
         * This factory method is part of Java 8 and returns an executor of type ForkJoinPool which works slightly different than normal executors.
         * Instead of using a fixed size thread-pool ForkJoinPools are created for a given parallelism size which per default is the number of available cores of the hosts CPU.
         */
        executorService = Executors.newWorkStealingPool();
        List<Callable<String>> callables  = Arrays.asList(
                ()->"task1",
                ()->"task2",
                ()->"task3"
        );
        List<Future<String>> futures;
        try {
            executorService.invokeAll(callables)
                .stream()
                    .map(future2 -> {
                        try {
                            return future2.get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(!executorService.isTerminated()){

            }
            executorService.shutdownNow();
            System.out.println("Thread shut down naw");
        }

        /**
         * invokeAny()
         * Submitting those callables to an executor via invokeAny() returns the string result of the fastest callable
         */
        executorService = Executors.newWorkStealingPool();
        List<Callable<String>> callableList = Arrays.asList(
                callable("task1",2),
                callable("task2",1),
                callable("task3",3)
        );
        try {
            String result2 = executorService.invokeAny(callableList);
            System.out.println(result2);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            if(!executorService.isTerminated()){

            }
            executorService.shutdownNow();
            System.out.println("Thread shut down naw");
        }

        /**
         * Scheduled Executors
         * In order to periodically run common tasks multiple times, we can utilize scheduled thread pools
         *
         * ScheduledExecutorService
         * A ScheduledExecutorService is capable of scheduling tasks to run either periodically or once after a certain amount of time has elapsed.
         *
         * getDelay()
         * etrieve the remaining delay
         */

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        ScheduledFuture<?> scheduledFuture =
            scheduledExecutorService.schedule(
                    ()->System.out.println("Thread name is "+Thread.currentThread().getName()+"  Scheduling :"+System.nanoTime()),
                    1,
                    TimeUnit.SECONDS
            );

        try {
            TimeUnit.MILLISECONDS.sleep(1);
            long remainingDelay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
            System.out.printf("Remaining Delay: %sms", remainingDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            scheduledExecutorService.shutdownNow();
        }


        /**
         * scheduleAtFixedRate()
         * scheduleWithFixedDelay()
         * In order to schedule tasks to be executed periodically, executors provide the two methods scheduleAtFixedRate() and scheduleWithFixedDelay().
         */
        scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleWithFixedDelay(
                ()->System.out.println(System.nanoTime()),
                0,
                2,
                TimeUnit.SECONDS
        );

    }

    Callable<String> callable(String result,long sleepSeconds){
        return ()->{
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };

    }







}
