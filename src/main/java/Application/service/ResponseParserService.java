package Application.service;

import Application.controller.ResponseHandlerController;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseParserService {
    private String intent = "", keyWord = "";

    private final ResponseHandlerController responseHandlerController;

    @Autowired
    public ResponseParserService(ResponseHandlerController responseHandlerController) {

        this.responseHandlerController = responseHandlerController;
    }

    void setResponse(JSONObject response) {
        intent = "";
        keyWord = "";
        try {
            parseQuery(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseQuery(JSONObject query) throws JSONException {

        Iterator it = query.getJSONObject("entities").keys();

        while(it.hasNext()) {
            switch (it.next().toString()) {
                case "intent":
                    intent = query.getJSONObject("entities").getJSONArray("intent").getJSONObject(0).getString("value");
                    break;
                case "mat":
                    keyWord = query.getJSONObject("entities").getJSONArray("mat").getJSONObject(0).getString("value");
                    break;
                case "plats":
                    keyWord = query.getJSONObject("entities").getJSONArray("plats").getJSONObject(0).getString("value");
                    break;
                case "droopy":
                    keyWord = query.getJSONObject("entities").getJSONArray("droopy").getJSONObject(0).getString("value");
                    break;
                case "mikro":
                    keyWord = query.getJSONObject("entities").getJSONArray("mikro").getJSONObject(0).getString("value");
                    break;
                case "number":
                    keyWord = Integer.toString(query.getJSONObject("entities").getJSONArray("number").getJSONObject(0).getInt("value"));
                    break;
            }
        }
        System.out.println(keyWord);
        responseHandlerController.setIntentAndKeyWord(intent, keyWord);
        responseHandlerController.getResponse();

    }
}
