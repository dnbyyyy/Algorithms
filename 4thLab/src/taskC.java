import java.io.*;

public class taskC {

    private static class Stack {

        private int tail = 0;

        private char[] data = new char[256];

        private void reallocate() {
            char[] buf = data;
            data = new char[2 * data.length];
            System.arraycopy(buf, 0, data, 0, buf.length);
        }

        private void push(char n){
            if (tail + 1 >= data.length) {
                reallocate();
            }
            data[tail] = n;
            tail++;
        }

        private int pop() {
            int deleted = data[tail - 1];
            tail--;
            return deleted;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("brackets.in"));
        FileWriter writer = new FileWriter("brackets.out");
        while (true) {
            try {
                int cnt = 0;
                String brackets = reader.readLine();
                Stack opBrackets = new Stack();
                for (int i = 0; i < brackets.length(); i++) {
                    if (brackets.charAt(0) == ')' || brackets.charAt(0) == ']'){
                        writer.write("NO\n");
                        cnt++;
                        break;
                    }
                    if (brackets.charAt(i) == '(' || brackets.charAt(i) == '['){
                        opBrackets.push(brackets.charAt(i));
                    }
                    if (brackets.charAt(i) == ')' || brackets.charAt(i) == ']'){
                        if (brackets.charAt(i) == ')') {

                        }
                    }
                }
            } catch (EOFException e){
                break;
            }
        }
    }
}
