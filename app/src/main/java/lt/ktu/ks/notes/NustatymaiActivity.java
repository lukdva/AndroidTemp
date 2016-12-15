package lt.ktu.ks.notes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.lang.Integer.parseInt;

public class NustatymaiActivity extends Activity {

    private static  final  String TAG = "NustatymaiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nustatymai);

        Bundle extras = getIntent().getExtras();

       /* String ID = extras.getString("id");
        String Pavadinimas = extras.getString("pavadinimas");

        gautiUzrasa(ID);*/
    }


    private void changeNustatymai(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText1 = (EditText) findViewById(R.id.setCold);
        EditText editText2 = (EditText) findViewById(R.id.setNormal);
        String coldLimit = editText1.getText().toString();
        String normalLimit = editText2.getText().toString();
        int coldInt = parseInt(coldLimit);
        int normalInt = parseInt(normalLimit);

        if(coldInt < normalInt) {
            //intent.putExtra(EXTRA_MESSAGE, message);
            //TODO get nustatymai to see active or not
            //TODO set nustatymai with new temps
            startActivity(intent);
        }
        else
        {

            //TODO display error message
        }
    }
    /*
    private void rodytiLaukus(Uzrasas uzr)
    {
        TextView pav = (TextView)findViewById(R.id.pavadinimas);
        pav.setText(uzr.Pavadinimas);

        TextView kat = (TextView)findViewById(R.id.kategorija);
        kat.setBackgroundColor(Color.parseColor("#"+uzr.Spalva));
        kat.setText(uzr.Kategorija);

        TextView tekstas = (TextView)findViewById(R.id.tekstas);
        tekstas.setText(uzr.Tekstas);

        TextView data = (TextView)findViewById(R.id.data);
        data.setText(uzr.DataIrLaikas);
    }

    private class gautiUzrasaTask extends AsyncTask<String, Void, Uzrasas>
    {
        ProgressDialog actionProgressDialog = new ProgressDialog(UzrasasActivity.this);

        @Override
        protected void onPreExecute()
        {
            actionProgressDialog.setMessage("Gaunamas užrašas...");
            actionProgressDialog.show();
            actionProgressDialog.setCancelable(true);
            super.onPreExecute();
        }

        protected Uzrasas doInBackground(String... str_param)
        {
            String RestURL = str_param[0];
            String ID = str_param[1];

            int uzrasoID = parseInt(ID);

            Uzrasas uzr = new Uzrasas();
            try
            {
                uzr = DataAPI.gautiUzrasa(RestURL, uzrasoID);
            }
            catch(Exception ex)
            {
                Log.e(TAG, ex.toString());
            }

            return uzr;

        }

        protected void onProgressUpdate(Void... progress)
        {

        }

        protected void onPostExecute(Uzrasas result)
        {
            actionProgressDialog.cancel();
            rodytiUzrasa(result);
        }
    }
*/
}
