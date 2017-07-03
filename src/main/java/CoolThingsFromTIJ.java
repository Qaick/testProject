import javafx.beans.WeakInvalidationListener;

import java.io.PipedReader;
import java.io.PipedWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class CoolThingsFromTIJ {
    public static void main(String[] args) throws Exception {
        testRTTI();

    }

    //region Inner Classes
    static void testInnerClasses(){
        new CoolThingsFromTIJ().new A().new B().new C().b();
    }

    class A{
        String fromA = "fromA";
        void a(){
            System.out.println("A.a");
        }
        class B{
            void a(){
                System.out.println("B.a");
            }
            class C{
                void b(){
                    a(); // used B.a()
                    A.this.a();
                    System.out.println(fromA); // have access to all inner tree
                }
            }
        }
    }

    class WithInner{
        class Inner{}
    }

    class InheritInner extends WithInner.Inner{
//        InheritInner(){} // not compiling
        InheritInner(WithInner wi){
            wi.super();
        }
    }

    interface Coordinates{
        int getLatitude();
        int getLongitude();
    }

    static Coordinates getPosition(final int lat, final int lon) {
        return new Coordinates() {
            @Override
            public int getLatitude() {
                return lat;
            }
            @Override
            public int getLongitude() {
                return lon;
            }
        };
    }

    class InnerClass{
//        static int a; // can't have static declaration
    }
    //endregion

    // Loading(find bytecode, create Class object)
    // Linking(check bytecode, memory for static fields, references)
    // Initializing(initializing superclass, static fields, static blocks)
    static void testRTTI(){
        // every constructor is static method
        Class<Integer> integer = Integer.class;
        try {
            Integer integ = integer.newInstance();
            System.out.println("integ = " + integ);
        } catch (InstantiationException e) {
            System.out.println("No default constructor for "+integer);
        } catch (IllegalAccessException e) {
            // can't create new java.lang.Class() xD
        }
        System.out.println(int.class);
        System.out.println(Integer.class);
        System.out.println(Integer.TYPE);

        System.out.println("integer.isInstance(Number.class): "+integer.isInstance(Number.class));
        System.out.println("integer.isAssignableFrom(Number.class): "+Number.class.isAssignableFrom(integer));


    }

    static void realRTTI(){
        Method method;
        Field field;
        Constructor constructor;

    }

    //region Concurrency
    static void testConcurrency() throws Exception{
        concurrency();
        Coordinates c = getPosition(48, 31);
        System.out.println("c.getLatitude() = " + c.getLatitude());
    }
    // has Class monitor to synch
    synchronized static void staticMethod() throws InterruptedException {
        System.out.println("in sm");
        Thread.sleep(1000);
        System.out.println("out sm");
    }

    // has object monitor to synch
    synchronized void nonStaticMethod() throws InterruptedException {
        System.out.println("in nsm");
        Thread.sleep(1000);
        System.out.println("out nsm");
    }

    static void concurrency() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool(); // can end all threads in same time
        final Future<Object> submit = executor.submit(() -> {
            CoolThingsFromTIJ tij = new CoolThingsFromTIJ();
            tij.nonStaticMethod();
            System.out.println("thread was executed");
            return "returns after execution";
        });
        executor.submit(() -> {
            try {
                staticMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.submit(() -> {
            try {
                staticMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown(); // don't allow new threads to be executed
        System.out.println("shutdown");
        System.out.println(submit.get()); // waits for answer

        int i = 3;
        while (i-->0){} // ARROW
        TimeUnit.SECONDS.sleep(1); // usable utility class
//        Thread.currentThread().setDaemon(true); // don't work. Thread could be set as daemon only before start.

        // race was realized by this
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                // this will be done after every iterations in moment every thread done chunk and is cyclicBarrier.await()
            }
        });
//        PipedReader, PipedWriter - create pipe between thread to send some data
//        Exchanger<Data> - to exchange(swap) data between threads

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

        Collections.synchronizedList(new ArrayList<>());

        ThreadLocal<Integer> threadLocalVariable = new ThreadLocal<>();
    }
    //endregion

    void imitateWorking() throws InterruptedException {
        System.out.println("should not be running right now");
        int i = 10;
        while (i-->0){
            Thread.sleep(1000) ;
            System.out.println("still running");
        }
        System.out.println("end...");
    }
}
