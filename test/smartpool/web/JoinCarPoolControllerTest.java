package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import smartpool.domain.JoinRequest;
import smartpool.service.BuddyService;
import smartpool.service.JoinRequestService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JoinCarPoolControllerTest {

    JoinCarPoolController joinCarPoolController;
    ModelMap model;
    private BuddyService buddyService;
    private JoinRequestService joinRequestService;

    @Before
    public void setup() {
        buddyService = mock(BuddyService.class);
        joinRequestService = mock(JoinRequestService.class);
        joinCarPoolController = new JoinCarPoolController(buddyService, joinRequestService);
        model = new ModelMap();
    }

    @Test
    public void shouldReturnView() {
        assertThat(joinCarPoolController.getUserDetails("1", model, null), equalTo("carpool/joinRequest"));
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        JoinRequest joinRequest = new JoinRequest("ishak", "carpool-1", "here", "now");
        String expectedURL = joinCarPoolController.submitUserDetails("carpool-1", joinRequest, model);
        assertEquals(expectedURL, "redirect:../../carpool/carpool-1");

        verify(joinRequestService).sendJoinRequest(joinRequest);
    }
}
