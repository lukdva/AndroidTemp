package lt.ktu.ks.notes;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import lt.ktu.ks.notes.Models.Nustatymai;
import lt.ktu.ks.notes.Models.Temperatura;

public class MainActivity extends Activity
{
    private static  final  String TAG = "MainActivity";
    ArrayList<HashMap<String, String>> UzrasaiDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gautiNustatymus();
        gautiTemperatura();

    }

    protected void rodytiTemperatura(Temperatura temp) {
        TextView tempText = (TextView)findViewById(R.id.textView2);
        //TextView busena = (TextView)findViewById(R.id.busena);
        tempText.setText(String.valueOf(temp.temp));
    }

    protected void rodytiNustatymus(Nustatymai nust) {
        TextView colLimit = (TextView)findViewById(R.id.coldLimit);
        TextView normalLimit = (TextView)findViewById(R.id.normalLimit);
        colLimit.setText(String.valueOf(nust.coldLimit));
        normalLimit.setText(String.valueOf(nust.normalLimit));
    }

    private void gautiNustatymus() {
    new gautiNustatymusTask().execute(null,null,null);
}
    private void gautiTemperatura()
    {
        new gautiTemperaturaTask().execute(null, null, null);
    }


    private class gautiNustatymusTask extends AsyncTask<String, Void, Nustatymai> {
        Nustatymai nust = new Nustatymai();

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        protected Nustatymai doInBackground(String... str_param) {
            String RestURL = str_param[0];
            try {
                nust = DataAPI.getNustatymai((Tools.RestURL));
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }

            return nust;
        }

        protected void onProgressUpdate(Void... progress) {
        }

        @Override
        protected void onPostExecute(Nustatymai nust) {
            rodytiNustatymus(nust);
        }

    }

    private class gautiTemperaturaTask extends AsyncTask<String, Void, Temperatura> {
        Temperatura temp = new Temperatura();

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        protected Temperatura doInBackground(String... str_param) {
            String RestURL = str_param[0];
            try {
                temp = DataAPI.gautiTemperatura(Tools.RestURL);
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }

            return temp;
        }

        protected void onProgressUpdate(Void... progress) {
        }

        @Override
        protected void onPostExecute(Temperatura temp) {
            rodytiTemperatura(temp);
        }

    }
}
