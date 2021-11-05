/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item> {

    private Object[] s;
    private int n = 0;
    public FixedCapacityStack(int n) {
        s = new Object[n];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(Item item) {
        s[n++] = item;
    }

    public Item pop() {
        Item item = (Item) s[--n];
        s[n] = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIerator();
    }

    private class ReverseArrayIerator implements Iterator<Item> {
        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return (Item) s[--i];
        }
    }
}
