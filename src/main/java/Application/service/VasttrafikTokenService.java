package Application.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class VasttrafikTokenService {
    private static final String url = "https://api.vasttrafik.se/token";

    public VasttrafikTokenService() {}

    public String fetchToken() {
        try {
            URL obj = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Basic ZlR1SWpXV1RUZkpHSlNaajBHdDZKTXQ3cXc0YTptTHhoNWpCVWdsTldWb3NqeXpjZjhTYjBKNGNh");

            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes("grant_type=client_credentials&scope=device_123");
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            return response.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String tokenParser(String response){
        try {
            JSONObject jsonObject = new JSONObject(new JSONTokener(response));
            return jsonObject.getString("access_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String args[]) {
        VasttrafikTokenService vasttrafikTokenService = new VasttrafikTokenService();
        System.out.println(vasttrafikTokenService.tokenParser(vasttrafikTokenService.fetchToken()));

    }

}
