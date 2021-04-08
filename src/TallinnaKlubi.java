public class TallinnaKlubi extends Klubi {
    private String linnaosa;

    public TallinnaKlubi(String nimi, String asukoht, String linnaosa) {
        super(nimi, asukoht);
        this.linnaosa = linnaosa;
    }

    @Override
    public String toString() {
        return super.toString() + ", Linnaosa: " + linnaosa + ";";
    }
}
