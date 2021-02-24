public class Collatz {
    public static void main(String[] args) {
        System.out.println(collatzLength(60));
        System.out.println(maxElement(78345));
        elements(98347);
    }

    public static int nextElement(int n) {
        if (n % 2 == 0) {
            return n / 2;
        }
        return 3 * n + 1;
    }

    public static int collatzLength(int n) {
        int length = 1;
        while (n > 1) {
            n = nextElement(n);
            length++;
        }
        return length;
    }

    public static int maxElement(int n) {
        int maxSeen = n;
        while (n > 1) {
            n = nextElement(n);
            if (n > maxSeen) maxSeen = n;
        }
        return maxSeen;
    }

    public static void elements(int n) {
        System.out.print(n);
        while (n > 1) {
            n = nextElement(n);
            System.out.print(" " + n);
        }
        System.out.println();
    }
}