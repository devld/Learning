/**
 * <h3>Shuffling a linked list</h3>
 * 
 * Given a singly-linked list containing n items, 
 * rearrange the items uniformly at random. 
 * Your algorithm should consume a logarithmic (or constant) 
 * amount of extra memory and run in time proportional to nlogn in the worst case.
 */

public class ShuffleLinkedList {

    private static LinkedList.Node shuffle(LinkedList.Node node) {
        if (node.next == null) {
            return node;
        }
        LinkedList.Node mid = node;
        LinkedList.Node x2 = node;

        while (x2.next != null && x2.next.next != null) {
            mid = mid.next;
            x2 = x2.next.next;
        }

        LinkedList.Node left = node;
        LinkedList.Node right = mid.next;
        // cut off
        mid.next = null;

        LinkedList.Node i = shuffle(left);
        LinkedList.Node j = shuffle(right);
        LinkedList.Node newNode = new LinkedList.Node();
        LinkedList.Node k = newNode;

        while (i != null || j != null) {
            if (i == null) {
                k.next = j;
                k = k.next;
                j = j.next;
            } else if (j == null) {
                k.next = i;
                k = k.next;
                i = i.next;
            } else if (Math.random() > .5) {
                k.next = i;
                k = k.next;
                i = i.next;
            } else {
                k.next = j;
                k = k.next;
                j = j.next;
            }
        }

        return newNode.next;

    }

    public static void shuffle(LinkedList list) {
        LinkedList.Node node = list.head;
        if (node == null) {
            return;
        }
        list.head = shuffle(node);
    }

    public static void main(String[] args) {

        LinkedList list = new LinkedList();

        for (int i = 100; i >= 0; i--) {
            list.insert(i);
        }

        System.out.println(list);

        shuffle(list);

        System.out.println(list);

    }
}

class LinkedList {

    Node head;

    static class Node {
        int data;
        Node next;

        public Node(int dat) {
            this.data = dat;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return data + "";
        }
    }

    public void insert(int dat) {
        Node node = new Node(dat);
        node.next = head;
        head = node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = head;
        while (node != null) {
            sb.append(node);
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }
        return sb.toString();
    }

}