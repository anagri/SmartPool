package smartpool.web.form;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CarpoolUpdateFormValidatorTest {
    private CarpoolUpdateForm form;
    private CarpoolUpdateFormValidator validator;
    private BindException errors;

    @Before
    public void setUp() throws Exception {
        form = new CarpoolUpdateForm();
        validator = new CarpoolUpdateFormValidator();
        errors = new BindException(form, "updateCarpoolForm");
    }

    @Test
    public void shouldGiveErrorForInvalidCapacity() throws Exception {
        form.setCapacity("-100");
        validator.validate(form, errors);
        Assert.assertThat(errors.getFieldError("capacity").getCode(), is("field.invalid"));
    }

    @Test
    public void shouldGivenErrorForInvalidStatus() throws Exception {
        form.setStatus("Any");
        validator.validate(form, errors);
        Assert.assertThat(errors.getFieldError("status").getCode(), is("field.invalid"));
    }

    @Test
    public void testShouldGiveErrorForBlankStatus() throws Exception {
        form.setStatus(null);
        validator.validate(form, errors);
        Assert.assertThat(errors.getFieldError("status").getCode(), is("field.invalid"));
    }

    @Test
    public void shouldGiveErrorForInvalidCharges() throws Exception {
        form.setCharges("-100");
        validator.validate(form, errors);
        Assert.assertThat(errors.getFieldError("charges").getCode(), is("field.invalid"));
    }

    @Test
    public void shouldGiveNoErrorsForDroppedStatus() throws Exception {
        form.setStatus("DROPPED");
        validator.validate(form, errors);
        Assert.assertThat(errors.hasErrors(), is(false));
    }
}
