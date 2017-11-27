package Application.service;

import Application.pojo.ResponsePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseHandlerService {
    private String intent, keyWord;

    @Autowired
    private PublicTransportService pts;

    @Autowired
    private ResponsePojo responsePojo;

    public ResponseHandlerService() {
    }

    public void setIntentAndKeyWord(String intent, String keyWord) {
        this.intent = intent;
        this.keyWord = keyWord;
    }

    public void getResponse() {

        responsePojo.setResponse1(null);
        responsePojo.setResponse2(null);
        responsePojo.setResponse3(null);

        switch(intent) {
            case "locate":
                switch (keyWord) {
                    case "pizza":
                        responsePojo.setResponse1("Nemos!");
                        break;
                    case "kaffe":
                        responsePojo.setResponse1("Kokboken");
                        responsePojo.setResponse2("Pressbyrån");
                        responsePojo.setResponse3("Café blå");
                        break;
                    case "thai":
                        responsePojo.setResponse1("Thaistugan!");
                        break;
                    case "alkohol":
                        responsePojo.setResponse1("FT");
                        responsePojo.setResponse2("11an");
                        responsePojo.setResponse3("Gasquen");
                        break;
                    case "FT":
                        responsePojo.setResponse1("Svea källarvåning");
                        break;
                    case "11an":
                        responsePojo.setResponse1("På hörnet av Svea");
                        break;
                    case "Gasquen":
                        responsePojo.setResponse1("Under kyrkan på campus Johanneberg");
                        break;

                }
                break;

            case "tid": {
                switch (keyWord) {
                    case "Gasquen":
                        responsePojo.setResponse1("22:00!");
                        break;
                    case "11an":
                        responsePojo.setResponse1("15:00!");
                        break;
                    case "16":
                        pts.setBus("16");
                        pts.getDepature();
                        break;
                    case "55":
                        pts.setBus("55");
                        pts.getDepature();
                        break;
                }
                break;



            }

            default: responsePojo.setResponse1("Jag förstod inte din fråga");


        }
    }


}