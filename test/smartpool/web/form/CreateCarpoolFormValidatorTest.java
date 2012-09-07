package smartpool.web.form;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import smartpool.common.Constants;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreateCarpoolFormValidatorTest {

    private CreateCarpoolForm form;
    private CreateCarpoolFormValidator validator;
    private BindException errors;
    String[] requiredFields = new String[]{"from","to","proposedStartDate","cabType","pickupPoint","pickupTime","officeArrivalTime","officeDepartureTime"};
    String[] formatSpecificFields = new String[]{"proposedStartDate","pickupTime","officeArrivalTime","officeDepartureTime","capacity"};
    @Before
    public void setUp() throws Exception {
        form = new CreateCarpoolForm();
        validator = new CreateCarpoolFormValidator();
        errors = new BindException(form,"createCarpoolForm");
    }

    @Test
    public void shouldCheckForEmptyForm() throws Exception {
        validator.validate(form, errors);
        for (String field : requiredFields) {
            String reason = "Empty field \"" + field + "\" not detected";
            assertThat(reason,errors.hasFieldErrors(field),is(true));
            assertThat(reason,errors.getFieldError(field).getCode(),is(Constants.FIELD_REQUIRED));
        }
    }

    @Test
    public void shouldCheckForInvalidFormat(){
        form.setCapacity("abcd");
        form.setProposedStartDate("efgh");
        form.setPickupTime("ijkl");
        form.setOfficeArrivalTime("mnop");
        form.setOfficeDepartureTime("qrst");

        validator.validate(form,errors);

        for (String field : formatSpecificFields) {
            assertThat("Invalid Field \"" + field + "\" not detected",errors.hasFieldErrors(field),is(true));
            assertThat(errors.getFieldError(field).getCode(),is(Constants.FIELD_INVALID));
        }
    }
}
