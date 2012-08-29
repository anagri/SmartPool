package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import smartpool.domain.JoinRequest;
import smartpool.service.BuddyService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class JoinCarPoolControllerTest {

    JoinCarPoolController joinCarPoolController;
    ModelMap model;
    @Before
    public void setup()
    {
      BuddyService buddyService=mock(BuddyService.class);
     joinCarPoolController=new JoinCarPoolController(buddyService);
         model = new ModelMap();
    }

    @Test
    public void shouldReturnView(){

        assertThat(joinCarPoolController.getUserDetails("1", model, null),equalTo("carpool/joinRequest"));
    }
    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        assertEquals(joinCarPoolController.submitUserDetails("carpool-1",new JoinRequest("ishak","carpool-1","here","now"),model), "redirect:../../carpool/carpool-1");
    }

}
