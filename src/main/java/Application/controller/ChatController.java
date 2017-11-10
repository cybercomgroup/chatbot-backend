package Application.controller;

import Application.pojo.ResponseParser_pojo;
import org.json.JSONObject;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import Application.service.*;

@RestController
@RequestMapping(value = "/chatbot")
public class ChatController {

    @Autowired
    private WitaiService was;
    private ResponseParser_pojo responseParserPojo = new ResponseParser_pojo();


    private String request;

    @RequestMapping(value = "/request")
    public @ResponseBody
    JSONObject Request(@RequestParam(value = "request") String request) {
        this.request = request;
        was.setPhrase(this.request);
        /* Psudo kod
           Här ska det typ vara
         */

        JSONObject jobj = was.getJsonResponse();
        responseParserPojo.setResponse(jobj);

        return jobj;
    }
}
