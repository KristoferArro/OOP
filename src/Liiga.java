import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public static void väravalööjad(List<Mängija> mängijad) { //Peab sorteerima kõik premium liiga mängijad väravate põhjal ja väljastama top 10;
        Collections.sort(mängijad, new Comparator<Mängija>() {
            @Override
            public int compare(Mängija nr1, Mängija nr2) {
                return nr1.getVäravad() - nr2.getVäravad();
            }
        }.reversed());
        System.out.println("TOP 10 kõige rohkem väravaid löönud mängijad: ");
        for (int i = 0; i < 10; i++) {
            Mängija mängija = mängijad.get(i);
            System.out.println(i + 1 + ". " + mängija.getNimi() + ", väravaid: " + mängija.getVäravad());
        }
    }

    public static void jooksjad(List<Mängija> mängijad) { //Peab sorteerima kõik Premium liiga mängijad jooksudistantsi põhjal ja väljastama top10;
        Collections.sort(mängijad, new Comparator<Mängija>() {
            @Override
            public int compare(Mängija nr1, Mängija nr2) {
                return (int)nr1.getJooks() - (int)nr2.getJooks();
            }
        }.reversed());
        System.out.println("TOP 10 kõige rohkem jooksnud mängijad (keskmiselt ühe mängu kohta): ");
        for (int i = 0; i < 10; i++) {
            Mängija mängija = mängijad.get(i);
            System.out.println(i + 1 + ". " + mängija.getNimi() + ", keskmiselt joostud km: " + mängija.getJooks());
        }
    }
}
