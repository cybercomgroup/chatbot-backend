package Application.controller;

import Application.pojo.ResponsePojo;
import Application.service.WitaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The URL the program gets, maps based on path in that URL to this class and method.
 * Takes the requestParameter from the URL as parameter to the method.
 * Request method then sends the parameter to WitAiService.
 *
 * @version 2017-12-06
 * @author Joakim Willard
 * @author Viktor Albihn
 */

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
