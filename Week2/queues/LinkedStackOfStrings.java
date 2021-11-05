/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

public class LinkedStackOfStrings {

    private Node first = null;

    private class Node {
        String item;

        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node newNode = new Node();
        newNode.item = item;

        newNode.next = first;
        first = newNode;
    }

    public String pop() {
        Node node = first;
        first = first.next;
        return node.item;
    }

    public static void main(String[] args) {

    }

}
