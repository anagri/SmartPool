package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.JoinRequestService;
import smartpool.web.form.JoinRequestForm;
import smartpool.web.form.JoinRequestFormValidator;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
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
    @Mock
    private CarpoolService carpoolService;


    @Before
    public void setup() {
        initMocks(this);
        joinCarPoolController = new JoinCarPoolController(buddyService, joinRequestService, carpoolService, new JoinRequestFormValidator());
        model = new ModelMap();
    }

    @Test
    public void shouldReturnView() {
        String buddyUserName = "test.twu";
        String carpoolName = "carpool-2";
        Buddy testUser = new Buddy(buddyUserName);

        when(buddyService.getUserNameFromCAS(request)).thenReturn(buddyUserName);
        when(buddyService.getBuddy(buddyUserName)).thenReturn(testUser);
        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(false);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(true);
        when(carpoolService.getByName("carpool-2")).thenReturn(new Carpool("carpool-2"));

        String userDetails = joinCarPoolController.getUserDetails("carpool-2", model, request);
        assertThat(userDetails, equalTo("carpool/joinRequest"));
        assertThat((Buddy) model.get("buddy"), equalTo(new Buddy(buddyUserName)));
    }

    @Test
    public void shouldReturnViewForABuddy_WhoHasAlreadyRequestedToJoinACarpool() {
        String buddyUserName = "test.twu";
        String carpoolName = "carpool-2";
        Buddy testUser = new Buddy(buddyUserName);

        when(buddyService.getUserNameFromCAS(request)).thenReturn(buddyUserName);
        when(buddyService.getBuddy(buddyUserName)).thenReturn(testUser);
        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(true);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(false);
        when(carpoolService.getByName("carpool-2")).thenReturn(new Carpool("carpool-2"));

        String view = joinCarPoolController.getUserDetails(carpoolName, model, request);
        assertThat(view, is("redirect:/carpool/search"));
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        String buddyUserName = "test.twu";
        String carpoolName = "carpool-2";
        Buddy testUser = new Buddy(buddyUserName);

        when(buddyService.getUserNameFromCAS(request)).thenReturn(buddyUserName);
        when(buddyService.getBuddy(buddyUserName)).thenReturn(testUser);
        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(false);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(true);
        when(carpoolService.getByName("carpool-2")).thenReturn(new Carpool("carpool-2"));

        JoinRequestForm joinRequest = new JoinRequestForm(buddyUserName, "carpool-2", "here", "09:30", "address", "999999999");
        ModelAndView expectedURL = joinCarPoolController.submitUserDetails("carpool-2", joinRequest, new BindException(joinRequest, "joinRequest"), request);

        assertThat(expectedURL.getView(), instanceOf(RedirectView.class));
        assertThat(((RedirectView) expectedURL.getView()).getUrl(), is("../../carpool/carpool-2"));
    }

    @Test
    public void testShouldRedirectToSearchWhenAccessingInvalidCarPool() throws Exception {
        


    }
}
