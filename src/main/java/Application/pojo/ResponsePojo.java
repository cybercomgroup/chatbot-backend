package Application.pojo;

import org.springframework.stereotype.Component;

@Component
public class ResponsePojo {

    private String response1;
    private String response2;
    private String response3;


    public void setResponse1(String response1) {
        this.response1 = response1;
    }

    public void setResponse2(String response2) {
        this.response2 = response2;
    }

    public void setResponse3(String response3) {
        this.response3 = response3;
    }

    public String getResponse1() {
        return response1;
    }

    public String getResponse2() {
        return response2;
    }

    public String getResponse3() {
        return response3;
    }
}
