/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

public class QueueOfString {
    private Node first;
    private Node last;
    private int n = 0;

    private class Node {
        private String item;

        private Node next;
    }

    public QueueOfString() {

    }

    public void enqueue(String item) {
        Node newNode = new Node();
        newNode.item = item;
        n++;

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = new Node();
        }
    }

    public String dequeue() {
        n--;
        String item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {

    }
}
