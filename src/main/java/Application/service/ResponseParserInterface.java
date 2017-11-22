package Application.service;

import net.minidev.json.JSONObject;

public interface ResponseParserInterface {

    public String getIntent();
    public String getKeyWord();
    public void setResponse(JSONObject response);
    void parseQuery(JSONObject query);
}