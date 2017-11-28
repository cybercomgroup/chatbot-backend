package Application.controller;

import Application.pojo.ResponsePojo;
import Application.service.ResponseHandlerService;
import Application.service.ResponseParserService;
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
    private ResponseParserService responseParserService /*= new ResponseParser_pojo()*/;

    @Autowired
    private ResponseHandlerService responseHandlerService;

    @Autowired
    private ResponsePojo responsePojo;

    private String request;

    @RequestMapping(value = "/request")
    public /*@ResponseBody
    JSONObject*/ ResponsePojo Request(@RequestParam(value = "request") String request) {
        this.request = request;
        was.setPhrase(this.request);

        JSONObject jobj = was.getJsonResponse();
        responseParserService.setResponse(jobj);
        responseHandlerService.getResponse();

        return responsePojo;
       // return "\""+responseHandlerService.getResponse()+"\"";
    }
}
