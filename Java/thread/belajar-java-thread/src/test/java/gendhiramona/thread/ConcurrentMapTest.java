package gendhiramona.thread;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapTest {

  @Test
  void testConcurrentMap() throws InterruptedException {

    final var countDown = new CountDownLatch(100);
    final var map = new ConcurrentHashMap<Integer, String>();
    final var executor = Executors.newFixedThreadPool(100);

    for (int i = 0; i < 100; i++) {
      final var index = i;
      executor.execute(() -> {
        try {
          Thread.sleep(1000L);
          map.putIfAbsent(index, "Data-" + index);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          countDown.countDown();
        }
      });
    }

    executor.execute(() -> {
      try {
        countDown.await();
        System.out.println("All tasks completed");
        map.forEach((k, v) -> System.out.println(k + " : " + v));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  @Test
  void testCollection() {

    List<String> list = new LinkedList<>(List.of("Food", "Bar", "Baz"));
    list.add("Hello");

    // Turning From Collection to Thread Safe/Concurrent Collection
    List<String> threadSafeList = Collections.synchronizedList(list);
    threadSafeList.forEach(System.out::println);
  }
}
