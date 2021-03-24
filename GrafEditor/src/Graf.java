import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class Graf {

    private int stevec;
    protected Map<String, Tocka> tocke;

    public Graf() {
        stevec = 0;
        tocke = new HashMap<String, Tocka>();
    }

    public Tocka tocka(String ime) {
        return tocke.get(ime);
    }

    public boolean povezava(Tocka prva, Tocka druga) {
        return prva.sosedi.contains(druga);
    }

    public Tocka dodajTocko(String ime) {
        Tocka v = tocka(ime);
        if (v == null) {
            v = new Tocka(ime);
            tocke.put(ime, v);
        }
        return v;
    }

    public Tocka dodajTocko() {
        while (true) {
            String ime = Integer.toString(++stevec);
            if (tocka(ime) != null) continue;
            return dodajTocko(ime);
        }
    }

    public void dodajPovezavo(Tocka u, Tocka v) {
        if (v == u) return;
        v.sosedi.add(u);
        u.sosedi.add(v);
    }

    public void odstraniPovezavo(Tocka u, Tocka v) {
        v.sosedi.remove(u);
        u.sosedi.remove(v);
    }

    public void odstraniTocko(Tocka u) {
        for (Tocka v : u.sosedi) {
            v.sosedi.remove(u);
        }
        tocke.remove(u.ime);
    }

    private Tocka[] dodajTocke(int n) {
        Tocka[] tocke = new Tocka[n];
        for (int i = 0; i < n; ++i) {
            tocke[i] = dodajTocko();
        }
        return tocke;
    }

    public static Graf prazen(int n) {
        Graf g = new Graf();
        g.dodajTocke(n);
        return g;
    }

    public static Graf cikel(int n) {
        Graf g = new Graf();
        Tocka[] tocke = g.dodajTocke(n);
        for (int i = 0; i < n; ++i) {
            int naslednja = (i + 1) % n;
            g.dodajPovezavo(tocke[i], tocke[naslednja]);
        }
        return g;
    }

    public static Graf poln(int n) {
        Graf g = new Graf();
        Tocka[] tocke = g.dodajTocke(n);
        for (Tocka u : tocke) {
            for (Tocka v : tocke) {
                g.dodajPovezavo(u, v);
            }
        }
        return g;
    }

    public static Graf polnDvodelen(int n, int m) {
        Graf g = new Graf();
        Tocka[] tocke = g.dodajTocke(n + m);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                g.dodajPovezavo(tocke[i], tocke[n + j]);
            }
        }
        return g;
    }

    public void izpis() {
        String out = "";
        for (Tocka tocka : tocke.values()) {
            System.out.print(tocka + ":");
            for (Tocka u : tocka.sosedi) {
                System.out.print(" " + u);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int steviloKomponent() {
        HashSet<String> videne = new HashSet<String>();

        int stevilo = 0;

        for (Tocka v : tocke.values()) {
            if (videne.contains(v.ime)) continue;
            ++stevilo;

            videne.add(v.ime);
            LinkedList<Tocka> q = new LinkedList<Tocka>(v.sosedi);
            while (q.size() != 0) {
                Tocka trenutna = q.remove();
                if (videne.contains(trenutna.ime)) continue;
                videne.add(trenutna.ime);
                q.addAll(trenutna.sosedi);
            }
        }

        return stevilo;
    }

    public boolean povezan() {
        return steviloKomponent() == 1;
    }

    public void razporedi(double x, double y, double r) {
        int n = tocke.size();
        int i = 0;
        for (Tocka v : tocke.values()) {
            v.x = x + r * Math.cos(2 * i * Math.PI / n);
            v.y = y + r * Math.sin(2 * i * Math.PI / n);
            ++i;
        }
    }

    public void shrani(String ime){
        try {
            PrintWriter izhod = new PrintWriter(new FileWriter(ime));

            for (Tocka v : tocke.values()) {
                String out = v.ime + ": " + String.format("%.2f", v.x) + " " + String.format("%.2f", v.y);
                izhod.println(out);
            }
            izhod.println("***");

            for (Tocka v : tocke.values()) {
                String out = v.ime + ":";
                for (Tocka u : v.sosedi) {
                    out += " " + u.ime;
                }
                izhod.println(out);
            }
            izhod.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Graf preberi(String ime) {
        Graf g = new Graf();
        try {
            BufferedReader vhod = new BufferedReader(new FileReader(ime));

            boolean tocke = true;

            while (vhod.ready()) {
                String vrstica = vhod.readLine().trim();
                System.out.println(vrstica);
                if (vrstica.equals("")) continue;

                if (tocke) {
                    if (vrstica.equals("***")) {
                        tocke = false;
                        continue;
                    }
                    String[] razcljenjenaVrstica = vrstica.split(" ");
                    String temp = razcljenjenaVrstica[0];
                    String tockaIme;
                    if (temp.length() == 0) {
                        tockaIme = temp;
                    } else {
                        tockaIme = temp.substring(0, temp.length() - 1);
                    }

                    Tocka v = g.dodajTocko(tockaIme);
                    v.x = Double.parseDouble(razcljenjenaVrstica[1]);
                    v.y = Double.parseDouble(razcljenjenaVrstica[2]);
                }
                else {
                    String[] razcljenjenaVrstica = vrstica.split(" ");
                    String temp = razcljenjenaVrstica[0];
                    String tockaIme = temp.substring(0, temp.length() - 1);

                    Tocka zacetnaTocka = g.tocke.get(tockaIme);

                    for (int i = 1; i < razcljenjenaVrstica.length; ++i) {
                        String trenutnoIme = razcljenjenaVrstica[i];
                        Tocka trenutnaTocka = g.tocke.get(trenutnoIme);

                        g.dodajPovezavo(zacetnaTocka, trenutnaTocka);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }


}
