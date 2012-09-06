package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import smartpool.domain.Buddy;
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
        String buddyUserName = "ishak";
        when(buddyService.getUserNameFromCAS(request)).thenReturn(buddyUserName);
        when(buddyService.getBuddy(buddyUserName)).thenReturn(new Buddy(buddyUserName));

        String userDetails = joinCarPoolController.getUserDetails("1", model, request);
        assertThat(userDetails, equalTo("carpool/joinRequest"));
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
        JoinRequestForm joinRequest = new JoinRequestForm("ishak", "carpool-1", "here", "09:30", null, null);
        ModelAndView expectedURL = joinCarPoolController.submitUserDetails("carpool-1", joinRequest, new BindException(joinRequest, "joinRequest"), request);

        assertThat(expectedURL.getView(), instanceOf(RedirectView.class));
        assertThat(((RedirectView) expectedURL.getView()).getUrl(), is("../../carpool/carpool-1"));
    }
}
