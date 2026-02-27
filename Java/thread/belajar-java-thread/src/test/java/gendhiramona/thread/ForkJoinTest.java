package gendhiramona.thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ForkJoinTest {

    @Test
    void create() {

        var forkJoinPool1 = ForkJoinPool.commonPool();
        var forkJoinPool2 = new ForkJoinPool(6);

        var executor1 = Executors.newWorkStealingPool();
        var executor2 = Executors.newFixedThreadPool(6);

    }

    @Test
    void recursiveAction() throws InterruptedException {

//        var pool = ForkJoinPool.commonPool();
        var pool = new ForkJoinPool(6);
        List<Integer> integers = IntStream.range(0, 1000).boxed().toList();

        var task = new SimpleForkJoinTask(integers);
        pool.execute(task);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void recursiveTask() throws InterruptedException, ExecutionException {
//        var pool = ForkJoinPool.commonPool();
        var pool = new ForkJoinPool(6);
        List<Integer> integers = IntStream.range(0, 1000).boxed().toList();

        var task = new TotalJoinForkTask(integers);
        var submit = pool.submit(task);

        Long result = submit.get();
        System.out.println(result);

        long sum = integers.stream().mapToLong(value -> value).sum();
        System.out.println(sum);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

    public static class SimpleForkJoinTask extends RecursiveAction {

        private List<Integer> integers;

        public SimpleForkJoinTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected void compute() {
            if (integers.size() <= 10) {
                doCompute();
            } else {
                forkCompute();
            }
        }


        private void doCompute() {
            integers.forEach(integer -> {
                System.out.println(Thread.currentThread().getName() + " : " + integer);
            });
        }

        private void forkCompute() {
            var middle = integers.size() / 2;
            var left = integers.subList(0, middle);
            var right = integers.subList(middle, integers.size());
            ForkJoinTask.invokeAll(new SimpleForkJoinTask(left), new SimpleForkJoinTask(right));
        }

//        private void forCompute() {
//            List<Integer> integers1 = integers.subList(0, this.integers.size() / 2);
//            List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());
//
//            SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);
//            SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);
//
//            ForkJoinTask.invokeAll(task1, task2);
//        }
    }

    public static class TotalJoinForkTask extends RecursiveTask<Long> {

        private final List<Integer> integers;

        public TotalJoinForkTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected Long compute() {
            if (integers.size() <= 10) {
                return doCompute();
            } else {
                return forkCompute();
            }
        }

        private Long doCompute() {
            return integers.stream()
                    .mapToLong(value -> value)
                    .peek(value -> {
                        System.out.println(Thread.currentThread().getName() + " : " + value);
                    })
                    .sum();
        }

        private Long forkCompute() {
            var middle = integers.size() / 2;
            var left = integers.subList(0, middle);
            var right = integers.subList(middle, integers.size());

            var task1 = new TotalJoinForkTask(left);
            var task2 = new TotalJoinForkTask(right);
            ForkJoinTask.invokeAll(task1, task2);
            return task1.join() + task2.join();
        }
    }

}
