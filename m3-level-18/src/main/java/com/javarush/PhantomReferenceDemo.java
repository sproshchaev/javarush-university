package com.javarush;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {

    static ReferenceQueue referenceQueue = new ReferenceQueue();

    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object() {
          protected void finalize() {
              System.out.println("finalize() вызван");
          }
        };

        PhantomReference<Object> phantomReference = new PhantomReference<>(obj, referenceQueue);

        System.out.println("phantomReference.get(): " + phantomReference.get());

        obj = null;
        System.gc(); // нет гарантии вызова
        Thread.sleep(1000);

        System.out.println("После GC в очереди: " + (referenceQueue.poll() != null));

        // .get() всегда возвращает null, используется с ReferenceQueue

    }

}
