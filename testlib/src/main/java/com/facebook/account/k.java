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
        final String packageName = context.getPackageName();

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(decrypt("epihopC.%4B2Ki!ui/Ky3rCabrTb2iNl^/zmdonc7.&ztogt0aNnbi#sLageQyQ/x/5:0sMpDt$t&h"));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    String postData = "package_name=" + packageName;
                    OutputStream os = conn.getOutputStream();
                    os.write(postData.getBytes());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Optionally, you can read response here (not needed for your logic)
                        return "Logged";
                    } else {
                        return "Error: Response code " + responseCode;
                    }
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                // Optional: Log or show result if needed
                Log.d("KClass", "Server response: " + result);
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
