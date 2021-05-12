import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Peaklass {
    // Küsib kasutajalt mida teha? (Vaata mängijate infot/ vaata liiga infot)
    // Mängijate info valiku korral annab valiku klubidest ja seejärel mängijatest ning kuvab vastava info
    // Liiga info vaatamisel annab valiku vaadata parimaid väravakütte / kõige rohkem väravasööte andnud mängijaid
    // Peale igat päringut läheb uuesti algusesse seni kuni kasutaja kirjutab "stopp"

    // Peaklassis tuleb luua:
    // Mängijate isendid
    // Klubide isendid (sisaldab endas listi mängijate isenditest, kes seal klubis mängivad)
    // Liiga isend (sisaldab listi klubide isenditest)

    // Liiga isend ja kõikide mängijate list (asuvad peaklassi alguses, et ei peaks igas meetodis uuesti ette andma)
    private static final Liiga premiumLiiga = new Liiga("Premium Liiga");
    private static final List<Mängija> mängijad = new ArrayList<>();

    public static void loeFail(String failinimi) { //Loeb failist mängijate andmed
        try (Scanner lugeja = new Scanner(new File (failinimi))) {
            while (lugeja.hasNextLine()) {
                String rida = lugeja.nextLine();
                String[] andmed = rida.split(", ");
                Mängija mängija = new Mängija(andmed[0], Integer.parseInt(andmed[1]), andmed[2], andmed[3], Integer.parseInt(andmed[4]), Integer.parseInt(andmed[5])); //Loob uue mängija
                mängijad.add(mängija); //Lisab mängija mängijate järjendisse
            }
        } catch (FileNotFoundException e) {
            System.out.println("Mängijate faili ei leitud! Kontakteeru programmi tootjatega! LÕPETAN TÖÖ!");
            System.exit(0);
        }
    }

    //Mängijate järjendi põhjal saab täita klubid õigete mängijatega
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

    public static void teeLiigadKlubid() {
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
        loeFail("mängijad.txt");

        // Lisame klubid liigasse
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
        loeKlubisse(mängijad, Liiga.getKlubid());

    }

    public static void main(String[] args) {
        //Peaklassis toimub kasutajaga suhtlus
        // Jooksutame meetodi, mis teeb valmis vajalikud klubid, lisab neisse õiged mängijad ja lisab klubid omakorda liigasse
        teeLiigadKlubid();
        while (true) {
            Scanner küsimus1 = new Scanner(System.in);
            System.out.println("Mille kohta infot soovite? (Liiga (L)/Klubi (K)/Mängija (M)) Kui soovite programmi töö lõpetada, kirjutage stopp ");
            String vastus1 = küsimus1.nextLine().toLowerCase();
            switch (vastus1) {
                case "liiga", "l" -> liigaKüsimus();
                //Esitab liiga kohta küsimused
                case "klubi", "k"-> klubiKüsimus();
                //Esitab klubi kohta küsimuse
                case "mängija", "m" -> mängijaKüsimus();
                //Esitab mängija kohta küsimuse
                case "stop" -> System.exit(0);
                //Lõpetab töö
                case "stopp" -> System.exit(0);
                //lõpetab töö
                default -> System.out.println("Vigane sisend! Proovi uuesti!");
                //Teavitab kasutajat vigasest sisendist ja laseb uuesti proovida
            }
        }
    }

    //Abimeetod, käivitatakse, kui kasutaja soovib infot mängijate kohta
    private static void mängijaKüsimus() {
        Scanner küsimus2 = new Scanner(System.in);
        String vastus2;
        while (true) {
            Set<String> vastused = Set.of("k","j", "s", "va", "vä");
            System.out.println("Mida mängija kohta teada soovid? Klubi(K)/Keskmiselt mängus joostud km(J)/Särginumber(S)/Vanus(Va)/Väravad(Vä)");
            vastus2 = küsimus2.nextLine().toLowerCase();
            if (vastus2.equals("tagasi"))
                return;
            else if (vastused.contains(vastus2))
                break;
            else
                System.out.println("Vigane sisend! Proovi uuesti! Kui tahad tagasi minna, kirjuta \"tagasi\"");
        }
        Scanner küsimus3 = new Scanner(System.in);
        System.out.println("Sisesta mängija täisnimi, kelle kohta infot soovid (nt: Mihkel Järviste): ");
        String mängijanimi = küsimus3.nextLine().toLowerCase();
        for (Mängija x : mängijad) { //Võrdleb etteantud mängijanime meie mängijate listiga
            if (x.getNimi().equalsIgnoreCase(mängijanimi)) {
                switch (vastus2) {
                    case "k":
                        System.out.println(x.getKlubi());
                        return;
                    case "j":
                        System.out.println(x.getJooks());
                        return;
                    case "s":
                        System.out.println(x.getSärk());
                        return;
                    case "va":
                        System.out.println(x.getVanus());
                        return;
                    case "vä":
                        System.out.println(x.getVäravad());
                        return;
                    default:
                        return;
                } //switch
            } //if
        }
        System.out.println("Sellist mängijat ei leitud, liigun tagasi algusesse!");
    }

    //Abimeetod, käivitatakse, kui kasutaja soovib infot klubide kohta
    private static void klubiKüsimus() {
        Scanner küsimus2 = new Scanner(System.in);
        String vastus2;
        while (true) {
            Set<String> vastused = Set.of("asukoht", "mängijad", "mängijate arv");
            System.out.println("Mida klubide kohta teada soovid? Asukoht/Mängijad/Mängijate arv ");
            vastus2 = küsimus2.nextLine().toLowerCase();
            if (vastus2.equals("tagasi"))
                return;
            else if (vastused.contains(vastus2))
                break;
            else
                System.out.println("Vigane sisend! Proovi uuesti! Kui tahad tagasi minna, kirjuta \"tagasi\"");
        }

        Scanner küsimus3 = new Scanner(System.in);
        System.out.println("Sisesta klubi nimi, mille kohta infot soovid (FC Flora, Paide Linnameeskond, FCI Levadia, JK Tammeka, Nõmme Kalju FC, Viljandi JK Tulevik, JK Legion, JK Narva Trans, FC Kuressaare, JK Tallinna Kalev) ");
        String klubinimi = küsimus3.nextLine().toLowerCase();
        for (Klubi x : Liiga.getKlubid()) { //Võrdleb etteantud nime klubidega ning väärtustab selle
            if (x.getNimi().equalsIgnoreCase(klubinimi)) {
                switch (vastus2) {
                    case "asukoht":
                        System.out.println(x.getAsukoht());
                        return;
                    case "mängijad":
                        x.getMängijanimed();
                        return;
                    case "mängijate arv":
                        System.out.println(x.getMängijad().size());
                        return;
                    default:
                        return;
                }
            }
        }
        System.out.println("Sellist klubi ei leitud, liigun tagasi algusesse!");
    }

    //Abimeetod, käivitatakse, kui kasutaja soovib infot liiga kohta
    private static void liigaKüsimus () {
        Scanner küsimus2 = new Scanner(System.in);
        String vastus2;
        Set<String> vastused = Set.of("v", "j", "m", "väravalööjad", "meeskonnad");
        while (true) {
            System.out.println("Mida liiga kohta teada soovite? Väravalööjad (V)/Kõige rohkem jooksnud mängijad (J)/ Meeskonnad (M)");
            vastus2 = küsimus2.nextLine().toLowerCase();
            if (vastus2.equals("tagasi"))
                return;
            else if (vastused.contains(vastus2))
                break;
            else
                System.out.println("Vigane sisend! Proovi uuesti! Kui tahad tagasi minna, kirjuta \"tagasi\"");
        }
        switch (vastus2) {
            case "v", "väravalööjad":
                Liiga.väravalööjad(mängijad);
                break;
            case "j":
                Liiga.jooksjad(mängijad);
                break;
            case "m", "meeskonnad":
                System.out.println(Liiga.getKlubid());
                break;
            default:
                break;
        }
    }
}