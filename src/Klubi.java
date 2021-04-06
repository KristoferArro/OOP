import java.util.List;

public class Klubi {
    private final String nimi;
    private List<Mängija> mängijad;

    public Klubi(String nimi, List<Mängija> mängijad) {
        this.nimi = nimi;
        this.mängijad = mängijad;
    }
}
