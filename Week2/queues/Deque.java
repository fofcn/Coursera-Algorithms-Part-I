/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private Node<Item> first;
    private Node<Item> last;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        size++;
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        size++;
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first.item = null;
        if (size > 1) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }

        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        last.item = null;
        if (size > 1) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> cur;
        public DequeIterator() {
            this.cur = first;
        }

        public boolean hasNext() {
            return cur != null;
        }

        public Item next() {
            if (cur == null) {
                throw new NoSuchElementException();
            }

            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<Item> {
        private Item item;

        private Node<Item> next;
        private Node<Item> prev;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // empty deque add test
        Deque<Integer> deque = new Deque<>();
        // isEmpty test
        StdOut.println("empty deque: " + deque.isEmpty());
        StdOut.println("empty deque: " + deque.size());
        deque.addFirst(1);
        StdOut.println("empty deque add first test.");

        Integer item;
        deque = new Deque<>();
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);
        while ((item = deque.removeFirst()) != null) {
            StdOut.print(item + " ");
        }
        StdOut.println();

        deque = new Deque<>();
        deque.addLast( 1);
        StdOut.println("empty deque add last test.");

        // empty deque add last multi-item test
        deque = newDequeAddFirst(7);
        StdOut.println("assert: after add 7 items, expect deque.size() == 7, actual result: " + (deque.size() == 7));
        StdOut.println("assert: after add 7 items, expect deque.isEmpty() == false, actual result: " + deque.isEmpty());
        // test remove first

        while ((item = deque.removeFirst()) != null) {
            StdOut.print(item + " ");
        }
        StdOut.println();
        deque = newDequeAddFirst(7);
        StdOut.println("assert: after add 7 items, expect deque.size() == 7, actual result: " + (deque.size() == 7));
        StdOut.println("assert: after add 7 items, expect deque.isEmpty() == false, actual result: " + deque.isEmpty());
        // test remove first
        while ((item = deque.removeLast()) != null) {
            StdOut.print(item + " ");
        }
        StdOut.println();

        // empty deque add first multi-item test
        deque = newDequeAddLast(7);

        StdOut.println("assert: after add 7 items, expect deque.size() == 7, actual result: " + (deque.size() == 7));
        StdOut.println("assert: after add 7 items, expect deque.isEmpty() == false, actual result: " + deque.isEmpty());

        // test remove first
        while ((item = deque.removeFirst()) != null) {
            StdOut.print(item + " ");
        }
        StdOut.println();

        // Iterator test with 12 items
        deque = newDequeAddLast(12);
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + " ");
        }
        StdOut.println();

        deque = new Deque<Integer>();
        iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next());
        }
        StdOut.println();

        try {
            iterator.remove();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }


    }

    private static Deque<Integer> newDequeAddFirst(int size) {
        // empty deque add first multi-item test
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < size; i++) {
            deque.addFirst( i + 1);
        }

        return deque;
    }

    private static Deque<Integer> newDequeAddLast(int size) {
        // empty deque add first multi-item test
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < size; i++) {
            deque.addLast( i + 1);
        }

        return deque;
    }

}