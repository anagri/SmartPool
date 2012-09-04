package smartpool.web;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import smartpool.domain.Buddy;
import smartpool.domain.JoinRequest;
import smartpool.service.BuddyService;
import smartpool.service.JoinRequestService;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class JoinCarPoolControllerTest {

    JoinCarPoolController joinCarPoolController;
    ModelMap model;

    @Mock
    private JoinRequestService joinRequestService;
    @Mock
    private BuddyService buddyService;
    @Mock
    private HttpServletRequest request;

    @Before
    public void setup() {
        initMocks(this);
        joinCarPoolController = new JoinCarPoolController(buddyService, joinRequestService);
        model = new ModelMap();
    }

    @Test
    public void shouldReturnView() {
        String buddyUserName = "ishak";
        when(buddyService.getUserNameFromCAS(request)).thenReturn(buddyUserName);
        when(buddyService.getBuddy(buddyUserName)).thenReturn(new Buddy(buddyUserName));

        String userDetails = joinCarPoolController.getUserDetails("1", model, request);
        assertThat(userDetails, equalTo("carpool/joinRequest"));
        assertThat((String) model.get("casUserName"), equalTo(buddyUserName));
        assertThat((Buddy) model.get("buddy"), equalTo(new Buddy(buddyUserName)));
    }

    @Test
    public void shouldReturnViewForABuddy_WhoHasAlreadyRequestedToJoinACarpool() {
        String buddyUserName = "ishak";
        String carpoolName = "1";
        Buddy isha = new Buddy(buddyUserName);

        when(buddyService.getUserNameFromCAS(request)).thenReturn(buddyUserName);
        when(buddyService.getBuddy(buddyUserName)).thenReturn(isha);
        when(joinRequestService.isRequestSent(isha, carpoolName)).thenReturn(true);

        joinCarPoolController.getUserDetails(carpoolName, model, request);
        assertThat((Boolean) model.get("isRequestSent"), equalTo(true));
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        JoinRequest joinRequest = new JoinRequest("ishak", "carpool-1", "here", new LocalTime("08:30"));
        String expectedURL = joinCarPoolController.submitUserDetails("carpool-1", joinRequest, model);
        assertEquals(expectedURL, "redirect:../../carpool/carpool-1");

        verify(joinRequestService).sendJoinRequest(joinRequest);
    }
}
