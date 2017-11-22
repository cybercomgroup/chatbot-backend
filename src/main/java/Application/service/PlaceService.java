/**
 *
 * @version 2017-11-22
 * @author Johan Martinson
 * @author Daniel Rydén
 */
package Application.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Service
public class PlaceService {

    private static final String KEY = "&key=AIzaSyAldrk8h5ElMPKxI5p3yiw1SwoSRMt6Vkw";

    private static final String urlS = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    private static final String charset = "UTF-8";

    HttpURLConnection connection = null;

    public PlaceService(){

    }

    private String placeResponse(String query){

        try {
            URL url = new URL(urlS + query + KEY);
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(connection.getInputStream());

            String temp = getStringFromInputStream(in);
            in.close();
            return temp;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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


/* TODO!: remove after testing is finished

 */
    public static void main(String[] args){
        String s = "";
        for(String str: args) {
            s = s+"+" +str;
        }

        PlaceService placeService = new PlaceService();

        System.out.println(placeService.placeResponse(s));
        //System.out.println(placeService.placeResponse(args[0]));

    }


}
