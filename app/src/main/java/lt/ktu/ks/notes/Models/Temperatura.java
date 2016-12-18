package lt.ktu.ks.notes.Models;

/**
 * Created by Juozas on 2015-10-03.
 */
public class Temperatura {

    public int id;
    public double temp;
    public String date;


    public Temperatura() {
    }

    public Temperatura(int id, double temp, String date) {
        this.id = id;
        this.temp = temp;
        this.date = date;
    }
}
