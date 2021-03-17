public class Test {

    public static void main(String[] args) {
        Graf g = Graf.polnDvodelen(5, 3);
        g.razporedi(400, 400, 300); // hardcoded vrednosti, ker je okno 800x800

        Okno okno = new Okno();
        okno.pack();
        okno.setVisible(true);

        okno.platno.nastaviGraf(g);
    }
}
