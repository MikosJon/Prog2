import java.util.HashMap;
import java.util.HashSet;
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

    public boolean povezan() {
        HashSet<Tocka> videne = new HashSet<Tocka>();

        return tocke.size() == 0;
    }
}
