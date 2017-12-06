package Application.service;

import Application.pojo.ResponsePojo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LsKitchenService {

    private final ResponsePojo responsePojo;


    @Autowired
    public LsKitchenService(ResponsePojo responsePojo) {
        this.responsePojo = responsePojo;
    }


    void getMenu() {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://carboncloudrestaurantapi.azurewebsites.net/api/menuscreen/getdataday?restaurantid=8")
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            BufferedReader in = new BufferedReader(new InputStreamReader(response.body().byteStream()));
            String inputLine;
            StringBuilder stringBuffer = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            JSONObject jsonObject = new JSONObject(new JSONTokener(stringBuffer.toString()));
            JSONArray jsonArray = jsonObject.getJSONArray("recipeCategories");
            String menuItemOne = jsonArray.getJSONObject(0).getJSONArray("recipes").getJSONObject(0)
                    .getJSONArray("displayNames").getJSONObject(0).getString("displayName");
            String menuItemTwo = jsonArray.getJSONObject(1).getJSONArray("recipes").getJSONObject(0)
                    .getJSONArray("displayNames").getJSONObject(0).getString("displayName");
            String menuItemThree = jsonArray.getJSONObject(3).getJSONArray("recipes")
                    .getJSONObject(0).getJSONArray("displayNames").getJSONObject(0)
                    .getString("displayName");


            responsePojo.setResponse("Kött: "+ menuItemOne +"\nFisk: " + menuItemTwo + "\nVeggie: " + menuItemThree);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}


