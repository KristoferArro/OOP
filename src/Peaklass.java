import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

    public static List<Mängija> loeFail(String failinimi) throws Exception{ //Loeb failist mängijate andmed
        List<Mängija> mängijad = new ArrayList<>();
        File objekt = new File(failinimi);
        Scanner lugeja = new Scanner(objekt);
        while(lugeja.hasNextLine()){
            String rida = lugeja.nextLine();
            String[] andmed = rida.split(", ");
            Mängija mängija = new Mängija(andmed[0],Integer.parseInt(andmed[1]),andmed[2],andmed[3],Integer.parseInt(andmed[4]),Integer.parseInt(andmed[5])); //Loob uue mängija
            mängijad.add(mängija); //Lisab mängija mängijate järjendisse
        }
        return mängijad;
    }
    //Mängijate järjendi põhjal saab luua klubid

    public static void main(String[] args) {

    }
}
