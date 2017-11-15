package Application.pojo;

import org.json.JSONObject;

public interface ResponseParser_interface {

    String getIntent();
    String getKeyWord();
    void setResponse(JSONObject response);
    void parseQuery(JSONObject query);
}