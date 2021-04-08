import java.util.ArrayList;
import java.util.List;

public abstract class Klubi {
    private final String nimi;
    private List<Mängija> mängijad;
    private final String asukoht;

    public String getNimi() {
        return nimi;
    }

    public List<Mängija> getMängijad() {
        return mängijad;
    }

    public void getMängijanimed(){ //Väljastab klubi mängijate nimed
        for (Mängija mängija : mängijad) {
            System.out.println(mängija.getNimi());
        }
    }

    public String getAsukoht() {
        return asukoht;
    }

    public Klubi(String nimi, String asukoht) {
        this.nimi = nimi;
        this.mängijad = new ArrayList<>();
        this.asukoht = asukoht;
    }

    public void lisaMängija(Mängija mängija) {
        mängijad.add(mängija);
    }

    @Override
    public String toString() {
        return "Klubi nimi: " + nimi +
                ", Mängijaid: " + mängijad.size();
    }
}
