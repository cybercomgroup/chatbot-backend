package Application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chatbot")
public class ChatController {

    private String request;

    @RequestMapping(value = "/request")
    public void Request(@RequestParam(value = "request") String request){
        this.request = request;
        Application.service.WitAiService.setPhrase(this.request);
    }


}
