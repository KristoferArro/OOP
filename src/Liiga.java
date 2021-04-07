import java.util.ArrayList;
import java.util.List;

public class Liiga {
    private List<Klubi> klubid;
    private final String nimi;

    public Liiga(String nimi) {
        this.nimi = nimi;
        this.klubid = new ArrayList<>();
    }

    public List<Klubi> getKlubid() {
        return klubid;
    }

    public void lisaKlubi(Klubi klubi) {
        klubid.add(klubi);
    }
}
