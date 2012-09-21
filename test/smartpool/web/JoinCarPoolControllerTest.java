package smartpool.web;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import smartpool.domain.*;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolBuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.JoinRequestService;
import smartpool.web.form.JoinRequestForm;
import smartpool.web.form.JoinRequestFormValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
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
    @Mock
    private CarpoolBuddyService carpoolBuddyService;
    @Mock
    private JoinRequestForm joinRequestForm;
    @Mock
    private BindingResult errors;
    @Mock
    private JoinRequestFormValidator validator;

    private String buddyUserName;
    private Buddy testUser;
    private String carpoolName;
    private CarpoolBuddy carpoolBuddy;
    private Carpool carpool;

    @Before
    public void setup() {
        initMocks(this);
        joinCarPoolController = new JoinCarPoolController(buddyService, joinRequestService,
                                carpoolService, validator,carpoolBuddyService);
        model = new ModelMap();
        buddyUserName = "test.twu";
        carpoolName = "carpool-2";
        testUser = new Buddy(buddyUserName);
        carpool = new Carpool(carpoolName, new LocalDate(), CabType.COMPANY, 100, new LocalTime(9, 0),
                    new LocalTime(), Status.ACTIVE, new ArrayList<CarpoolBuddy>(), 4, new ArrayList<String>());

        when(buddyService.getUserNameFromCAS(request)).thenReturn(buddyUserName);
        when(buddyService.getBuddy(buddyUserName)).thenReturn(testUser);
        when(carpoolService.getByName(carpoolName)).thenReturn(new Carpool(carpoolName));
    }

    @Test
    public void shouldReturnView() {
        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(false);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(true);

        assertThat(joinCarPoolController.getUserDetails(carpoolName, model, request), equalTo("carpool/joinRequest"));
        assertThat((Buddy) model.get("buddy"), equalTo(new Buddy(buddyUserName)));
    }

    @Test
    public void shouldReturnViewForABuddy_WhoHasAlreadyRequestedToJoinACarpool() {
        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(true);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(false);

        assertThat(joinCarPoolController.getUserDetails(carpoolName, model, request), is("redirect:/carpool/search"));
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        when(joinRequestForm.getPreferredPickupTime()).thenReturn("08:00");
        when(carpoolService.getByName(carpoolName)).thenReturn(carpool);
        when(joinRequestService.isRequestSent(testUser, carpoolName)).thenReturn(false);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName, carpoolName)).thenReturn(true);


        ModelAndView expectedURL = joinCarPoolController.submitUserDetails(carpoolName, joinRequestForm, new BindException(joinRequestForm, "joinRequest"), request);

        assertThat(expectedURL.getView(), instanceOf(RedirectView.class));
        assertThat(((RedirectView) expectedURL.getView()).getUrl(), is("../../carpool/carpool-2"));
    }

    @Test
    public void shouldReturnToJoinApprove() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String userName = "userName";
        when(joinRequestService.isUidPresent(uuid)).thenReturn(true);
        when(joinRequestService.getBuddyUserNameFromUid(uuid)).thenReturn(userName);
        String carpoolName = "carpoolName";
        when(joinRequestService.getCarpoolNameFromUid(uuid)).thenReturn(carpoolName);
        JoinRequest joinRequest = new JoinRequest(userName,carpoolName,"address","pickupPoint",new LocalTime());
        when(joinRequestService.getJoinRequestByUserNameAndCarpoolName(userName,carpoolName)).thenReturn(joinRequest);
        ModelAndView approveJoin=joinCarPoolController.approveJoinRequest(uuid);
        assertThat(approveJoin.getViewName(),equalTo("carpool/approve"));
        verify(joinRequestService).deletePendingRequest(uuid);
    }

    @Test
    public void shouldAddCarpoolBuddyOnApproval() throws Exception {
        String uuid = UUID.randomUUID().toString();

        String userName = "userName";
        when(joinRequestService.isUidPresent(uuid)).thenReturn(true);
        when(joinRequestService.getBuddyUserNameFromUid(uuid)).thenReturn(userName);
        String carpoolName = "carpoolName";
        when(joinRequestService.getCarpoolNameFromUid(uuid)).thenReturn(carpoolName);
        JoinRequest joinRequest = new JoinRequest(userName,carpoolName,"address","pickupPoint",new LocalTime());
        when(joinRequestService.getJoinRequestByUserNameAndCarpoolName(userName,carpoolName)).thenReturn(joinRequest);

        Carpool carpool = carpoolService.findCarpoolByName(carpoolName);
        carpoolBuddy = new CarpoolBuddy();
        Buddy buddy = new Buddy(userName);
        when(buddyService.getBuddy(userName)).thenReturn(buddy);
        CarpoolBuddy carpoolBuddy = new CarpoolBuddy(buddy,joinRequest.getPickupPoint(),joinRequest.getPreferredPickupTime());

        ModelAndView modelAndView = joinCarPoolController.approveJoinRequest(uuid);
        verify(carpoolBuddyService).insert(carpoolBuddy, carpool);
        assertThat((Boolean)modelAndView.getModel().get("approve"),is(true));
    }

    @Test
    public void shouldDisapproveCarpoolJoinRequest() throws Exception {
        String uid = UUID.randomUUID().toString();
        when(joinRequestService.isUidPresent(uid)).thenReturn(true);
        ModelAndView modelAndView = joinCarPoolController.disapproveJoinRequest(uid);
        verify(joinRequestService).deletePendingRequest(uid);
        assertThat((Boolean)modelAndView.getModel().get("approve"),is(false));
    }

    @Test
    public void shouldShowErrorInfoJoinRequestWhenPickUpTimeAfterOAT() throws Exception {
        when(joinRequestForm.getPreferredPickupTime()).thenReturn("10:00");

        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        when(carpoolService.canUserSendRequest(buddyUserName,carpoolName)).thenReturn(true);
        when(carpoolService.getByName(carpoolName)).thenReturn(carpool);

        joinCarPoolController.submitUserDetails(carpoolName, joinRequestForm, errors, request);

        verify(errors).rejectValue("preferredPickupTime", "field.invalid");
    }
}
