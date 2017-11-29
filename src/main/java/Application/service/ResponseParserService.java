package Application.service;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseParserService {
    private JSONObject response;
    private String intent = "", keyWord = "";

    private final ResponseHandlerService responseHandlerService;

    @Autowired
    public ResponseParserService(ResponseHandlerService responseHandlerService) {

        this.responseHandlerService = responseHandlerService;
    }

    public String getIntent() {
        return intent;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
        intent = "";
        keyWord = "";
        try {
            parseQuery(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseQuery(JSONObject query) throws JSONException {
        Iterator<String> it = query.getJSONObject("entities").keys();

        while(it.hasNext()) {
            switch (it.next()) {
                case "intent":
                    intent = query.getJSONObject("entities").getJSONArray("intent").getJSONObject(0).getString("value");
                    break;
                case "mat":
                    keyWord = query.getJSONObject("entities").getJSONArray("mat").getJSONObject(0).getString("value");
                    break;
                case "v_sttrafik":
                    keyWord = query.getJSONObject("entities").getJSONArray("v_sttrafik").getJSONObject(0).getString("value");
                    break;
                case "plats":
                    keyWord = query.getJSONObject("entities").getJSONArray("plats").getJSONObject(0).getString("value");
                    break;



            }
        }
        responseHandlerService.setIntentAndKeyWord(intent, keyWord);
        responseHandlerService.getResponse();

    }
}
