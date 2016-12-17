package lt.ktu.ks.notes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
