import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Platno extends JPanel {

    protected Graf graf;
    protected Color barvaPovezave;
    protected Color barvaTocke;
    protected Color barvaRoba;

    protected int debelinaPovezave;
    protected int debelinaRoba;
    protected int polmer;

    public Platno(int sirina, int visina) {
        super();
        graf = null;
        setPreferredSize(new Dimension(sirina, visina));

        barvaPovezave = Color.black;
        barvaTocke = Color.pink;
        barvaRoba = Color.red;

        debelinaPovezave = 3;
        debelinaRoba = 5;
        polmer = 25;
    }

    public void nastaviGraf(Graf g) {
        graf = g;
        repaint();
    }

    private static int round(double s) {return (int)(s + 0.5);}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (graf == null) return;

        Graphics2D g2d = (Graphics2D) g;

        for (Tocka v : graf.tocke.values()) {
            g2d.setColor(barvaPovezave);
            g2d.setStroke(new BasicStroke(debelinaPovezave));

            int vx = Platno.round(v.x);
            int vy = Platno.round(v.y);

            for (Tocka u : v.sosedi) {
                int ux = Platno.round(u.x);
                int uy = Platno.round(u.y);
                if (v.ime.compareTo(u.ime) > 0) g2d.drawLine(vx, vy, ux, uy);
            }
        }

        for (Tocka v : graf.tocke.values()) {
            int vx = Platno.round(v.x);
            int vy = Platno.round(v.y);

            g2d.setColor(barvaTocke);
            g2d.fillOval(vx - polmer, vy - polmer, 2*polmer, 2*polmer);

            g2d.setColor(barvaRoba);
            g2d.setStroke(new BasicStroke(debelinaRoba));
            g2d.drawOval(vx - polmer, vy - polmer, 2*polmer, 2*polmer);
        }
    }
}
