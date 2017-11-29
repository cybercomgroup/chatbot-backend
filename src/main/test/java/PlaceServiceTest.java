import Application.service.*;
import Application.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PlaceServiceTest.PlaceServiceTestConfiguration.class})
public class PlaceServiceTest {

    private PlaceService placeService;


    @Autowired
    public void init(PlaceService placeService){
        this.placeService = placeService;
    }

    @Test
    public void placeResponseTest(){

        placeService.placeResponse("pizza");

        assertEquals(responsePojo.getResponse1(), "Kapten Nemo's Resturang Pizzeria");

    }


    @Configuration
    public class PlaceServiceTestConfiguration{

        @Bean
        ResponsePojo responsePojo(){
            return new ResponsePojo();
        }

        @Bean
        PlaceService placeService(){
            return new PlaceService();
        }
    }
}
