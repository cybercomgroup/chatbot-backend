package Application.controller;

import org.springframework.web.bind.annotation.RequestParam;

public interface Controller_interface {

    /*@ResponseBody
    JSONObject*/ String Request(@RequestParam(value = "request") String request);

}
