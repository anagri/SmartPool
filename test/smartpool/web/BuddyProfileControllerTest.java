package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import smartpool.domain.Buddy;
import smartpool.domain.LDAPResultSet;
import smartpool.service.BuddyService;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BuddyProfileControllerTest {

    @Mock
    BuddyService buddyProfileService;
    private BuddyProfileController buddyProfileController;

    @Mock
    private HttpServletRequest request;

    @Mock
    private LDAPResultSet resultSet;

    @Before
    public void setUp() {
        initMocks(this);
        buddyProfileController = new BuddyProfileController(buddyProfileService);
    }

    @Test
    public void shouldReturnBuddyProfile() {
        Buddy buddy = new Buddy("Prithvi");
        when(buddyProfileService.getUserNameFromCAS(request)).thenReturn("mzhao");
        when(buddyProfileService.getBuddy("prithvin")).thenReturn(buddy);

        ModelMap model = new ModelMap();
        String response = buddyProfileController.viewProfile("prithvin", model);

        assertThat(response, equalTo("buddy/viewUserProfile"));
        assertThat((Buddy) model.get("buddyProfile"), equalTo(buddy));
    }

    @Test
    public void shouldReturnCurrentlyLoggedInUserProfile() {
        Buddy currentlyLoggedInUser = new Buddy("prithvin");
        when(buddyProfileService.getCurrentBuddy(request)).thenReturn(currentlyLoggedInUser);

        ModelMap model = new ModelMap();
        String response = buddyProfileController.viewMyProfile(model, request);

        assertThat(response, equalTo("buddy/viewUserProfile"));
        assertThat((Buddy) model.get("buddyProfile"), is(currentlyLoggedInUser));
    }

    @Test
    public void shouldRenderPrePopulatedCreateProfileForm() throws Exception {
        Buddy currentlyLoggedInUser = new Buddy("prithvin");
        when(buddyProfileService.getCurrentBuddy(request)).thenReturn(currentlyLoggedInUser);

        ModelMap model = new ModelMap();
        String response = buddyProfileController.viewMyProfile(model, request);

        assertThat(response, equalTo("buddy/viewUserProfile"));
        assertThat((Buddy) model.get("buddyProfile"), is(currentlyLoggedInUser));
    }

    @Test
    public void shouldShowWrongPageIfMissingMandatoryInformation() throws Exception {

    }

    @Test
    public void shouldCreateProfileIfInformationIsProvided() throws Exception {
    }
}
