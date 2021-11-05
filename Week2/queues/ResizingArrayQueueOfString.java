/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

public class ResizingArrayQueueOfString {

    private String[] q;
    private int head = 0;
    private int tail = 0;

    public ResizingArrayQueueOfString(int n) {
        q = new String[n];
    }

    public void enqueue(String item) {
        if (tail % q.length - head == q.length) {
            resize(q.length * 2);
        }

        q[tail % q.length] = item;
        tail++;
    }

    public String dequeue() {
        if (head == tail) {
            return null;
        }

        String item = q[head % q.length];
        head++;

        return item;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return tail - head;
    }

    private void resize(int newSize) {
        String[] newQ = new String[newSize];
        for (int i = head % q.length; i < tail % q.length; i++) {
            newQ[i] = q[i];
        }
    }

    public static void main(String[] args) {

    }
}
