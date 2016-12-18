package lt.ktu.ks.notes.Models;

import java.util.Date;

/**
 * Created by Tom on 18/12/2016.
 */
public class Nustatymai {

    public int id;
    public double coldLimit;
    public double normalLimit;
    public int status;
    public Date date;

    public Nustatymai() {

    }

    public Nustatymai(int id, double coldLimit, double normalLimit, int status) {
        this.id = id;
        this.coldLimit = coldLimit;
        this.normalLimit = normalLimit;
        this.status = status;
    }
}
