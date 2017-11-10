package Application.controller;

import Application.pojo.ResponseParser_pojo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Application.service.*;

@RestController
@RequestMapping(value = "/chatbot")
public class ChatController {

    @Autowired
    private WitaiService was;

    @Autowired
    private ResponseParser_pojo responseParserPojo /*= new ResponseParser_pojo()*/;


    private String request;

    @RequestMapping(value = "/request")
    public /*@ResponseBody
    JSONObject*/ String Request(@RequestParam(value = "request") String request) {
        this.request = request;
        was.setPhrase(this.request);

        JSONObject jobj = was.getJsonResponse();
        responseParserPojo.setResponse(jobj);

        return responseParserPojo.getIntent() + " " + responseParserPojo.getKeyWord();
    }
}
