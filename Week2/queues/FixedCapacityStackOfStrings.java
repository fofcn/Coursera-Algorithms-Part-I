/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

public class FixedCapacityStackOfStrings {

    private String[] s;
    private int n = 0;

    public FixedCapacityStackOfStrings(int n) {
        s = new String[n];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(String item) {
        s[n++] = item;
    }

    public String pop() {
        String item = s[--n];
        s[n] = null;
        return item;
    }

    public static void main(String[] args) {

    }
}
