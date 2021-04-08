import java.util.ArrayList;
import java.util.List;

public class Liiga {
    private static List<Klubi> klubid;
    private final String nimi;

    public Liiga(String nimi) {
        this.nimi = nimi;
        this.klubid = new ArrayList<>();
    }

    public static List<Klubi> getKlubid() {
        return klubid;
    }

    public void lisaKlubi(Klubi klubi) {
        klubid.add(klubi);
    }

    public static void väravalööjad(){ //Peab sorteerima kõik premium liiga mängijad väravate põhjal ja väljastama top 10;
        System.out.println("värav");
    }

    public static void jooksjad(){ //Peab sorteerima kõik Premium liiga mängijad jooksudistantsi põhjal ja väljastama top10;
        System.out.println("jooks");
    }
}
