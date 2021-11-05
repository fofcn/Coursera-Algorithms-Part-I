/* *****************************************************************************
 *  Name: 冀全喜
 *  email: errorfatal89@gmail.com
 *  Date: 2021.11.05
 *  Description:
 **************************************************************************** */

public class ResizingArrayStackOfStrings {
    private String[] s;
    private int n = 0;
    public ResizingArrayStackOfStrings(int n) {
        s = new String[n];
    }

    public void push(String item) {
        if (n == s.length) {
            resize(2 * s.length);
        }

        s[n++] = item;
    }

    public String pop() {
        String item = s[--n];
        s[n] = null;
        if (n > 0 && n == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    private void resize(int newSize) {
        String[] newS = new String[newSize];
        for (int i = 0; i < s.length; i++) {
            newS[i] = s[i];
        }

        s = newS;
    }

    public static void main(String[] args) {

    }
}
