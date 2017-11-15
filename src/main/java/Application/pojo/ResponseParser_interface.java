public interface ResponseParser_interface {

    public String getIntent();
    public String getKeyWord();
    public void setResponse(JSONObject response);
    private void parseQuery(JSONObject query);
}