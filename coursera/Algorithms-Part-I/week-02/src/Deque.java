import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node head;
    private Node tail;

    public Deque() {
        // construct an empty deque
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        // is the deque empty?
        return size == 0;
    }

    public int size() {
        // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {
        // add the item to the front
        Node node = new Node(item);
        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            head.pre = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addLast(Item item) {
        // add the item to the end
        Node node = new Node(item);
        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        size++;
    }

    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.pre = null;
        }
        size--;
        return node.dat;
    }

    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.pre;
            tail.next = null;
        }
        size--;
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
        // return an iterator over items in order from front to end
        return new MyIterator();
    }

    public static void main(String[] args) {
        // unit testing (optional)
        Deque<String> deque = new Deque<>();

        StdOut.println("Testing add: ");
        deque.addFirst("hello");
        StdOut.println(deque);
        deque.addFirst("last");
        StdOut.println(deque);
        deque.addLast("world");
        StdOut.println(deque);

        StdOut.println("Testing iterator: ");
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }

        StdOut.println("Testing remove: ");
        StdOut.println(deque.removeFirst());
        StdOut.println(deque);

        StdOut.println(deque.removeFirst());
        StdOut.println(deque);

        StdOut.println(deque.removeLast());
        StdOut.println(deque);

    }

    private class MyIterator implements Iterator<Item> {

        private Node next;

        public MyIterator() {
            next = head;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = next.dat;
            next = next.next;
            return item;
        }

    }

    private class Node {

        private final Item dat;
        private Node pre;
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
            r.append("[" + (pre == null ? "<null>" : pre.dat.toString()) + " -> ");
            r.append(dat.toString() + " -> ");
            r.append((next == null ? "<null>" : next.dat.toString()) + "]");
            return r.toString();
        }
    }
}