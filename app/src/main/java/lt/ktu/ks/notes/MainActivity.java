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
        Log.d("Temp", temp.date);
        tempText.setText(String.valueOf(temp.temp));
    }
    private void gautiTemperatura()
    {
        new gautiTemperaturaTask().execute(Tools.RestURL, null, null);
    }

    private class gautiTemperaturaTask extends AsyncTask<String, Void, Temperatura>
    {
        Temperatura temp = new Temperatura();

        @Override
        protected void onPreExecute()
        {

            super.onPreExecute();
        }

        protected Temperatura doInBackground(String... str_param)
        {
            String RestURL = str_param[0];
            try
            {
                temp = DataAPI.gautiTemperatura(Tools.RestURL);
            }
            catch(Exception ex)
            {
                Log.e(TAG, ex.toString());
            }

            return temp;
        }

        protected void onProgressUpdate(Void... progress) {}

        @Override
        protected void onPostExecute(Temperatura temp)
        {
            rodytiTemperatura(temp);
        }

    }

/*
    private void gautiUzrasus()
    {
        new gautiUzrasusTask().execute(Tools.RestURL, null, null);
    }

    private void rodytiUzrasus(List<Uzrasas> uzrasai)
    {
        UzrasaiDataList = new ArrayList<HashMap<String, String>>();

        for(int i=0; i<uzrasai.size(); i++)
        {
            Uzrasas u = uzrasai.get(i);

            HashMap<String, String> UzrasasDataMap =  new HashMap<String, String>();

            UzrasasDataMap.put("id", String.valueOf(u.ID));
            UzrasasDataMap.put("pavadinimas", u.Pavadinimas);
            UzrasasDataMap.put("kategorija", u.Kategorija);
            UzrasasDataMap.put("arSvarbus", u.ArSvarbus);

            UzrasaiDataList.add(UzrasasDataMap);
        }

        ListView mlv = (ListView)findViewById(R.id.uzrasaiListView);

        SimpleAdapter SimpleMiestaiAdapter = new SimpleAdapter(this, UzrasaiDataList, R.layout.uzrasai_list_row,
                new String[] {"pavadinimas", "kategorija", "arSvarbus"},
                new int[] {R.id.pavadinimasTextView, R.id.kategorijaTextView, R.id.arSvarbusTextView});

        mlv.setAdapter(SimpleMiestaiAdapter);
        mlv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        String UzrasoID = UzrasaiDataList.get(pos).get("id");
        String Pavadinimas = UzrasaiDataList.get(pos).get("pavadinimas");

        Intent myIntent = new Intent(this, UzrasasActivity.class);
        myIntent.putExtra("id", UzrasoID);
        myIntent.putExtra("pavadinimas", Pavadinimas);
        startActivity(myIntent);
    }

    private class gautiUzrasusTask extends AsyncTask<String, Void, List<Uzrasas>>
    {
        ProgressDialog actionProgressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute()
        {
            actionProgressDialog.setMessage("Gaunami užrašai...");
            actionProgressDialog.show();
            actionProgressDialog.setCancelable(true);
            super.onPreExecute();
        }

        protected List<Uzrasas> doInBackground(String... str_param)
        {
            String RestURL = str_param[0];
            List<Uzrasas> uzrasai = new ArrayList<Uzrasas>();
            try
            {
                uzrasai = DataAPI.gautiUzrasus(RestURL);
            }
            catch(Exception ex)
            {
                Log.e(TAG, ex.toString());
            }

            return uzrasai;
        }

        protected void onProgressUpdate(Void... progress) {}

        protected void onPostExecute(List<Uzrasas> result)
        {
            actionProgressDialog.cancel();
            rodytiUzrasus(result);
        }
    }*/
}
