import java.io.*;

public class SteviloBesed {
    public static void main(String[] args) throws IOException {
        System.out.println(steviloBesed("vhod.txt", "izhod.txt"));
    }

    public static int steviloBesed(String inputFilename, String outputFilename) throws IOException {
        BufferedReader vhod = new BufferedReader(new FileReader(inputFilename));
        PrintWriter izhod = new PrintWriter(new FileWriter(outputFilename));

        int stevec = 0;

        while (vhod.ready()) {
            String vrstica = vhod.readLine().trim();
            if (vrstica.equals("")) continue;

            String[] besede = vrstica.split(" +");
            stevec += besede.length;

            for (String beseda : besede) {
                izhod.println(beseda);
            }
        }

        vhod.close();
        izhod.close();
        return stevec;
    }
}
