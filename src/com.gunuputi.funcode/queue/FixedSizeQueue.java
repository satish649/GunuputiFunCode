package com.gunuputi.funcode.queuee;

import java.lang.reflect.Array;

public class FixedSizeQueue<E> {

    // initial state of queue
    private int head = -1;
    private int tail = -1;
    private int size = 0;

    private Object[] elements;

    public FixedSizeQueue(Class<?> elemType, int size) {
        elements = (E[]) Array.newInstance(elemType, size);
    }

    public <E> boolean enqueue(E e) {

        if (size == elements.length) {
            throw new RuntimeException("Queue is full and cannot accomodate elements!");
        }

        head = (head + 1) % elements.length;
        elements[head] = e;
        size++;

        if (tail == -1) {
            tail = head;
        }

        return true;
    }

    public <E> E deQueue() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty and not element can be vended!");
        }

        E e = (E) elements[tail];
        elements[tail] = null;
        tail = (tail + 1) % elements.length;
        size--;

        if (size == 0) {
            head = tail = -1;
        }

        return e;
    }

    public <E> E peek() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty and not element can be vended!");
        }

        return (E) elements[tail];
    }

    public int size() {
        return size;
    }

    private void currentState() {
        System.out.println(String.format("Head: [%s] Tail: [%s], Size: [%s]", head, tail, size));
    }

    public static void main(String[] args) {
        FixedSizeQueue<String> tc1 = new FixedSizeQueue(String.class, 5);
        tc1.enqueue("test");
        tc1.deQueue();

        tc1.enqueue("test");
        tc1.enqueue("test");
        tc1.enqueue("test");
        tc1.enqueue("test");
        tc1.enqueue("test");
        tc1.deQueue();
        tc1.enqueue("test");
        try {
            tc1.enqueue("test");
            throw new RuntimeException("Code should not reach here!");
        } catch (RuntimeException rte) {
            // expected behaviour
            System.out.println("Expected behaviour tc1");
        }

        tc1.deQueue();
        tc1.deQueue();
        tc1.deQueue();
        tc1.deQueue();
        tc1.deQueue();
        tc1.currentState();
        tc1.enqueue("test");
        tc1.currentState();
    }
}
