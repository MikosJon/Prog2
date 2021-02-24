public class Razcep {
    public static void main(String[] args) {
        razcep(1000000007);
        razcep(5761665);
        razcep(1024);
    }

    public static void razcep(int n) {
        char operator = '=';
        System.out.print(n);
        for (int d = 2; d * d <= n; ++d) {
            int e = 0;
            while (n % d == 0) {
                ++e;
                n /= d;
            }
            if (e > 0) {
                System.out.print(" " + operator + ' ' + d);
                if (e > 1) System.out.print("^" + e);
                operator = '*';
            }
        }
        if (n > 1) System.out.print(" " + operator + ' ' + n);
        System.out.println();
    }
}
