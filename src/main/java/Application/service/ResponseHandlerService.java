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

        responsePojo.setResponse(null);

        switch(intent) {
            case "locate":
                switch (keyWord) {
                    case "pizza":
                        placeService.placeResponse("pizza");
                        break;
                    case "kaffe":
                        placeService.placeResponse("kaffe");
                        break;
                    case "thai":
                        placeService.placeResponse("thai");
                        break;
                    case "alkohol":
                        responsePojo.setResponse("Det finns många flytande delikatesser vid: \n11:an \nGasquen \neller FT");
                        break;
                    case "FT":
                        responsePojo.setResponse("Svea källarvåning");
                        break;
                    case "11an":
                        responsePojo.setResponse("På hörnet av Svea");
                        break;
                    case "Gasquen":
                        responsePojo.setResponse("Förbi kårhuset på Johanneberg");
                        break;
                    case "mikro":
                        responsePojo.setResponse("Mirkovågsugnar finns vid: \nLoungen, tredje våningen, "
                                + "Jupiter \nLunchrummet, källarvåning, Jupiter \nFT, källarvåning, Svea");
                        break;
                    case "droopy": responsePojo.setResponse("Droopy säger voff!");
                        break;
                    default:
                        if (keyWord.equals(null) || !keyWord.equals("")) {
                            placeService.placeResponse(keyWord);
                        } else {
                            responsePojo.setResponse("Jag förstod inte din fråga");
                        }


                }
                break;

            case "tid": {
                switch (keyWord) {
                    case "Gasquen":
                        responsePojo.setResponse("22:00!");
                        break;
                    case "11an":
                        responsePojo.setResponse("15:00!");
                        break;


                    default:
                        if(keyWord.chars().allMatch(Character::isDigit)/*keyWord.matches("-?\\d+(\\.\\d+)?")*/) {
                            pts.setBus(keyWord);
                            pts.getDepature();
                        }else{
                            responsePojo.setResponse("Det finns inga tider för " + keyWord);
                        }

                }
                break;
            }

            case "special":
                switch (keyWord) {
                    case "H":
                        responsePojo.setResponse("H!");
                        break;
                    case "cerise":
                        responsePojo.setResponse("Cerise!");
                        break;
                    case  "droopy":
                        responsePojo.setResponse("Droopy!");
                        break;
                    case  "från h":
                        responsePojo.setResponse("När vi från H!");
                        break;
                    case  "marsch":
                        responsePojo.setResponse("Marscherar in!");
                        break;
                }
                break;

            case "info":
                switch (keyWord) {
                    case "kokboken":
                        kokbokenService.getMenu();
                        break;
                    default: responsePojo.setResponse("Det finns ingen information om " + keyWord);
                }
                break;
            case "mat":
                placeService.placeResponse("mat");
                break;

            default: responsePojo.setResponse("Jag förstod inte din fråga");
        }
    }
}