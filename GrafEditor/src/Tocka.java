import java.util.HashSet;
import java.util.Set;

public class Tocka {

    protected String ime;
    protected Set<Tocka> sosedi;

    public Tocka(String ime) {
        this.ime = ime;
        sosedi = new HashSet<Tocka>();
    }

    public int stopnja() {
        return sosedi.size();
    }

    @Override
    public String toString() {
        return ime;
    }
}
