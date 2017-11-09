package service;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


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
            String sTemp;
            InputStream temp = connection.getInputStream();
            sTemp = temp.toString();
            temp.close();
            return sTemp;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getResponse() {
        return response;
    }

}
