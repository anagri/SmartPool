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
    private String buddyUserName;
    private Buddy testUser;
    private String carpoolName;


    @Before
    public void setup() {
        initMocks(this);
        joinCarPoolController = new JoinCarPoolController(buddyService, joinRequestService, carpoolService, new JoinRequestFormValidator());
        model = new ModelMap();
        buddyUserName = "test.twu";
        carpoolName = "carpool-2";
        testUser = new Buddy(buddyUserName);
        when(buddyService.getUserNameFromCAS(request)).thenReturn(buddyUserName);
        when(buddyService.getBuddy(buddyUserName)).thenReturn(testUser);
        when(carpoolService.getByName(carpoolName)).thenReturn(new Carpool(carpoolName));


    }

    @Test
    public void shouldReturnView() {

        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(false);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(true);

        String userDetails = joinCarPoolController.getUserDetails(carpoolName, model, request);
        assertThat(userDetails, equalTo("carpool/joinRequest"));
        assertThat((Buddy) model.get("buddy"), equalTo(new Buddy(buddyUserName)));
    }

    @Test
    public void shouldReturnViewForABuddy_WhoHasAlreadyRequestedToJoinACarpool() {

        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(true);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(false);

        String view = joinCarPoolController.getUserDetails(carpoolName, model, request);
        assertThat(view, is("redirect:/carpool/search"));
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {

        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(false);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(true);

        JoinRequestForm joinRequest = new JoinRequestForm(buddyUserName, carpoolName, "address", "999999999", "abc", "");
        ModelAndView expectedURL = joinCarPoolController.submitUserDetails(carpoolName, joinRequest, new BindException(joinRequest, "joinRequest"), request);

        assertThat(expectedURL.getView(), instanceOf(RedirectView.class));
        assertThat(((RedirectView) expectedURL.getView()).getUrl(), is("../../carpool/carpool-2"));
    }

    @Test
    public void testShouldRedirectToSearchWhenAccessingInvalidCarPool() throws Exception {


    }
}
