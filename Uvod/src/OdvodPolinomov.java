public class OdvodPolinomov {
    public static void main(String[] args) {
        double[] p1 = {1, 2, 3};
        double[] p2 = {4, -1, 2, 0, 1};
        double[] p3 = {1};

        double[] op1 = odvod(p1);
        double[] op2 = odvod(p2, 2);
        double[] op3 = odvod(p3);

        izpis(op1);
        izpis(op2);
        izpis(op3);
    }

    public static void izpis(double[] polinom) {
        System.out.print('{');
        for (int i = 0; i < polinom.length; ++i) {
            if (i > 0) System.out.print(", ");
            System.out.print(polinom[i]);
        }
        System.out.println("}");
    }

    public static double[] odvod(double[] polinom) {
        return odvod(polinom, 1);
    }

    public static double[] odvod(double[] polinom, int n) {
        if (polinom.length < n) return new double[0];
        double[] op = new double[polinom.length - n];

        int faktor = 1;
        for (int i = 2; i <= n; ++i) {
            faktor *= i;
        }

        for (int i = n; i < polinom.length; ++i) {
            op[i - n] = polinom[i] * faktor;
            faktor /= i - n + 1;
            faktor *= i + 1;
        }
        return op;
    }
}
