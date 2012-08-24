package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import smartpool.service.BuddyService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class JoinCarPoolControllerTest {

    JoinCarPoolController joinCarPoolController;
    @Before
    public void setup()
    {
      BuddyService buddyService=mock(BuddyService.class);
     joinCarPoolController=new JoinCarPoolController(buddyService);
    }


    @Test
    public void shouldReturnView(){
        ModelMap model = new ModelMap();
        assertThat(joinCarPoolController.joinCarPool("1", model),equalTo("carpool/JoinRequest"));
    }

}
