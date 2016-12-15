package lt.ktu.ks.notes;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

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
