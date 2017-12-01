
package Application.service;

import Application.pojo.ResponsePojo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 2017-11-22
 * @author Johan Martinson
 * @author Daniel Rydén
 */
@Service
public class PlaceService {

    private final ResponsePojo responsePojo;

    private static final String KEY = "&key=AIzaSyAldrk8h5ElMPKxI5p3yiw1SwoSRMt6Vkw";

    private static final String urlS = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    private String placeOne = null;
    private String placeTwo = null;
    private String placeThree = null;

    @Autowired
    public PlaceService(ResponsePojo responsePojo){

        this.responsePojo = responsePojo;
    }

    void placeResponse(String query){

        try {
            URL url = new URL(urlS + "lindholmen+" + query   + KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(connection.getInputStream());

            JSONObject jsonObject = new JSONObject(new JSONTokener(getStringFromInputStream(in)));
            in.close();

            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i = 0; i < jsonArray.length(); i++) {
                if(placeOne == null) {
                    placeOne = jsonArray.getJSONObject(i).getString("name");
                }
                else if(placeTwo == null) {
                    placeTwo = jsonArray.getJSONObject(i).getString("name");
                }
                else if(placeThree == null) {
                    placeThree = jsonArray.getJSONObject(i).getString("name");
                }
            }
            responsePojo.setResponse("Du kan hitta " + query + " vid: \n" +  placeOne + "\n"
                    + placeTwo + "\n" + placeThree);
            placeOne = null;
            placeTwo = null;
            placeThree = null;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    private String getStringFromInputStream(InputStreamReader in) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        String line;

        try{
            bufferedReader = new BufferedReader(in);
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
}
