import java.time.Year;

public class Mängija {
    private String nimi;
    private int väravad;
    private double jooks;
    private String positsioon;
    private int särk;
    private String klubi;
    private int sünniaasta;
    private int vanus;

    public Mängija(String nimi,int sünniaasta, String positsioon, String klubi, int väravad, int särk) {
        this.nimi = nimi;
        this.väravad = väravad;
        this.positsioon = positsioon;
        this.särk = särk;
        this.klubi = klubi;
    }

    public void setJooks(String positsioon){
        switch(positsioon){
            case "ründaja":
                jooks = 7.0+(Math.random()*2.0); //Keskmine distants 7km - 9km
                break;
            case "poolkaitsja":
                jooks = 8.0+(Math.random()*4.0); //Keskmine distants 8km - 12km
                break;
            case "kaitsja":
                jooks = 5.0+(Math.random()*3.0);//Keskmine distants 5km - 8km
                break;
            case "väravavaht":
                jooks = 1.0+(Math.random());//Keskmine distants 1km - 2km
                break;
            default:
                jooks = 6.0+(Math.random()*4.0); //Keskmine distants 6km - 10km
                break;
        }
    }

    public void setVanus(int sünniaasta){
        int aasta = Year.now().getValue();
        vanus = aasta-sünniaasta; //Leiab isiku vanuse
    }




}
