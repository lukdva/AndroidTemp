package lt.ktu.ks.notes;

/**
 * Created by Lukis on 2016-12-14.
 */

public class Nustatymai {
    public int id;
    public double coldLimit;
    public double normalLimit;
    public int status;
    public String date;


    public Nustatymai() {
    }

    public Nustatymai(int id, double coldLimit, double normalLimit, int status, String date) {
            this.id = id;
            this.coldLimit = coldLimit;
            this.normalLimit = normalLimit;
            this.status = status;
            this.date = date;
    }
}
