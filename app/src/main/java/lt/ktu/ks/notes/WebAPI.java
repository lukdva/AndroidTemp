package lt.ktu.ks.notes;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juozas on 2015-10-03.
 */
public class WebAPI {
    private static final String TAG = "WebAPI";

//TODO Get nustatymai

    public static String gautiNustatymai(String URL) throws Exception
    {
        HttpClient client = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(URL + "/temp/getSettings");

        HttpResponse response = client.execute(getRequest);

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        char[] buffer = new char[4096];
        int read_cnt = 0;

        StringBuffer sb = new StringBuffer();

        while((read_cnt = reader.read(buffer, 0, 4096)) != -1)
        {
            sb.append(buffer, 0, read_cnt);
        }

        Log.e(TAG, sb.toString());
        return sb.toString();
    }

    public static String gautiTemperatura(String URL) throws Exception
    {
        HttpClient client = new DefaultHttpClient();
        HttpPost getRequest = new HttpPost(URL + "/temp/getCurrentTemperature");

        HttpResponse response = client.execute(getRequest);

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        char[] buffer = new char[4096];
        int read_cnt = 0;

        StringBuffer sb = new StringBuffer();

        while((read_cnt = reader.read(buffer, 0, 4096)) != -1)
        {
            sb.append(buffer, 0, read_cnt);
        }

        Log.e(TAG, sb.toString());
        return sb.toString();
    }

    public static String postNustatymai(String URL, Nustatymai settings) throws Exception
    {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 3000);

        HttpClient client = new DefaultHttpClient(params);
        HttpPost postRequest = new HttpPost(URL + "/setSettings");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
        nameValuePair.add(new BasicNameValuePair("coldLimit", String.format("%s", settings.coldLimit)));
        nameValuePair.add(new BasicNameValuePair("normalLimit", String.format("%s", settings.normalLimit)));
        nameValuePair.add(new BasicNameValuePair("status", String.format("%s", settings.status)));

        postRequest.setEntity(new UrlEncodedFormEntity(nameValuePair));


        HttpResponse response = client.execute(postRequest);

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        char[] buffer = new char[4096];
        int read_cnt = 0;

        StringBuffer sb = new StringBuffer();

        while((read_cnt = reader.read(buffer, 0, 4096)) != -1)
        {
            sb.append(buffer, 0, read_cnt);
        }

        Log.e(TAG, sb.toString());
        return sb.toString();
    }
}
