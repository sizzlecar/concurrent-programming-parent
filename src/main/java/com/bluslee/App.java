package com.bluslee;

import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App {
    private static Integer val = 0;

    public synchronized static Integer getVal() {
        return val;
    }

    public synchronized static void setVal(Integer val) {
        App.val = val;
    }

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i= 0; i < 200; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++){
                        synchronized (App.class){
                            Integer val = App.getVal();
                            App.setVal(val + 1);
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try{
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("val：" + App.val);

    }
}
