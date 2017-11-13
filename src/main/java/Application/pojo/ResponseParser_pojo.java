package Application.pojo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import org.springframework.stereotype.Component;

@Component
public class ResponseParser_pojo {
    private JSONObject response;
    private String intent = "", keyWord = "";

    @Autowired
    private ResponseHandler_pojo responseHandlerPojo;

    public ResponseParser_pojo() {

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

            }
        }
        ResponseHandler_pojo.setIntentAndKeyWord(intent, keyWord);

    }
}
