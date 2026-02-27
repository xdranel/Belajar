package belajar.java.collection;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeApp {
    public static void main(String[] args) {

//        Deque<String> stack = new ArrayDeque<>();
        Deque<String> stack = new LinkedList<>();

        stack.offerLast("John");
        stack.offerLast("Mary");
        stack.offerLast("Sam");

        System.out.println(stack.pollLast());
        System.out.println(stack.pollLast());
        System.out.println(stack.pollLast());
        System.out.println(stack.pollLast());

        Deque<String> queue = new LinkedList<>();
        queue.offerLast("John");
        queue.offerLast("Mary");
        queue.offerLast("Sam");

        System.out.println(queue.pollFirst());
        System.out.println(queue.pollFirst());
        System.out.println(queue.pollFirst());

    }
}
