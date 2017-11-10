package Application.controller;

<<<<<<< HEAD
import org.json.JSONArray;
import org.json.JSONObject;
=======
>>>>>>> origin/feature/ChatController
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD
import Application.service.*;
=======
>>>>>>> origin/feature/ChatController

@RestController
@RequestMapping(value = "/chatbot")
public class ChatController {

    private String request;

    @RequestMapping(value = "/request")
<<<<<<< HEAD
    public @ResponseBody
    JSONObject Request(@RequestParam(value = "request") String request){
        this.request = request;
        WitaiService witaiService = new WitaiService();
        witaiService.setPhrase(this.request);
        JSONObject jobj = witaiService.getJsonResponse();
        return jobj;
=======
    public void Request(@RequestParam(value = "request") String request){
        this.request = request;
        Application.service.WitAiService.setPhrase(this.request);
>>>>>>> origin/feature/ChatController
    }


}
