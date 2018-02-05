import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Node head;
    private Node tail;

    public RandomizedQueue() {
        // construct an empty randomized queue
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        // is the randomized queue empty?
        return size == 0;
    }

    public int size() {
        // return the number of items on the randomized queue
        return size;
    }

    public void enqueue(Item item) {
        // add the item
        Node node = new Node(item);
        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public Item dequeue() {
        // remove and return a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int itemToRemove = StdRandom.uniform(0, size());
        Node pre = null;
        Node node = head;
        while (node != null && itemToRemove-- > 0) {
            pre = node;
            node = node.next;
        }
        if (node == null) {
            throw new NoSuchElementException();
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            if (node == head) {
                head = node.next;
            }
            if (node == tail) {
                tail = pre;
            }
            if (pre != null) {
                pre.next = node.next;
            }
        }
        size--;
        return node.dat;
    }

    public Item sample() {
        // return a random item (but do not remove it)
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int itemToRemove = StdRandom.uniform(0, size());
        Node node = head;
        while (node != null && itemToRemove-- > 0) {
            node = node.next;
        }
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.dat;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append("<" + size() + "> " + "{");
        Node node = head;
        while (node != null) {
            r.append(node.toString());
            if (node != tail) {
                r.append(", ");
            }
            node = node.next;
        }
        r.append("}");
        return r.toString();
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new MyIterator();
    }

    public static void main(String[] args) {
        // unit testing (optional)
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("hello");
        StdOut.println(rq);

        rq.enqueue("world");
        StdOut.println(rq);

        rq.enqueue("root");
        StdOut.println(rq);

        for (int i = 0; i < 3; i++) {
            rq.enqueue("item " + i);
        }

        // while (!rq.isEmpty()) {
        //     StdOut.println(rq.dequeue());
        //     // StdOut.println(rq);
        // }

        StdOut.println("Testing iterator: ");
        Iterator<String> iterator = rq.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }

    }

    private class MyIterator implements Iterator<Item> {

        private final Item[] items;
        private int index;

        public MyIterator() {
            items = (Item[]) new Object[size()];
            Node node = head;
            for (int i = 0; i < size(); i++) {
                items[i] = node.dat;
                node = node.next;
            }
            StdRandom.shuffle(items);
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < items.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[index++];
        }
    }

    private class Node {

        private final Item dat;
        private Node next;

        public Node(Item dat) {
            if (dat == null) {
                throw new IllegalArgumentException();
            }
            this.dat = dat;
        }

        @Override
        public String toString() {
            StringBuilder r = new StringBuilder();
            r.append("[");
            r.append(dat.toString() + " -> ");
            r.append((next == null ? "<null>" : next.dat.toString()) + "]");
            return r.toString();
        }
    }
}