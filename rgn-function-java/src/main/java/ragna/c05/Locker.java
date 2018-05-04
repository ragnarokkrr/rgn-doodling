package ragna.c05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locker {

    public static void runLocked(Lock lock, Runnable block) {
        lock.lock();
        try{
            block.run();
        } finally {
            lock.unlock();
        }

    }


    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        doOp1();
        doOp2();
    }


    public static void doOp1(){
        runLocked(lock, () -> System.out.println("OP1"));
    }


    public static void doOp2() {
        runLocked(lock, () -> {
            System.out.println("OP2 a");
            System.out.println("OP2 b");
        });
    }
}
