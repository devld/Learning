import java.util.NoSuchElementException;

/**
 * <h3>Stack with max</h3>
 * 
 * Create a data structure that efficiently supports
 * the stack operations (push and pop) and also a return-the-maximum operation.
 * Assume the elements are reals numbers so that you can compare them.
 */

public class StackWithMax<T extends Comparable<? super T>> {

    private Node head;
    private Node maxHead;
    private int size;

    public StackWithMax() {
        size = 0;
        head = null;
        maxHead = null;
    }

    public void push(T item) {
        head = insertHead(head, item);
        size++;
        if (maxHead == null) {
            maxHead = insertHead(maxHead, item);
        } else {
            if (item.compareTo(maxHead.dat) >= 0) {
                maxHead = insertHead(maxHead, item);
            }
        }
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = head;
        head = node.next;
        if (maxHead != null) {
            if (maxHead.dat.compareTo(node.dat) <= 0) {
                maxHead = maxHead.next;
            }
        }
        size--;
        return node.dat;
    }

    public T max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return maxHead.dat;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private Node insertHead(Node head, T dat) {
        Node node = new Node(dat);
        node.next = head;
        return node;
    }

    public static void main(String[] args) {

        StackWithMax<Integer> swm = new StackWithMax<>();

        swm.push(5);
        System.out.println(swm.max());
        swm.push(6);
        System.out.println(swm.max());
        System.out.println("pop: " + swm.pop());
        System.out.println(swm.max());
        swm.push(2);
        System.out.println(swm.max());

        System.out.println("=============");

        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 10);
            swm.push(num);
            System.out.println(num + " pushed, the max is " + swm.max());
        }

        System.out.println("-----------");

        while (!swm.isEmpty()) {
            System.out.print(swm.pop() + " popped, ");
            System.out.println(swm.isEmpty() ? " empty stack" : ("the max is " + swm.max()));
        }

    }

    private class Node {
        T dat;
        Node next;

        public Node(T dat) {
            if (dat == null) {
                throw new NullPointerException();
            }
            this.dat = dat;
        }
    }
}