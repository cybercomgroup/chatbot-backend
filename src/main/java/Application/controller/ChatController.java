package Application.controller;

import Application.pojo.ResponsePojo;
import Application.service.WitaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chatbot")
public class ChatController {

    @Autowired
    private WitaiService was;

    @Autowired
    private ResponsePojo responsePojo;

    private String request;

    @RequestMapping(value = "/request")
    public ResponsePojo Request(@RequestParam(value = "request") String request) {
        this.request = request;
        was.setPhrase(this.request);

        return responsePojo;
    }
}
