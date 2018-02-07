import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * <h3>Queue with two stacks</h3>
 * 
 * Implement a queue with two stacks so that each queue operations
 * takes a constant amortized number of stack operations.
 */

public class QueueWithStacks<T> {

    private final Stack<T> stackIn;
    private final Stack<T> stackOut;
    private int size;

    public QueueWithStacks() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
        size = 0;
    }

    public void enqueue(T item) {
        stackIn.push(item);
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        size--;
        return stackOut.pop();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        QueueWithStacks<String> qws = new QueueWithStacks<>();

        qws.enqueue("Hello");
        qws.enqueue("World");

        System.out.println(qws.dequeue());
        qws.enqueue("Item 1");

        System.out.println(qws.dequeue());

        qws.enqueue("Item 2");

        System.out.println(qws.dequeue());

        qws.enqueue("Item 3");

        while (!qws.isEmpty()) {
            System.out.println(qws.dequeue());
        }

    }
}