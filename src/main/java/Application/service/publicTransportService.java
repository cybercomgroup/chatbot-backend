package Application.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class publicTransportService {

    private VasttrafikTokenService VTS;
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'&time='HH'%3A'mm");
    // SimpleDateFormat timeFormat = new SimpleDateFormat("");


    public publicTransportService(){
        VTS = new VasttrafikTokenService();
    }

    public String getDepature(){
        String url = "https://api.vasttrafik.se/bin/rest.exe/v2/departureBoard?id=9021014004490000&date="+dateFormat.format(date);


        HttpsURLConnection connection = null;
        try {
            URL obj = new URL(url);
            connection = (HttpsURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + VTS.tokenParser(VTS.fetchToken()));

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            JSONObject jsonObject = XML.toJSONObject(response.toString());
            jsonObject = jsonObject.getJSONObject("DepartureBoard");
            JSONArray jsonArray = jsonObject.getJSONArray("Departure");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject temp = jsonArray.getJSONObject(i);
                if(temp.getString("name").equals("Buss 16") &&
                        (temp.getString("direction").equals("Högsbohöjd") ||
                                temp.getString("direction").equals("Marklandsgatan"))) {
                    return temp.getString("time");
                }
            }
            JSONObject jobj = jsonArray.getJSONObject(2);

            System.out.println(jsonObject);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "N/A";

    }
    public static void main(String args[]) {
        publicTransportService PTS = new publicTransportService();
        System.out.println(PTS.getDepature());
    }

}



