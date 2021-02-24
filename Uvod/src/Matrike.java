public class Matrike {
    public static void main(String[] args) {
        double[][] table = {{1.2, 34.12}, {213.3, 23.4}, {1.1, 1.1}, {2.1, 12093.1}};
        printTable(table);
        printTable(transpose(table));
    }

    public static void printTable(double[][] table) {
        if (table == null) {
            System.out.println("{}");
            return;
        }
        System.out.println("{");

        for (int i = 0; i < table.length; ++i) {
            String out = "  {";

            if (table[i] != null) {
                double[] row = table[i];
                for (int j = 0; j < row.length; ++j) {
                    double v = row[j];
                    out += Double.toString(v);
                    if (j != row.length - 1) {
                        out += ", ";
                    }
                }
            }
            out += "}";

            if (i != table.length - 1) {
                out += ",";
            }
            System.out.println(out);
        }
        System.out.println("}");
    }

    public static boolean isMatrix(double[][] table) {
        if (table == null || table.length == 0) return false;

        int dolzinaVrstice = table[0].length;
        for (int i = 0; i < table.length; ++i) {
            if (table[i] == null || table[i].length != dolzinaVrstice) return false;
        }
        return true;
    }

    public static double[][] transpose(double[][] matrix) {
        if (!isMatrix(matrix)) return null;

        int nRows = matrix.length;
        int nCols = matrix[0].length;

        double[][] transposed = new double[nCols][nRows];

        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }
}
