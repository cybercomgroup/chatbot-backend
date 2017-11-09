package Application.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import Application.service.*;

@RestController
@RequestMapping(value = "/chatbot")
public class ChatController {

    private String request;

    @RequestMapping(value = "/request")
    public @ResponseBody
    JSONObject Request(@RequestParam(value = "request") String request){
        this.request = request;
        WitaiService witaiService = new WitaiService();
        witaiService.setPhrase(this.request);
        JSONObject jobj = witaiService.getJsonResponse();
        return jobj;
    }


}
