import Application.service.PlaceService;
import org.junit.Before;
import org.junit.Test;

public class PlaceServiceTest {


    @Before
    public void init(){

    }

    @Test
    public void placeResponseTest(){

        String pizza = "pizza+Lindholmen";
        String library = "bibliotek+Lindholmen";

        PlaceService placeService = new PlaceService();
        placeService.placeResponse(pizza);


    }
}
