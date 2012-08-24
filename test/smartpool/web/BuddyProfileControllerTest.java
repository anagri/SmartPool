package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import smartpool.domain.Buddy;
import smartpool.service.BuddyService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BuddyProfileControllerTest {

    @Mock
    BuddyService buddyProfileService;
    private Buddy buddy;
    private BuddyProfileController buddyProfileController;

    @Before
    public void setUp(){
        initMocks(this);

        buddy = new Buddy("prithvi","Prithvi", "Bangalore", "9123456789", "a@b.c");
        buddyProfileController = new BuddyProfileController(buddyProfileService);

        when(buddyProfileService.findBuddyProfile(1)).thenReturn(buddy);
    }

    @Test
    public void shouldReturnView(){
        ModelMap model = new ModelMap();
        assertThat(buddyProfileController.viewProfile(1, model),equalTo("viewProfile"));
    }

    @Test
    public void shouldPutDomainObjectInModel(){
        ModelMap model = new ModelMap();
        buddyProfileController.viewProfile(1, model);
        assertThat((Buddy) model.get("buddyProfile"),equalTo(buddy));
    }
}
