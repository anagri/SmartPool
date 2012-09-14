package smartpool.web;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import smartpool.builder.CarpoolBuilder;
import smartpool.common.Constants;
import smartpool.domain.*;
import smartpool.service.*;
import smartpool.web.form.CarpoolUpdateForm;
import smartpool.web.form.CarpoolUpdateFormValidator;
import smartpool.web.form.CreateCarpoolForm;
import smartpool.web.form.CreateCarpoolFormValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class CarpoolControllerTest {

    private CarpoolController carpoolController;
    @Mock
    private CarpoolService carpoolService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private BuddyService buddyService;
    @Mock
    private RouteService routeService;
    @Mock
    private CreateCarpoolFormValidator createCarpoolFormValidator;
    @Mock
    private CreateCarpoolForm createCarpoolForm;
    @Mock
    private BindingResult errors;
    @Mock
    private JoinRequestService joinRequestService;
    @Mock
    private Properties appProperties;
    private CarpoolUpdateFormValidator updateValidator;
    @Mock
    private CarpoolBuddyService carpoolBuddyService;
    @Mock
    private MailService mailService;

    private ModelMap model;
    private Carpool expectedCarpool = CarpoolBuilder.CARPOOL_1;
    private ArrayList<Carpool> defaultCarpools;
    private final CarpoolBuddy testBuddy = new CarpoolBuddy(new Buddy("testBuddy"),"location",new LocalTime(10,30));
    private List<String> defaultRouteLocations;
    private String carpoolName;

    public CarpoolControllerTest() {
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        updateValidator = new CarpoolUpdateFormValidator();
        carpoolController = new CarpoolController(carpoolService, joinRequestService, buddyService, routeService, createCarpoolFormValidator, mailService, carpoolBuddyService, appProperties, updateValidator);
        when(carpoolService.getByName("carpool")).thenReturn(expectedCarpool);
        model = new ModelMap();

        defaultCarpools = new ArrayList<Carpool>() {{
            add(expectedCarpool);
        }};
        when(carpoolService.findAllCarpoolsByLocation("Diamond District")).thenReturn(defaultCarpools);

        when(buddyService.getCurrentBuddy(request)).thenReturn(testBuddy.getBuddy());

        when(request.getParameter("query")).thenReturn("Diamond District");

        when(createCarpoolForm.getDomainObject(testBuddy.getBuddy())).thenReturn(expectedCarpool);

        defaultRouteLocations = Arrays.asList("Diamond District");
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        String carpoolName = "carpool";
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        assertEquals(carpoolController.viewCarpool(carpoolName, model, request), "carpool/view");
    }

    @Test
    public void shouldHaveCarpoolInstanceInModel() throws Exception {
        String carpoolName = "carpool";
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        carpoolController.viewCarpool(carpoolName, model, request);
        Carpool carpoolActual = (Carpool) model.get("carpool");

        assertEquals(expectedCarpool, carpoolActual);
    }

    @Test
    public void shouldSearchForCarpool() {
        carpoolController.searchByLocation(model, request);
        List<Carpool> searchResult = (List<Carpool>) model.get("searchResult");
        assertThat(searchResult, hasItems(expectedCarpool));
    }

    @Test
    public void shouldRedirectToViewSearchCarpool() throws Exception {
        assertThat(carpoolController.searchByLocation(model, request), equalTo("carpool/search"));
    }

    @Test
    public void shouldRedirectToCreateCarpool() throws Exception {
        assertThat(carpoolController.create(), equalTo("carpool/create"));
    }

    @Test
    public void shouldRedirectToViewCarpoolWhenPostedOnCreate() {
        assertThat(carpoolController.create(new CreateCarpoolForm("from", "to", "15/06/2012", "pickupPoint", "9:00", "PERSONAL", "0", "10:00", "18:00", "Kormangla"), errors, model, request), equalTo("redirect:/carpool/from - to"));
    }

    @Test
    public void shouldInsertIntoDBWhenPostedOnCreate() throws Exception {
        ArrayList<CarpoolBuddy> carpoolBuddies = new ArrayList<CarpoolBuddy>();
        carpoolBuddies.add(new CarpoolBuddy(testBuddy.getBuddy(), "pickupPoint", new LocalTime(9, 0)));

        ArrayList<String> routePoints = new ArrayList<String>();
        routePoints.add("Kormangla");
        routePoints.add("Domlur");

        Carpool carpool = new Carpool("from - to", new LocalDate(2012, 6, 15), CabType.PERSONAL, 0, new LocalTime(10, 0), new LocalTime(18, 0), Status.NOT_STARTED, carpoolBuddies, 0, routePoints);
        carpoolController.create(new CreateCarpoolForm("from", "to", "15/06/2012", "pickupPoint", "9:00", "PERSONAL", "0", "10:00", "18:00", "Kormangla, Domlur"), errors, model, request);

        verify(carpoolService).insert(carpool);
    }

    @Test
    public void shouldDisplayAllCarpoolsIfQueryIsNull() {
        when(carpoolService.findAllCarpoolsByLocation(null)).thenReturn(defaultCarpools);
        when(request.getParameter("query")).thenReturn(null);
        carpoolController.searchByLocation(model, request);
        assertThat((ArrayList<Carpool>) model.get("searchResult"), is(defaultCarpools));
    }

    @Test
    public void shouldAddCurrentBuddyToCarpoolWhileCreating() throws Exception {
        when(createCarpoolForm.getDomainObject(testBuddy.getBuddy())).thenReturn(new Carpool("testName"));
        carpoolController.create(createCarpoolForm, errors, model, request);
        verify(createCarpoolForm).getDomainObject(testBuddy.getBuddy());
    }

    @Test
    public void shouldValidateFormBeforeCreatingCarpool() throws Exception {
        carpoolController.create(createCarpoolForm, errors, model, request);
        verify(createCarpoolFormValidator).validate(createCarpoolForm, errors);
    }

    @Test
    public void shouldRedirectToCreatePageIfValidationHasErrors() throws Exception {
        when(errors.hasErrors()).thenReturn(true);
        String redirectPage = carpoolController.create(createCarpoolForm, errors, model, request);
        assertThat(redirectPage, is("carpool/create"));
    }

    @Test
    public void shouldGiveBackTheFormInModelWhenValidationHasErrors() throws Exception {
        when(errors.hasErrors()).thenReturn(true);
        carpoolController.create(createCarpoolForm, errors, model, request);
        assertThat((CreateCarpoolForm) model.get("createCarpoolForm"), is(createCarpoolForm));
    }

    @Test
    public void shouldGetAllRoutePoints() throws Exception {
        when(routeService.getAllLocation()).thenReturn(defaultRouteLocations);
        when(request.getParameter("query")).thenReturn(null);
        carpoolController.searchByLocation(model, request);
        assertThat((List<String>) model.get("routePoints"), is(defaultRouteLocations));
    }

    @Test
    public void shouldGetDashboardURL() throws Exception {
        CarpoolUpdateForm updateForm = new CarpoolUpdateForm("ACTIVE", "1000", "4");
        assertThat(carpoolController.viewDashboard(updateForm, model, request), is("admin/dashboard"));
    }

    @Test
    public void shouldGetCarpoolForDashboard() throws Exception {
        CarpoolUpdateForm updateForm = new CarpoolUpdateForm("ACTIVE", "1000", "3");
        carpoolController.viewDashboard(updateForm, model, request);
        assertThat((List<Carpool>) model.get("searchResult"), hasItem(expectedCarpool));
    }

    @Test
    public void shouldRedirectBackToAdminAfterUpdate() {
        CarpoolUpdateForm updateForm = new CarpoolUpdateForm(Status.ACTIVE.toString(), "300", "4");
        carpoolName = "any";
        when(carpoolService.getByName(carpoolName)).thenReturn(new Carpool(carpoolName));
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        ModelAndView expectedURL = carpoolController.updateCarpoolAttributes(carpoolName, updateForm, errors, model, request);

        assertThat(expectedURL.getView(), instanceOf(RedirectView.class));
    }

    @Test
    public void should() throws Exception {

    }

    @Test
    public void shouldDeleteBuddyFromCarpool() throws Exception {
        String carpoolName = "carpoolName";
        String buddyUserName = "buddyUserName";
        carpoolController.deleteBuddy(carpoolName, buddyUserName);
        verify(carpoolBuddyService).delete(carpoolName, buddyUserName);
    }

    @Test
    public void shouldRedirectToDashboardAfterDeletingBuddyFromCarpool() throws Exception {
        String carpoolName = "carpoolName";
        String buddyUserName = "buddyUserName";
        String s = carpoolController.deleteBuddy(carpoolName, buddyUserName);
        assertThat(s, is("redirect:/admin/dashboard"));
    }

    @Test
    public void shouldSendEmailAndSetRequestSentStatusToTrue() throws Exception {
        when(appProperties.getProperty(Constants.ADMIN_EMAIL)).thenReturn("yqhuang@thoughtworks.com");
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("localhost");
        when(request.getServerPort()).thenReturn(9090);
        when(request.getContextPath()).thenReturn("/smartpool");

        carpoolController.startCarpool("carpool-1", request);
        verify(carpoolService).updateRequestSent("carpool-1", true);
        String acceptLink = "http://localhost:9090/smartpool/carpool/carpool-1/acceptStartRequest";
        String rejectLink = "http://localhost:9090/smartpool/carpool/carpool-1/rejectStartRequest";
        verify(mailService).sendMailTo("yqhuang@thoughtworks.com", "Request to start carpool carpool-1", "Please start carpool-1<br> <a href='" + acceptLink + "'>Accept</a> <br> <a href='" + rejectLink + "'>Reject</a>");
    }

    @Test
    public void shouldSetCarpoolStatusToActiveWhenAcceptStartRequest() throws Exception {
        String carpoolName = "carpool-1";
        carpoolController.acceptStartRequest(carpoolName);

        verify(carpoolService).updateStatus(carpoolName, Status.ACTIVE);
    }

    @Test
    public void shouldSetRequestSentToFalseWhenRejectStartRequest() throws Exception {
        String carpoolName = "carpool-1";
        carpoolController.rejectStartRequest(carpoolName);

        verify(carpoolService).updateRequestSent(carpoolName, false);
    }


    @Test
    public void testModelShouldShowThatErrorsWereDetected() throws Exception {
        CarpoolUpdateForm updateForm = new CarpoolUpdateForm("Bad Status", "-100", "-1");
        BindException bindingException = new BindException(updateForm, "updateCarpoolForm");
        Carpool carpool = new Carpool(carpoolName);
        when(carpoolService.getByName(carpoolName)).thenReturn(carpool);
        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);

        ModelAndView afterUpdate = carpoolController.updateCarpoolAttributes(carpoolName, updateForm, bindingException, model, request);

        assertThat((Boolean)model.get("errors"), is(true));
    }

    @Test
    public void testModelShouldShowNoErrors() throws Exception {
        CarpoolUpdateForm updateForm = new CarpoolUpdateForm(Status.NOT_STARTED.toString(), "100", "3");
        BindException bindingException = new BindException(updateForm, "updateCarpoolForm");
        Carpool original = new Carpool(carpoolName);
        when(carpoolService.getByName(carpoolName)).thenReturn(original);

        when(carpoolService.isValidCarpool(carpoolName)).thenReturn(true);
        ModelAndView afterUpdate = carpoolController.updateCarpoolAttributes(carpoolName, updateForm, bindingException, model, request);

        assertThat((Boolean) model.get("errors"), is(false));
    }

}
