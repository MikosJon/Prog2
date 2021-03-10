import java.io.*;

public class Predor {

    public static void main(String[] args) throws IOException {
        System.out.println(predor("podatki.txt", "krsitelji.txt"));
    }

    public static int predor(String inputFilename, String outputFilename) throws IOException {
        BufferedReader vhod = new BufferedReader(new FileReader(inputFilename));
        PrintWriter izhod = new PrintWriter(new FileWriter(outputFilename));

        int dolzina = 622; // v kilometrih
        int omejitev = 80; // v kilometrih na uro

        int stevilo = 0;

        while (vhod.ready()) {
            String vrstica = vhod.readLine().trim();
            if (vrstica.equals("")) continue;

            String[] besede = vrstica.split(" ");

            int s = Integer.parseInt(besede[0]);
            int t = Integer.parseInt(besede[1]);
            String registrskaTablica = besede[2];

            double cas = t - s;
            double povprecnaHitrost = 3.6 * (dolzina / cas);

            if (povprecnaHitrost > omejitev) {
                ++stevilo;
                izhod.println(registrskaTablica + String.format(" %.2f", povprecnaHitrost));
            }
        }

        vhod.close();
        izhod.close();

        return stevilo;
    }
}
