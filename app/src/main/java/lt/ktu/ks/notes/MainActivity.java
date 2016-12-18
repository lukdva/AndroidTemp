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

        gautiTemperatura();

    }

    protected void rodytiTemperatura(Temperatura temp) {
        TextView tempText = (TextView)findViewById(R.id.textView2);
        TextView busena = (TextView)findViewById(R.id.busena);
        tempText.setText(String.valueOf(temp.temp));
    }
    private void gautiTemperatura()
    {
        new gautiTemperaturaTask().execute(Tools.RestURL, null, null);
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
                Nustatymai nust = DataAPI.getNustatymai((Tools.RestURL));
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
