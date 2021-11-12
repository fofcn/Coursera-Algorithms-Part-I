/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

public class QueueUseTwoStacks<Item> {

    private Stack<Item> inStack;

    private Stack<Item> outStack;

    public void enqueue(Item item) {
        inStack.push(item);
    }

    // remove and return a random item
    public Item dequeue() {
        if (outStack.isEmpty() && !inStack.isEmpty()) {
            Item item = inStack.pop();
            while (item != null) {
                outStack.push(item);
                item = inStack.pop();
            }
        }

        return outStack.pop();
    }

    public static void main(String[] args) {

    }
}
