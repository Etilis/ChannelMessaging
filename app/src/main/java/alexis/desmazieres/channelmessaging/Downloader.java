package alexis.desmazieres.channelmessaging;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by desmazia on 20/01/2017.
 */


public class Downloader extends AsyncTask<Void, Void, String> implements OnDownloadCompleteListener {


    public static final String PREFS_NAME = "properties";

    ArrayList<OnDownloadCompleteListener> listDownload = new ArrayList<OnDownloadCompleteListener>();
    LoginActivity login;
    ChannelActivity channel;
    private String url;
    HashMap<String, String> postparams;

    public Downloader(LoginActivity login)
    {
        this.login = login;
    }

    public Downloader(ChannelActivity channelActivity, String url, HashMap<String, String> postparams)
    {
        this.channel = channelActivity;
        this.url = url;
        this.postparams = postparams;
    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws
            UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first) first = false;
            else result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }


    public void setListDownload(OnDownloadCompleteListener oneDow) {
        this.listDownload.add(oneDow);
    }

    public ArrayList<OnDownloadCompleteListener> getListDownload() {
        return listDownload;
    }

    @Override
    protected String doInBackground(Void... params) {

        HashMap<String, String> postparams = new HashMap<>();

        String response = null;
        if(this.login != null){
            postparams.put("username", login.getId());
            postparams.put("password", login.getPassword());
            response = performPostCall("http://www.raphaelbischof.fr/messaging/?function=connect", postparams);
        }
        //faire méthiode générique
        else if(this.channel != null){

        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        for (OnDownloadCompleteListener listener : listDownload)
        {
            listener.onDownloadCompleted(s);
        }
    }


    @Override
    public void onDownloadCompleted(String content) {

    }


}