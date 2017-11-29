import Application.service.*;
import Application.pojo.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlaceServiceTest {


    @Before
    public void init(){

    }

    @Test
    public void placeResponseTest(){
        String pizza = "pizza";

        ResponsePojo responsePojo = new ResponsePojo();
        PlaceService placeService = new PlaceService();
        placeService.placeResponse("pizza");

        assertTrue(responsePojo.getResponse1().equals("Kapten Nemo's Resturang Pizzeria") ||
                responsePojo.getResponse2().equals("Kapten Nemo's Resturang Pizzeria")||
                responsePojo.getResponse3().equals("Kapten Nemo's Resturang Pizzeria")
        );

    }
}
