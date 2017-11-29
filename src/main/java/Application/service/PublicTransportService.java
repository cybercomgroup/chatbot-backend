package Application.service;

import Application.pojo.ResponsePojo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicTransportService {

    private final VasttrafikTokenService vts;

    private final ResponsePojo responsePojo;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'&time='HH'%3A'mm");
    private String bus;
    private String departureOne;
    private String departureTwo;

    @Autowired
    public PublicTransportService(VasttrafikTokenService vts, ResponsePojo responsePojo){
        this.vts = vts;
        this.responsePojo = responsePojo;
    }

    void getDepature() {
        Date date = new Date();

        String url16 = "https://api.vasttrafik.se/bin/rest.exe/v2/departureBoard?id=9021014004490000&date=" + dateFormat.format(date);
        String url55 = "https://api.vasttrafik.se/bin/rest.exe/v2/departureBoard?id=9021014006675000&date=" + dateFormat.format(date);

        HttpsURLConnection connection;
        try {
            if (bus.equals("16")) {

                URL obj = new URL(url16);
                connection = (HttpsURLConnection) obj.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", "Bearer " + vts.tokenParser(vts.fetchToken()));

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                JSONObject jsonObject = XML.toJSONObject(response.toString());
                jsonObject = jsonObject.getJSONObject("DepartureBoard");
                JSONArray jsonArray = jsonObject.getJSONArray("Departure");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject temp = jsonArray.getJSONObject(i);
                    if (temp.getString("name").equals("Buss 16") &&
                            (temp.getString("direction").equals("Högsbohöjd") ||
                                    temp.getString("direction").equals("Marklandsgatan"))) {
                        if(departureOne == null)
                            departureOne = temp.getString("time");
                        else if(departureTwo == null)
                            departureTwo = temp.getString("time");


                    }
                }
                responsePojo.setResponse1(departureOne);
                responsePojo.setResponse2(departureTwo);
                departureOne = null;
                departureTwo = null;

            } else if (bus.equals("55")) {
                URL obj = new URL(url55);
                connection = (HttpsURLConnection) obj.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", "Bearer " + vts.tokenParser(vts.fetchToken()));

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                JSONObject jsonObject = XML.toJSONObject(response.toString());

                System.out.println(jsonObject);
                jsonObject = jsonObject.getJSONObject("DepartureBoard");
                JSONArray jsonArray = jsonObject.getJSONArray("Departure");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject temp = jsonArray.getJSONObject(i);
                    if (temp.getString("name").equals("Buss 55")) {
                        if(departureOne == null)
                            departureOne = temp.getString("time");
                        else if(departureTwo == null)
                            departureTwo = (temp.getString("time"));
                    }
                }

                responsePojo.setResponse1(departureOne);
                responsePojo.setResponse2(departureTwo);
                departureOne = null;
                departureTwo = null;
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    void setBus(String bus) {
        this.bus = bus;
    }
}



