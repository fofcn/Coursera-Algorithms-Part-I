/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            queue.enqueue(str);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
