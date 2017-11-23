package Application.service;

import org.json.JSONObject;

public interface ResponseParserInterface {

    public String getIntent();
    public String getKeyWord();
    public void setResponse(JSONObject response);
    void parseQuery(JSONObject query);
}