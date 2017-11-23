package Application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseHandlerService {
    private String intent, keyWord;

    @Autowired
    private publicTransportService pts;

    public ResponseHandlerService() {
    }

    public void setIntentAndKeyWord(String intent, String keyWord) {
        this.intent = intent;
        this.keyWord = keyWord;
    }

    public String getResponse() {
        switch(intent) {
            case "locate":
                switch (keyWord) {
                    case "pizza": return "Nemos!";
                    case "kaffe": return "Kokboken, Pressbyrån, Cafe blå";
                    case "thai": return "Thaistugan!";
                    case "alkohol": return "FT, 11an, Gasquen";
                    case "FT": return "Svea källarvåing";
                    case "11an": return "På hörnet av Svea";
                    case "Gasquen": return "Under kyrkan på campus Johanneberg";

                }

            case "tid": {
                switch (keyWord) {
                    case "Gasquen": return "22:00!";
                    case "11an": return "15:00!";
                    case "16": return pts.getDepature();

                }



            }

            case "": return "Jag förstod inte din fråga";

        }
        return "Jag förstod inte din fråga";
    }

// IntentResolveService
}