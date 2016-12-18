package lt.ktu.ks.notes;

import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import lt.ktu.ks.notes.Models.Nustatymai;
import lt.ktu.ks.notes.Models.Temperatura;

/**
 * Created by Lukis on 2016-12-01.
 */
public class DataAPI {

    private static final String TAG = "DataAPI";
    public static Temperatura gautiTemperatura(String RestURL) throws Exception
    {
        int id =0;
        String date = "";
        double t = 0.0;
        String response = WebAPI.gautiTemperatura(RestURL);
        ArrayList<LinkedTreeMap> jsonResponse = null;
        if(response.length() > 1)
        {
            Gson gson;
            gson = new Gson();

           JSONArray jObject = new JSONArray(response);
            JSONObject object = (JSONObject) jObject.get(0);
            id = (int) object.get("id");
            date = object.get("date").toString();
            t = Double.parseDouble(object.get("temp").toString()) ;
        }

        Temperatura temp = new Temperatura(id, t, date);

        return temp;
    }

    public static Nustatymai getNustatymai(String RestURL) throws Exception
    {
        int id = 0;
        double coldLimit = 0.0;
        double normalLimit = 0.0;
        int status = 0;
        String response = WebAPI.gautiNustatymus(RestURL);
        ArrayList<LinkedTreeMap> jsonResponse = null;
        if(response.length() > 1)
        {
            Gson gson;
            gson = new Gson();

            JSONArray jObject = new JSONArray(response);
            JSONObject object = (JSONObject) jObject.get(0);
            id = (int) object.get("id");
            coldLimit = Double.parseDouble(object.get("coldLimit").toString());
            normalLimit = Double.parseDouble(object.get("normalLimit").toString());
            status = (int) object.get("status");
        }

        Nustatymai temp = new Nustatymai(id, coldLimit, normalLimit, status);

        return temp;
    }

    // get nustatymai

   /* public static Uzrasas gautiUzrasa(String RestURL, int ID) throws Exception
    {
        Uzrasas rez = new Uzrasas();
        String response = WebAPI.gautiUzrasa(RestURL, ID);

        if(response.length() > 1)
        {
            Gson gson;
            gson = new Gson();

            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Uzrasas>() {}.getType();
            rez = gson.fromJson(response, type);
        }

        return rez;
    }*/
}
