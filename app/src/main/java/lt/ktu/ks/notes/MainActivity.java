package lt.ktu.ks.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import lt.ktu.ks.notes.Models.Nustatymai;
import lt.ktu.ks.notes.Models.Temperatura;

public class MainActivity extends Activity
{
    private static  final  String TAG = "MainActivity";
    ArrayList<HashMap<String, String>> UzrasaiDataList;
    private double coldLimit = 0.0;
    private double normalLimit  = 0.0;
    int status = 1;
    TextView busena;
    private Button onButton;
    private Button offButton;
    private Button settingsButton;
    private  TextView tempText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busena = (TextView)findViewById(R.id.busena);
        busena.setText("Programa pradedama");
        tempText = (TextView)findViewById(R.id.textView2);
        onButton = (Button) findViewById(R.id.buttonOn);
        offButton = (Button) findViewById(R.id.buttonOff);
        settingsButton = (Button) findViewById(R.id.settings);
        //Ijungti mygtukas

        onButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                offButton.setEnabled(true);
                onButton.setEnabled(false);
                status = 1;
                irasytiNustatymus();
            }
        });


        //Isjunkti mygtukas

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 0;
                offButton.setEnabled(false);
                onButton.setEnabled(true);
                irasytiNustatymus();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),
                        NustatymaiActivity.class);
                startActivity(in);
            }
        });


        gautiNustatymus();


        //Scheduler
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if(status == 1) {
                            gautiTemperatura();
                        }
                        else {
                            nustatytiSustabdyta();
                        }

                    }
                });
            }
        };
        timer.schedule(task, 0, 1000);


    }

    protected void nustatytiSustabdyta() {
        tempText.setText("sustabdyta");
        busena.setText("sustabdyta");
    }
   protected void nustatytiMygtukus() {
       if(status == 1) {
           offButton.setEnabled(true);
           onButton.setEnabled(false);
       }
       else {
           offButton.setEnabled(false);
           onButton.setEnabled(true);
       }
   }

    protected void rodytiTemperatura(Temperatura temp) {
        tempText.setText(String.valueOf(temp.temp));
    }

    protected void atnaujintiBusena(Temperatura temp) {
        if(temp.temp< coldLimit) {
        busena.setText("Šalta");
        }
        else if(temp.temp > coldLimit && temp.temp < normalLimit) {
            busena.setText("Normali");
        }
        else {
            busena.setText("Karšta");
        }

    }

    protected void rodytiNustatymus(Nustatymai nust) {
        TextView colLimit = (TextView)findViewById(R.id.coldLimit);
        TextView normalLimit = (TextView)findViewById(R.id.normalLimit);
        colLimit.setText(String.valueOf(nust.coldLimit));
        normalLimit.setText(String.valueOf(nust.normalLimit));
        this.normalLimit = nust.normalLimit;
        this.coldLimit = nust.coldLimit;
        this.status = nust.status;
        nustatytiMygtukus();
    }

    private void gautiNustatymus() {
    new gautiNustatymusTask().execute(null,null,null);
}
    private void gautiTemperatura()
    {
        new gautiTemperaturaTask().execute(null, null, null);
    }

    private void irasytiNustatymus() {
        new irasytiNustatymusTask().execute(null,null,null);
    }


    private class irasytiNustatymusTask extends AsyncTask<String, Void, Nustatymai> {
        Nustatymai nust = new Nustatymai();

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        protected Nustatymai doInBackground(String... str_param) {
            String RestURL = str_param[0];
            try {
                nust = new Nustatymai(1,coldLimit,normalLimit,status);
                DataAPI.irasytiNustatymus(Tools.RestURL, nust);
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }

            return nust;
        }

        protected void onProgressUpdate(Void... progress) {
        }

        @Override
        protected void onPostExecute(Nustatymai nust) {

        }

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
            atnaujintiBusena(temp);
        }

    }
}
