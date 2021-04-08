import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Peaklass {
    // Küsib kasutajalt mida teha? (Vaata mängijate infot/ vaata liiga infot)
    // Mängijate info valiku korral annab valiku klubidest ja seejärel mängijatest ning kuvab vastava info
    // Liiga info vaatamisel annab valiku vaadata parimaid väravakütte / kõige rohkem väravasööte andnud mängijaid
    // Peale igat päringut läheb uuesti algusesse seni kuni kasutaja kirjutab "stopp"

    // Peaklassis tuleb luua:
    // Mängijate isendid
    // Klubide isendid (sisaldab endas listi mängijate isenditest, kes seal klubis mängivad)
    // Liiga isend (sisaldab listi klubide isenditest)

    // Liiga isend
    private static Liiga premiumLiiga;
    private static List<Mängija> mängijad = new ArrayList<>();

    public static List<Mängija> loeFail(String failinimi) throws Exception { //Loeb failist mängijate andmed
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

    public static void teeLiigadKlubid() throws Exception {


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

        //Loome Premium Liiga
        Liiga premiumLiiga = new Liiga("Premium liiga");

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

    }

    public static void main(String[] args) throws Exception {
        //Peaklassis toimud kasutajaga suhtlus
        teeLiigadKlubid();
        Scanner küsimus1 = new Scanner(System.in);
        System.out.println("Mille kohta infot soovite? (Liiga/Klubi/Mängija) Kui soovite programmi töö igal hetkel lõpetada, kirjutage stopp ");
        String vastus1 = küsimus1.nextLine().toLowerCase();
        switch (vastus1) {
            case "liiga":
                liigaKüsimus();
                break;
                //Esitab liiga kohta küsimused
            case "klubi":
                klubiKüsimus();
                break;
                //Esitab klubi kohta küsimuse
            case "mängija":
                mängijaKüsimus();
                break;
                //Esitab mängija kohta küsimuse
            case "stop":
                break; //Lõpetab töö
            case "stopp":
                break; //lõpetab töö
            default:
                break;
        }
    }

    //Abimeetod, käivitatakse, kui kasutaja soovid infot mängijate kohta
    private static void mängijaKüsimus() {
        Scanner küsimus2 = new Scanner(System.in);
        System.out.println("Mida mängija kohta teada soovid? Klubi/Jooks/Särginumber/Vanus/Väravad");
        String vastus2 = küsimus2.nextLine().toLowerCase();
        Scanner küsimus3 = new Scanner(System.in);
        System.out.println("Sisesta mängija täisnimi, kelle kohta infot soovid (nt: Mihkel Järviste): ");
        String mängijanimi = küsimus3.nextLine().toLowerCase();
        Mängija mängija = null;
        for (Mängija x : mängijad) { //Võrdleb etteantud mängijanime meie mängijate listiga
            if(x.getNimi().toLowerCase().strip().equals(mängijanimi)) mängija = x;
        }

        switch (vastus2){
            case "klubi":
                System.out.println(mängija.getKlubi());
                break;
            case "jooks":
                System.out.println(mängija.getJooks());
                break;
            case "särginumber":
                System.out.println(mängija.getSärk());
                break;
            case "vanus":
                System.out.println(mängija.getVanus());
                break;
            case "väravad":
                System.out.println(mängija.getVäravad());
                break;
            case "stop":
                break;
            case "stopp":
                break;
            default:
                break;
        }
    }
    //Abimeetod, käivitatakse, kui kasutaja soovid infot klubide kohta
    private static void klubiKüsimus() {
        Scanner küsimus2 = new Scanner(System.in);
        System.out.println("Mida klubide kohta teada soovid? Asukoht/Mängijad/Mängijate arv ");
        String vastus2 = küsimus2.nextLine().toLowerCase();
        Scanner küsimus3 = new Scanner(System.in);
        System.out.println("Sisesta klubi nimi, mille kohta infot soovid (flora, paide, levadia, tammeka, kalju, tulevik, legion, trans, kure, kalev) ");
        String klubinimi = küsimus3.nextLine().toLowerCase();
        Klubi klubi = null;
        for (Klubi x : premiumLiiga.getKlubid()) { //Võrdleb etteantud nime klubidega ning väärtustab selle
            if (x.getNimi().strip().toLowerCase(Locale.ROOT).equals(klubinimi)) klubi = x;
        }

        switch (vastus2) {
            case "asukoht":
                System.out.println(klubi.getAsukoht());
                break;
            case "mängijad":
                System.out.println(klubi.getMängijad());
                break;
            case "mängijate arv":
                System.out.println(klubi.getArv());
                break;
            case "stop":
                break;
            case "stopp":
                break;
            default:
                break;
        }
    }
    //Abimeetod, käivitatakse, kui kasutaja soovid infot liiga kohta
    private static void liigaKüsimus() {
        Scanner küsimus2 = new Scanner(System.in);
        System.out.println("Mida liiga kohta teada soovite? Väravalööjad/Jooksjad/Meeskonnad ");
        String vastus2 = küsimus2.nextLine().toLowerCase();
        switch (vastus2) {
            case "väravalööjad":
                premiumLiiga.väravalööjad();
                break;
            case "jooksjad":
                premiumLiiga.jooksjad();
                break;
            case "meeskonnad":
                System.out.println(premiumLiiga.getKlubid());
                break;
            case "stop":
                break; //Lõpetab töö
            case "stopp":
                break; //lõpetab töö
            default:
                break;
        }
    }


}
