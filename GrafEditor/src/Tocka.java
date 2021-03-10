import java.util.HashSet;
import java.util.Set;

public class Tocka {

    protected String ime;
    protected Set<Tocka> sosedi;
    protected double x, y;

    public Tocka(String ime) {
        this.ime = ime;
        sosedi = new HashSet<Tocka>();
        x = y = 0;
    }

    public int stopnja() {
        return sosedi.size();
    }

    @Override
    public String toString() {
        return ime;
    }
}
