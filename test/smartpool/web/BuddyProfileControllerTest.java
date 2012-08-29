package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import smartpool.domain.Buddy;
import smartpool.service.BuddyService;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BuddyProfileControllerTest {

    @Mock
    BuddyService buddyProfileService;
    private Buddy buddy;
    private BuddyProfileController buddyProfileController;
    private HttpServletRequest request_prithvin;
    private HttpServletRequest request_mzhao;


    @Before
    public void setUp(){
        initMocks(this);

        buddy = new Buddy("Prithvi");
        buddyProfileController = new BuddyProfileController(buddyProfileService);

        when(buddyProfileService.getBuddy("prithvin")).thenReturn(buddy);
    }

    @Test
    public void shouldReturnBuddyProfile(){
        ModelMap model = new ModelMap();
        when(buddyProfileService.getUserNameFromCAS(request_mzhao)).thenReturn("mzhao");
        assertThat(buddyProfileController.viewProfile("prithvin", model, request_mzhao),equalTo("buddy/viewBuddyProfile"));
    }

    @Test
    public void shouldReturnUserProfile(){
        ModelMap model = new ModelMap();
        when(buddyProfileService.getUserNameFromCAS(request_prithvin)).thenReturn("prithvin");
        assertThat(buddyProfileController.viewProfile("prithvin", model, request_prithvin),equalTo("buddy/viewUserProfile"));
    }

    @Test
    public void shouldPutDomainObjectInModel(){
        ModelMap model = new ModelMap();
        buddyProfileController.viewProfile("prithvin", model, request_prithvin);
        assertThat((Buddy) model.get("buddyProfile"),equalTo(buddy));
    }
}
