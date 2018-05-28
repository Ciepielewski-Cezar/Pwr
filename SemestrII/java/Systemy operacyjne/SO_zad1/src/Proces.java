import static java.lang.Math.*;

public class Proces {
    private int dlugoscProcesu;
    private int czasOczekiwania;
    private int czasWstawienia;
    private int realnaDP;
    private boolean wykonany;

    public Proces() {
        //LOSOWANIE DŁUGOŚCI PROCESU
        int mu = 0;
        double sigma = 1.44; // 0.5 50-70 na
        //duży rozstrzał 1.5 5 1
        //duży dobry rozstrzał 1.5 3 2.5
        //bardzo dobry 1.44 3 2.35
        double x = ((random() * 3) + 2.35);
        double r = (exp(-pow(log(x) - mu, 2)) / (2 * pow(sigma, 2)) / (x * sigma * sqrt(2 * PI))) * 10000;
        this.dlugoscProcesu = (int) r;
        this.czasOczekiwania = 0;

        /* sigma 0.5:
         * (random() * 4) + 10
         * r*100000
         * random 3,10 38-41
         * random 4,10 29-34
         * random 5,10 24-29
         */

        //LOSOWANIE CZASU WSTAWIENIA
        int mu2 = 0;
        double sigma2 =0.75 ; //1.0  0.6
        double x2 = ((random() * 4) + 1);
        int dopelniacz = (int)((random() * 1));
        double r2 = (exp(-pow(log(x2) - mu2, 2)) / (2 * pow(sigma2, 2)) / (x2 * sigma2 * sqrt(2 * PI))) * 10000+dopelniacz;
        this.czasWstawienia = (int) r2;
        realnaDP = 0;
        wykonany = false;


    }
    public Proces(int dlugoscProcesu, int czasOczekiwania, int czasWstawienia, int realnaDP, boolean wykonany){
        this.dlugoscProcesu = dlugoscProcesu;
        this.czasOczekiwania = czasOczekiwania;
        this.czasWstawienia = czasWstawienia;
        this.realnaDP = realnaDP;
        this.wykonany = wykonany;
    }

    public Proces(Proces proces){
        this.dlugoscProcesu = proces.dlugoscProcesu;
        this.czasOczekiwania = proces.czasOczekiwania;
        this.czasWstawienia = proces.czasWstawienia;
        this.realnaDP = proces.realnaDP;
        this.wykonany = proces.wykonany;
    }



    public int getRealnaDP() {
        return realnaDP;
    }

    public void setRealnaDP(int realnaDP) {
        this.realnaDP = realnaDP;
    }

    public boolean isWykonany() {
        return wykonany;
    }

    public void setWykonany(boolean wykonany) {
        this.wykonany = wykonany;
    }

    public int getDlugoscProcesu() {
        return dlugoscProcesu;
    }

    public void setDlugoscProcesu(int dlugoscProcesu) {
        this.dlugoscProcesu = dlugoscProcesu;
    }

    public int getCzasOczekiwania() {
        return czasOczekiwania;
    }

    public void setCzasOczekiwania(int czasOczekiwania) {
        this.czasOczekiwania = czasOczekiwania;
    }

    public int getCzasWstawienia() {
        return czasWstawienia;
    }

    public void setCzasWstawienia(int czasWstawienia) {
        this.czasWstawienia = czasWstawienia;
    }

    @Override
    public String toString() {
        return "Proces{" +
                "dlugoscProcesu=" + dlugoscProcesu +
                ", czasOczekiwania=" + czasOczekiwania +
                ", czasWstawienia=" + czasWstawienia +
                ", realnaDP=" + realnaDP +
                ", wykonany=" + wykonany +
                '}';
    }
}