package com.zisad.hellomedico;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUrl {
    public String readUrl(String myUrl) throws Exception {
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(myUrl).openConnection();
            connection.connect();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                String line = readLine;
                if (readLine != null) {
                    stringBuffer.append(line);
                } else {
                    String data = stringBuffer.toString();
                    bufferedReader.close();
                    return data;
                }
            }
        } finally {
            inputStream.close();
            connection.disconnect();
        }
    }
}
