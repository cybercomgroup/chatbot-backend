package service;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;


/**
 * @author Johan Martinson
 * @author Daniel Rydén
 */

public class WitaiService {
    private static final String url = "https://api.wit.ai/message";
    private static final String token = "EWFRMP2FXYKOEF4CSSSANDO4F4NTGZCN";
    private static final String charset = "UTF-8";
    private static String versionParam = "0.0.1";

    private String query;

    private URLConnection connection;

    private String response;

    public WitaiService(String phrase) {
        try {
            query = String.format("v=%s&q=%s",
                    URLEncoder.encode(versionParam, charset),
                    URLEncoder.encode(phrase, charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(witResponse(query) != null)
            response = witResponse(query);

        System.out.println(response);


    }

    private String witResponse(String query) {
        try {
            connection = new URL(url + "?" + query).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Accept-Charset", charset);

        try {
            InputStream temp = connection.getInputStream();
            String sTemp = getStringFromInputStream(temp);
            temp.close();
            return sTemp;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getStringFromInputStream(InputStream temp) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        String line;

        try{
            bufferedReader = new BufferedReader(new InputStreamReader(temp));
            while ((line = bufferedReader.readLine())!=null)
                stringBuilder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try{
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    public String getResponse() {
        return response;
    }

    /*public static void main(String[] args){
        String s = "";
        for(String str: args) {
            s = s+" " +str;
        }
        System.out.println(s);
        WitaiService witaiService = new WitaiService(s);
    }*/
}
