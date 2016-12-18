package lt.ktu.ks.notes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;

import lt.ktu.ks.notes.Models.Nustatymai;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.lang.Integer.parseInt;

public class NustatymaiActivity extends Activity {

    private static  final  String TAG = "NustatymaiActivity";
    EditText editText1;
    EditText editText2;
    private Button submitButton;
    private double coldInt;
    private double normalInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nustatymai);
        editText1 = (EditText) findViewById(R.id.setCold);
        editText2 = (EditText) findViewById(R.id.setNormal);

        submitButton = (Button) findViewById(R.id.setSettings);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coldsLimit = editText1.getText().toString();
                if(editText1.getText()!=null &&  editText2.getText()!=null) {
                    String coldLimit = editText1.getText().toString();
                    String normalLimit = editText2.getText().toString();
                    coldInt = parseInt(coldLimit);
                    normalInt = parseInt(normalLimit);
                    changeNustatymai();
                }
            }
        });
    }


    private void changeNustatymai()
    {
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
                nust = new Nustatymai(1,coldInt,normalInt,1);
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
            Intent in = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(in);
        }

    }

}
