package smartpool.web.form;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JoinRequestFormValidatorTest {

    private JoinRequestFormValidator validator;
    private String valid_username = "twu.test";
    private String valid_pickuptime = "09:30";
    private String valid_contactnumber = "9100000000";
    private String valid_pickuppoint = "DD";
    private String valid_address = "Diamond District";

    @Before
    public void setUp() throws Exception {
        validator = new JoinRequestFormValidator();
        HttpSession stubSession = mock(HttpSession.class);
        when(stubSession.getAttribute(CASFilter.CAS_FILTER_USER)).thenReturn(valid_username);
        HttpServletRequest stubRequest = mock(HttpServletRequest.class);
        when(stubRequest.getSession()).thenReturn(stubSession);
    }

    @Test
    public void testShouldAddErrorOnInvalidNumbers() throws Exception {
        JoinRequestForm form = new JoinRequestForm(valid_username, "test pool", valid_address, "abc", null, null);
        BindException errors = new BindException(form, "joinRequestForm");
        validator.validate(form, errors);

        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.getAllErrors().size(), is(1));
        assertThat(errors.getFieldError("contactNumber").getCode(), is("field.invalid"));
    }

    @Test
    public void testShouldAddErrorIfPickupPointNotProvided() throws Exception {
        JoinRequestForm form = new JoinRequestForm(valid_username, "test pool", valid_address, valid_contactnumber, null, null);

        BindException errors = new BindException(form, "joinRequestForm");

        validator.validate(form, errors);

        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.getAllErrors().size(), is(1));
        assertThat(errors.getFieldError("pickupPoint").getCode(), is("field.required"));

    }

    @Test
    public void shouldAddErrorIfPreferredTimeIsNotInCorrectFormat() throws Exception {
        JoinRequestForm form = new JoinRequestForm(valid_username, "test pool", valid_address, valid_contactnumber, null, null);
        BindException errors = new BindException(form, "joinRequestForm");
        validator.validate(form, errors);

        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.getAllErrors().size(), is(1));
        assertThat(errors.getFieldError("preferredPickupTime").getCode(), is("field.invalid"));
    }

    @Test
    public void shouldAddErrorIfPreferredTimeNotInRange() throws Exception {
        JoinRequestForm form = new JoinRequestForm(valid_username, "test pool", valid_address, valid_contactnumber, null, null);
        BindException errors = new BindException(form, "joinRequestForm");
        validator.validate(form, errors);

        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.getAllErrors().size(), is(1));
        assertThat(errors.getFieldError("preferredPickupTime").getCode(), is("field.invalid"));
    }

}
