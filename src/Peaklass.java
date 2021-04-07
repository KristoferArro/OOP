import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Peaklass {
    // Küsib kasutajalt mida teha? (Vaata mängijate infot/ vaata liiga infot)
    // Mängijate info valiku korral annab valiku klubidest ja seejärel mängijatest ning kuvab vastava info
    // Liiga info vaatamisel annab valiku vaadata parimaid väravakütte / kõige rohkem väravasööte andnud mängijaid
    // Peale igat päringut läheb uuesti algusesse seni kuni kasutaja kirjutab "lõpeta"

    // Peaklassis tuleb luua:
    // Mängijate isendid
    // Klubide isendid (sisaldab endas listi mängijate isenditest, kes seal klubis mängivad)
    // Liiga isend (sisaldab listi klubide isenditest)

    public static List<Mängija> loeFail(String failinimi) throws Exception { //Loeb failist mängijate andmed
        List<Mängija> mängijad = new ArrayList<>();
        File objekt = new File(failinimi);
        Scanner lugeja = new Scanner(objekt);
        while (lugeja.hasNextLine()) {
            String rida = lugeja.nextLine();
            String[] andmed = rida.split(", ");
            Mängija mängija = new Mängija(andmed[0], Integer.parseInt(andmed[1]), andmed[2], andmed[3], Integer.parseInt(andmed[4]), Integer.parseInt(andmed[5])); //Loob uue mängija
            mängijad.add(mängija); //Lisab mängija mängijate järjendisse
        }
        return mängijad;
    }
    //Mängijate järjendi põhjal saab luua klubid

    // Võrdleme iga mängija klubi liigas olevate klubidega, seejärel paneme mängija õige klubi alla
    public static void loeKlubisse(List<Mängija> mängijad, List<Klubi> klubid) {
        for (Mängija mängija : mängijad) {
            String mängijaKlubi = mängija.getKlubi().toLowerCase(Locale.ROOT);
            for (Klubi klubi : klubid) {
                String klubiNimi = klubi.getNimi().toLowerCase(Locale.ROOT);
                if (mängijaKlubi.equals(klubiNimi))
                    klubi.lisaMängija(mängija);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // Liiga isend
        Liiga premiumLiiga = new Liiga("Premium liiga");

        // Klubide isendid
        Klubi flora = new TallinnaKlubi("FC Flora", "Tallinn", "Kristiine");
        Klubi paide = new MuuKlubi("Paide linnameeskond", "Paide");
        Klubi levadia = new TallinnaKlubi("FCI Levadia", "Tallinn", "Pirita");
        Klubi kalju = new TallinnaKlubi("Nõmme Kalju FC", "Tallinn", "Nõmme");
        Klubi tammeka = new MuuKlubi("JK Tammeka", "Tartu");
        Klubi tulevik = new MuuKlubi("Viljandi JK Tulevik", "Viljandi");
        Klubi legion = new TallinnaKlubi("JK Legion", "Tallinn", "Lasnamäe");
        Klubi trans = new MuuKlubi("JK Narva Trans", "Narva");
        Klubi kure = new MuuKlubi("FC Kuressaare", "Kuressaare");
        Klubi kalev = new TallinnaKlubi("JK Tallinna Kalev", "Tallinn", "Kesklinn");

        // Loeme mängijad failist
        List<Mängija> mängijad = loeFail("mängijad.txt");

        // Loeme klubid liigasse
        premiumLiiga.lisaKlubi(flora);
        premiumLiiga.lisaKlubi(paide);
        premiumLiiga.lisaKlubi(levadia);
        premiumLiiga.lisaKlubi(kalju);
        premiumLiiga.lisaKlubi(tammeka);
        premiumLiiga.lisaKlubi(tulevik);
        premiumLiiga.lisaKlubi(legion);
        premiumLiiga.lisaKlubi(trans);
        premiumLiiga.lisaKlubi(kure);
        premiumLiiga.lisaKlubi(kalev);

        // Jagame mängijad klubidesse
        loeKlubisse(mängijad, premiumLiiga.getKlubid());

        System.out.println(tammeka.getMängijad());
        System.out.println(flora.getMängijad());

    }
}
