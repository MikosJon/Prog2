import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    protected Graf graf;

    protected Tocka aktivnaTocka;
    protected Set<Tocka> izbraneTocke;

    protected Color barvaAktivneTocke;
    protected Color barvaIzbranihTock;
    protected Color barvaPovezave;
    protected Color barvaTocke;
    protected Color barvaRoba;

    protected int debelinaPovezave;
    protected int debelinaRoba;
    protected int polmer;

    private int starX;
    private int starY;
    private int klikX;
    private int klikY;

    public Platno(int sirina, int visina) {
        super();
        graf = null;
        setPreferredSize(new Dimension(sirina, visina));

        aktivnaTocka = null;
        izbraneTocke = new HashSet<Tocka>();

        barvaAktivneTocke = Color.red;
        barvaIzbranihTock = Color.yellow;
        barvaPovezave = Color.black;
        barvaTocke = Color.gray;
        barvaRoba = Color.darkGray;

        debelinaPovezave = 3;
        debelinaRoba = 2;
        polmer = 10;

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    public void nastaviGraf(Graf g) {
        graf = g;
        aktivnaTocka = null;
        izbraneTocke.clear();
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

        g2d.setStroke(new BasicStroke(debelinaRoba));
        for (Tocka v : graf.tocke.values()) {
            int vx = Platno.round(v.x);
            int vy = Platno.round(v.y);

            if (v == aktivnaTocka) g2d.setColor(barvaAktivneTocke);
            else if (izbraneTocke.contains(v)) g2d.setColor(barvaIzbranihTock);
            else g2d.setColor(barvaTocke);

            g2d.fillOval(vx - polmer, vy - polmer, 2*polmer, 2*polmer);

            g2d.setColor(barvaRoba);
            g2d.drawOval(vx - polmer, vy - polmer, 2*polmer, 2*polmer);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int x = starX = klikX = mouseEvent.getX();
        int y = starY = klikY = mouseEvent.getY();

        double najmanjsaRazdalja = 0.0;
        Tocka najblizjaTocka = null;

        for (Tocka v : graf.tocke.values()) {
            double razdaljaKvadrat = (v.x - x)*(v.x - x) + (v.y - y)*(v.y - y);

            if (najblizjaTocka == null || razdaljaKvadrat < najmanjsaRazdalja) {
                najblizjaTocka = v;
                najmanjsaRazdalja = razdaljaKvadrat;
            }
        }

        if (najmanjsaRazdalja < (polmer + 10)*(polmer + 10)) {
            aktivnaTocka = najblizjaTocka;
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        int trenutniX = mouseEvent.getX();
        int trenutniY = mouseEvent.getY();

        if (trenutniX == klikX && trenutniY == klikY) {
            if (aktivnaTocka == null) {
                Tocka nova = graf.dodajTocko();
                nova.x = trenutniX;
                nova.y = trenutniY;

                for (Tocka v : graf.tocke.values()) {
                    graf.dodajPovezavo(nova, v);
                }
            }
            else {
                if (izbraneTocke.contains(aktivnaTocka)) izbraneTocke.remove(aktivnaTocka);
                else izbraneTocke.add(aktivnaTocka);
            }
        }

        aktivnaTocka = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        int trenutniX = mouseEvent.getX();
        int trenutniY = mouseEvent.getY();

        int dx = trenutniX - starX;
        int dy = trenutniY - starY;

        if (aktivnaTocka != null) {
            aktivnaTocka.x += dx;
            aktivnaTocka.y += dy;
        }
        else {
            for (Tocka v : izbraneTocke) {
                v.x += dx;
                v.y += dy;
            }
        }
        starX = trenutniX;
        starY = trenutniY;

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        char tipka = keyEvent.getKeyChar();
        if (tipka == 'a') {
            izbraneTocke.addAll(graf.tocke.values());
        }
        else if (tipka == 'b') {
            izbraneTocke.clear();
        }
        else if (tipka == 'c') {
            for (Tocka v : izbraneTocke) {
                for (Tocka u : izbraneTocke) {
                    graf.dodajPovezavo(v, u);
                }
            }
        }
        else if (tipka == 'd') {
            for (Tocka v : izbraneTocke) {
                for (Tocka u : izbraneTocke) {
                    graf.odstraniPovezavo(v, u);
                }
            }
        }
        else if (tipka == 'e') {
            for (Tocka v : izbraneTocke) {
                graf.odstraniTocko(v);
            }
            izbraneTocke.clear();
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {}

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {}

    @Override
    public void keyReleased(KeyEvent keyEvent) {}
}
