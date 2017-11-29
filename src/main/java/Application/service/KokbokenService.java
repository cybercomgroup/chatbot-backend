package Application.service;

import Application.pojo.ResponsePojo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class KokbokenService {

    private final ResponsePojo responsePojo;

    private String menuItemOne;
    private String menuItemTwo;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public KokbokenService(ResponsePojo responsePojo) {
        this.responsePojo = responsePojo;
    }


    public void getMenu() {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://carboncloudrestaurantapi.azurewebsites.net/api/menuscreen/getdataweek?restaurantid=35")
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            BufferedReader in = new BufferedReader(new InputStreamReader(response.body().byteStream()));
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            JSONObject jsonObject = new JSONObject(new JSONTokener(stringBuffer.toString()));
            JSONArray jsonArray = jsonObject.getJSONArray("menus");
            for(int i = 0; i < jsonArray.length(); i++) {
                if(jsonArray.getJSONObject(i).getString("menuDate").contains(simpleDateFormat.format(new Date()))) {
                    JSONArray tempArray = jsonArray.getJSONObject(i).getJSONArray("recipeCategories");
                    menuItemOne = tempArray.getJSONObject(0).getJSONArray("recipes").getJSONObject(0).
                            getJSONArray("displayNames").getJSONObject(0).getString("displayName");

                    menuItemTwo = tempArray.getJSONObject(1).getJSONArray("recipes").getJSONObject(0).
                            getJSONArray("displayNames").getJSONObject(0).getString("displayName");
                }
            }
            responsePojo.setResponse1(menuItemOne);
            responsePojo.setResponse2(menuItemTwo);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}