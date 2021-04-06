import java.util.ArrayList;
import java.util.List;

public class Liiga {
    private List<Klubi> klubid;
    private final String nimi;
    private final String asukoht;

    public Liiga(List<Klubi> klubid, String nimi, String asukoht) {
        this.klubid = klubid;
        this.nimi = nimi;
        this.asukoht = asukoht;
    }
}
