public class Zaporedje {

    public static void main(String[] args) {
        final int N = 20;
        String[] sequence = zaporedje(N);
        for (int i = 0; i < N; ++i) {
            System.out.println(sequence[i]);
        }
    }

    public static String next(String s) {

        String out = "";
        char startNum = s.charAt(0);
        int startIndex = 0;

        for (int j = 0; j < s.length(); ++j) {
            char c = s.charAt(j);

            if (c != startNum) {
                int length = j - startIndex;
                out += Integer.toString(length);
                out += startNum;

                startNum = c;
                startIndex = j;
            }
        }
        out += Integer.toString(s.length() - startIndex);
        out += startNum;

        return out;
    }

    public static String[] zaporedje(int n) {
        String last = "1";
        String[] out = new String[n];

        out[0] = last;

        for (int i = 1; i < n; ++i) {
            String nextNum = next(last);
            out[i] = nextNum;
            last = nextNum;
        }

        return out;
    }
}