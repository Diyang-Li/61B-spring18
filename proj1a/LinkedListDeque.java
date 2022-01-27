import java.lang.annotation.Target;
import java.nio.channels.NonWritableChannelException;

/**
 * @author Diyang Li
 * @create 2022-01-03 10:38 AM
 */
public class LinkedListDeque<T> {
    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param
     */

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private static int totalSize;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        totalSize = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node(null, sentinel, sentinel);
        Node curNode = new Node(item, sentinel, sentinel);
        sentinel.next = curNode;
        sentinel.prev = curNode;
        totalSize = 1;
    }

    public void addFirst(T item) {
        Node curNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = curNode;
        sentinel.next = curNode;
        totalSize++;
    }


    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item
     */
    public void addLast(T item) {
        Node curNode = new Node(item, null, null);
        curNode.prev = sentinel.prev;
        sentinel.prev.next = curNode;
        curNode.next = sentinel;
        sentinel.prev = curNode;
        totalSize++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     *
     * @return
     */
    public boolean isEmpty() {
        return (totalSize == 0) ? true : false;
    }

    /**
     * Returns the number of items in the deque.
     *
     * @return
     */
    public int size() {
        return totalSize;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node pointer = sentinel.next;
        while (pointer.item != null) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return
     */
    public T removeFirst() {
        if (totalSize == 0) {
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        totalSize--;

        return firstItem;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return
     */
    public T removeLast() {
        if (totalSize == 0){
            return null;
        }
        T lastItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        totalSize--;
        return lastItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     *
     * @param index
     * @return
     */
    public T get(int index) {
        return null;
    }

    public T getRecursive(int index) {
        return null;
    }

    public static void main(String[] args) {
        LinkedListDeque l = new LinkedListDeque();
        l.addFirst(12);
        l.addFirst(13);
        l.addFirst(14);
        l.printDeque();
        System.out.println(l.removeLast());
    }

}
