/**
 *
 * @version 2017-11-09
 * @author Johan Martinson
 * @author Daniel Rydén
 */
package Application.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WitaiService {

    private final ResponseParserService responseParserService;

    private static final String url = "https://api.wit.ai/message";
    private static final String token = "EWFRMP2FXYKOEF4CSSSANDO4F4NTGZCN";
    private static final String charset = "UTF-8";
    private static String versionParam = "0.0.1";

    private String query, phrase;

    private URLConnection connection;

    private String response;

    private JSONObject jsonObject;

    @Autowired
    public WitaiService(ResponseParserService responseParserService) {

        this.responseParserService = responseParserService;
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

    public String getStringResponse() {
        return response;
    }

    public JSONObject getJsonResponse() {
        return jsonObject;
    }

    public void setPhrase(String phrase){
        this.phrase = phrase;

        try {
            query = String.format("v=%s&q=%s",
                    URLEncoder.encode(versionParam, charset),
                    URLEncoder.encode(phrase, charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(witResponse(query) != null)
            response = witResponse(query);

        try {
            jsonObject = new JSONObject(new JSONTokener(response));
            System.out.println(jsonObject);
            responseParserService.setResponse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
