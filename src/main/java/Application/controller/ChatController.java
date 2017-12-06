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

    private final WitaiService was;

    private final ResponsePojo responsePojo;

    @Autowired
    public ChatController(WitaiService was, ResponsePojo responsePojo) {
        this.was = was;
        this.responsePojo = responsePojo;
    }

    @RequestMapping(value = "/request")
    public ResponsePojo Request(@RequestParam(value = "request") String request) {
        was.setPhrase(request);

        return responsePojo;
    }
}
