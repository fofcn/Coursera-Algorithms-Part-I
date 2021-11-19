/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.11.19
 *  Description: 洗牌
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Shuffling {

    public static void shuffle(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // uniform返回0-（n-1）范围的随机数，所以这里需要加1
            int r = StdRandom.uniform(i + 1);
            exchange(a, i, r);
        }
    }

    private static void exchange(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {

    }
}
