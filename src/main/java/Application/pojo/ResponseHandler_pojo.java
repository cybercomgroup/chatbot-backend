package Application.pojo;

@Component
public class ResponseHandler_pojo {
    private String intent, keyWord;

    public ResponseHandler_pojo () {
    }

    public void setIntentAndKeyWord(String intent, String keyWord) {
        this.intent = intent;
        this.keyWord = keyWord;
    }

    public String getRespone() {
        switch(intent) {
            case "locate":
                switch (keyWord) {
                    case "pizza": return "Nemos!";
                    case "kaffe": return "Kokboken, Pressbyrån, Cafe blå";
                    case "thai": return "Thaistugan!";
                }

            case "tid": {
                switch (keyWord) {
                    case "Gasquen": return "22:00!";
                    case "11an": return "15:00!";
                }

            }
            case null: return "Jag förstod inte din fråga";

        }
    }


}