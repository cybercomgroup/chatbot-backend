package Application.service;

import Application.pojo.ResponsePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseHandlerService {
    private String intent, keyWord;

    private final PublicTransportService pts;

    private final ResponsePojo responsePojo;

    private final PlaceService placeService;

    private final KokbokenService kokbokenService;

    @Autowired
    public ResponseHandlerService(PublicTransportService pts, ResponsePojo responsePojo,
            PlaceService placeService, KokbokenService kokbokenService) {
        this.pts = pts;
        this.responsePojo = responsePojo;
        this.placeService = placeService;
        this.kokbokenService = kokbokenService;
    }

    void setIntentAndKeyWord(String intent, String keyWord) {
        this.intent = intent;
        this.keyWord = keyWord;
    }

    void getResponse() {

        responsePojo.setResponse1(null);
        responsePojo.setResponse2(null);
        responsePojo.setResponse3(null);

        switch(intent) {
            case "locate":
                switch (keyWord) {
                    case "pizza":
                        placeService.placeResponse("pizza");
                        break;
                    case "kaffe":
                        responsePojo.setResponse1("Kokboken");
                        responsePojo.setResponse2("Pressbyrån");
                        responsePojo.setResponse3("Café Norra Älvstranden");
                        break;
                    case "thai":
                        placeService.placeResponse("thai");
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
                    case "Micro":
                        responsePojo.setResponse1("Loungen, tredje våningen, Jupiter");
                        responsePojo.setResponse2("Lunchrum, källarvåning, Jupiter");
                        responsePojo.setResponse3("FT, källarvåning, Svea");
                        break;
                    default: responsePojo.setResponse1("Kan inte hitta " + keyWord);

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

                    default: responsePojo.setResponse1("Det finns inga tider för " + keyWord);
                }
                break;





            }

            case "info":
                switch (keyWord) {
                    case "kokboken":
                        kokbokenService.getMenu();
                        break;
                    default: responsePojo.setResponse1("Det finns ingen information om " + keyWord);
                }
                break;

            default: responsePojo.setResponse1("Jag förstod inte din fråga");
        }
    }
}