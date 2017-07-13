package by.training;

import by.training.impls.LFUCache;
import by.training.impls.LRUCache;
import by.training.interfaces.Cache;
import org.apache.logging.log4j.Logger;


public class Runner {
    private static Logger logger;
    public static void main(String[] args) {
        Cache lruCache = new LRUCache(4);
        Cache lfuCache = new LFUCache(5, 0.4);
        for (int k = 0; k < 5; k++) {
            int threadNumberOne = (int) (Math.random() * 100);
            String threadOneName = "Thread_" + threadNumberOne;
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    int key = i * 2 * threadNumberOne;
                    int value = (int) (i * 1000 * Math.random());
                    String threadName = Thread.currentThread().getName();
                    lfuCache.put(key, value);
                    lfuCache.get(key);
                        System.out.println(threadName + Constants.PUT_KEY + key + Constants.VALUE + value);
                        System.out.println(threadName + " " + lfuCache);
                    try {
                        Thread.sleep(21);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lfuCache.get(key);
                }
            }, threadOneName);
            int threadNumberTwo = (int) (Math.random() * 100);
            String threadTwoName = "Thread_" + threadNumberTwo;
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    int key = i * 6 * threadNumberTwo;
                    int value = (int) (i * 100 * Math.random());
                    String threadName = Thread.currentThread().getName();
                    lfuCache.put(key, value);
                    lfuCache.get(key);
                        System.out.println(threadName + Constants.PUT_KEY + key + Constants.VALUE + value);
                        System.out.println(threadName + " " + lfuCache);
                    try {
                        Thread.sleep(33);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, threadTwoName);
            t1.start();
            t2.start();

        }
    }


    private static class Constants {
        static final String PUT_KEY = " put key ";
        public static final String VALUE = " value ";
    }
}
