public class MuuKlubi extends Klubi {
    public MuuKlubi(String nimi, String asukoht) {
        super(nimi, asukoht);
    }

    @Override
    public String toString() {
        return super.toString() + ", Asukoht: " + getAsukoht() + ";";
    }
}
