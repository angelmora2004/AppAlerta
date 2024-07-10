package com.example.fapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginAsyncTask extends AsyncTask<Void, Void, String> {
    private Activity activity;
    private ProgressDialog dialog;
    private String username, password;

    public LoginAsyncTask(Activity activity, ProgressDialog dialog, String username, String password) {
        super();
        this.activity = activity;
        this.dialog = new ProgressDialog(activity);
        this.username = username.replace(" ", "+");
        this.password = password;
    }
    @Override
    protected void onPreExecute() {
        dialog.setMessage("Conectandose...");
        dialog.setTitle("App Alerta");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://www.inkadroid.com/inkadroid/webservice/acces2.php?usuario-" + username + "&password-" + password + "");
        try {
            HttpResponse HttpResponse = httpClient.execute(httpPost);
            String str;
            if (HttpResponse.getStatusLine().getStatusCode() == 200) {
                str = EntityUtils.toString(HttpResponse.getEntity());
                return str;
            } else {
                return "Error";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        this.dialog.dismiss();
        try {
            if (!s.equalsIgnoreCase("error")){
                JSONArray jsonArray = new JSONArray(s);
                JSONObject json = jsonArray.getJSONObject(0);
                int status = (int) json.get("logstatus");
                Toast.makeText(activity, status, Toast.LENGTH_SHORT).show();
                if (status == 1) {
                    Intent i = new Intent(activity, MainActivity.class);
                    activity.startActivity(i);
                    activity.finish();
                } else {
                    Toast.makeText(activity.getApplicationContext(), "Incorrecto usuario o contraseña", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(activity.getApplicationContext(), "Error al conectar", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e ){
            e.printStackTrace();
        }
    }
}
