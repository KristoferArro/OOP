import java.time.Year;

public class Mängija{
    private String nimi;
    private int väravad;
    private double jooks;
    private int särk;
    private String klubi;
    private int vanus;

    public Mängija(String nimi, int sünniaasta, String positsioon, String klubi, int väravad, int särk) {
        this.nimi = nimi;
        this.väravad = väravad;
        this.särk = särk;
        this.klubi = klubi;
        this.vanus = setVanus(sünniaasta);
        this.jooks = setJooks(positsioon);
    }

    public String getNimi() {
        return nimi;
    }

    public int getVäravad() {
        return väravad;
    }

    public double getJooks() {
        return jooks;
    }

    public int getSärk() {
        return särk;
    }

    public int getVanus() {
        return vanus;
    }

    public String getKlubi() {
        return klubi;
    }

    public double setJooks(String positsioon) {
        switch (positsioon) {
            case "ründaja":
                return 7.0 + (Math.random() * 2.0); //Keskmine distants 7km - 9km
            case "poolkaitsja":
                return 8.0 + (Math.random() * 4.0); //Keskmine distants 8km - 12km
            case "kaitsja":
                return 5.0 + (Math.random() * 3.0);//Keskmine distants 5km - 8km
            case "väravavaht":
                return 1.0 + (Math.random());//Keskmine distants 1km - 2km
            default:
                return 6.0 + (Math.random() * 4.0); //Keskmine distants 6km - 10km
        }
    }

    public int setVanus(int sünniaasta) {
        int aasta = Year.now().getValue();
        return aasta - sünniaasta; //Leiab isiku vanuse
    }

    @Override
    public String toString() {
        return "Nimi:" + nimi +
                ", vanus: " + vanus +
                ", klubi: " + klubi +
                ", särgi nr: " + särk +
                ", väravaid: " + väravad +
                ", keskmiselt joostud distants mängus (km): " + jooks;
    }
}
