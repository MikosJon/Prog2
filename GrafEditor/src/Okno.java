import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Okno extends JFrame implements ActionListener {

    protected Platno platno;

    private JMenuItem menuOdpri;
    private JMenuItem menuShrani;
    private JMenuItem menuKoncaj;

    private JMenuItem menuPrazen;
    private JMenuItem menuPoln;
    private JMenuItem menuCikel;
    private JMenuItem menuPolnDvodelen;

    private JMenuItem menuBarvaPovezave;
    private JMenuItem menuBarvaTocke;
    private JMenuItem menuBarvaAktivneTocke;
    private JMenuItem menuBarvaIzbraneTocke;
    private JMenuItem menuBarvaRoba;
    private JMenuItem menuDebelinaRoba;
    private JMenuItem menuDebelinaPovezave;


    public Okno() {
        super();
        setTitle("Urejevalnik grafov");
        platno = new Platno(800, 800);
        add(platno);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu menuDatoteka = dodajMenu(menubar, "Datoteka");
        JMenu menuGraf = dodajMenu(menubar, "Graf");
        JMenu menuNastavitve = dodajMenu(menubar, "Nastavitve");

        menuOdpri = dodajMenuItem(menuDatoteka, "Odpri ...");
        menuShrani = dodajMenuItem(menuDatoteka, "Shrani ...");
        menuKoncaj = dodajMenuItem(menuDatoteka, "Končaj");

        menuPrazen = dodajMenuItem(menuGraf, "Prazen");
        menuPoln = dodajMenuItem(menuGraf, "Poln");
        menuCikel = dodajMenuItem(menuGraf, "Cikel");
        menuPolnDvodelen = dodajMenuItem(menuGraf, "PolnDvodelen");

        menuBarvaPovezave = dodajMenuItem(menuNastavitve, "Barva povezave");
        menuBarvaTocke = dodajMenuItem(menuNastavitve, "Barva točke");
        menuBarvaAktivneTocke = dodajMenuItem(menuNastavitve, "Barva aktivne točke");
        menuBarvaIzbraneTocke = dodajMenuItem(menuNastavitve, "Barva izbrane točke");
        menuBarvaRoba = dodajMenuItem(menuNastavitve, "Barva roba");
        menuDebelinaRoba = dodajMenuItem(menuNastavitve, "Debelina roba");
        menuDebelinaPovezave = dodajMenuItem(menuNastavitve, "Debelina povezave");

    }

    public JMenu dodajMenu(JMenuBar menubar, String naslov) {
        JMenu menu = new JMenu(naslov);
        menubar.add(menu);
        return menu;
    }

    public JMenuItem dodajMenuItem(JMenu menu, String naslov) {
        JMenuItem menuitem = new JMenuItem(naslov);
        menu.add(menuitem);
        menuitem.addActionListener(this);
        return menuitem;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == menuOdpri) {
            JFileChooser dialog = new JFileChooser();
            int izbira = dialog.showOpenDialog(this);
            if (izbira == JFileChooser.APPROVE_OPTION) {
                String ime = dialog.getSelectedFile().getPath();
                Graf g = Graf.preberi(ime);
                platno.nastaviGraf(g);
            }
        }
        else if (source == menuShrani) {
            JFileChooser dialog = new JFileChooser();
            int izbira = dialog.showSaveDialog(this);
            if (izbira == JFileChooser.APPROVE_OPTION) {
                String ime = dialog.getSelectedFile().getPath();
                platno.graf.shrani(ime);
            }
        }
        else if (source == menuKoncaj) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if (source == menuPrazen) {
            String steviloTock = JOptionPane.showInputDialog(this, "Število točk:");
            if (steviloTock != null && steviloTock.matches("\\d+")) {
                Graf g = Graf.prazen(Integer.parseInt(steviloTock));
                g.razporedi(400, 400, 300);
                platno.nastaviGraf(g);
            }
        }
        else if (source == menuPoln) {
            String steviloTock = JOptionPane.showInputDialog(this, "Število točk:");
            if (steviloTock != null && steviloTock.matches("\\d+")) {
                Graf g = Graf.poln(Integer.parseInt(steviloTock));
                g.razporedi(400, 400, 300);
                platno.nastaviGraf(g);
            }
        }
        else if (source == menuCikel) {
            String steviloTock = JOptionPane.showInputDialog(this, "Število točk:");
            if (steviloTock != null && steviloTock.matches("\\d+")) {
                Graf g = Graf.cikel(Integer.parseInt(steviloTock));
                g.razporedi(400, 400, 300);
                platno.nastaviGraf(g);
            }
        }
        else if (source == menuPolnDvodelen) {
            JTextField nn = new JTextField();
            JTextField mm = new JTextField();
            Component[] polja = {
                    new JLabel("Vnesi N:"), nn,
                    new JLabel("Vnesi M:"), mm
            };
            int izbira = JOptionPane.showConfirmDialog(this, polja, "Input", JOptionPane.OK_CANCEL_OPTION);
            if (izbira == JOptionPane.OK_OPTION && nn.getText().matches("\\d+") && mm.getText().matches("\\d+")) {
                Graf g = Graf.polnDvodelen(Integer.parseInt(nn.getText()), Integer.parseInt(mm.getText()));
                g.razporedi(400, 400, 300);
                platno.nastaviGraf(g);
            }
        }
        else if (source == menuBarvaPovezave) {
            Color barva = JColorChooser.showDialog(this, "Izberi barvo povezav", platno.barvaPovezave);
            if (barva != null) {
                platno.barvaPovezave = barva;
                platno.repaint();
            }
        }
        else if (source == menuBarvaTocke) {
            Color barva = JColorChooser.showDialog(this, "Izberi barvo točk", platno.barvaTocke);
            if (barva != null) {
                platno.barvaTocke = barva;
                platno.repaint();
            }
        }
        else if (source == menuBarvaAktivneTocke) {
            Color barva = JColorChooser.showDialog(this, "Izberi barvo aktivne točke", platno.barvaAktivneTocke);
            if (barva != null) {
                platno.barvaAktivneTocke = barva;
                platno.repaint();
            }
        }
        else if (source == menuBarvaIzbraneTocke) {
            Color barva = JColorChooser.showDialog(this, "Izberi barvo izbranih točk", platno.barvaIzbranihTock);
            if (barva != null) {
                platno.barvaIzbranihTock = barva;
                platno.repaint();
            }
        }
        else if (source == menuBarvaRoba) {
            Color barva = JColorChooser.showDialog(this, "Izberi barvo roba", platno.barvaRoba);
            if (barva != null) {
                platno.barvaRoba = barva;
                platno.repaint();
            }
        }
        else if (source == menuDebelinaPovezave) {
            String debelina = JOptionPane.showInputDialog(this, "Debelina povezav:");
            if (debelina != null && debelina.matches("\\d+")) {
                platno.debelinaPovezave = Integer.parseInt(debelina);
                platno.repaint();
            }
        }
        else if (source == menuDebelinaRoba) {
            String debelina = JOptionPane.showInputDialog(this, "Debelina roba:");
            if (debelina != null && debelina.matches("\\d+")) {
                platno.debelinaRoba = Integer.parseInt(debelina);
                platno.repaint();
            }
        }
    }


}
