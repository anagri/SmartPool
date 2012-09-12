package smartpool.web.form;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreateProfileFormValidatorTest {

    private CreateProfileFormValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new CreateProfileFormValidator();
    }

    @Test
    public void shouldValidateForPresenceOfContactNumber() throws Exception {
        CreateProfileForm form = new CreateProfileForm("test.twu", "Test TWU", "test.twu@thoughtworks.com", "G8", "", "Diamond District", "08:00");
        BindException errors = new BindException(form, "profileForm");

        validator.validate(form, errors);

        assertHasFieldErrorFor(errors, "contactNumber", "field.required");
    }

    @Test
    public void shouldValidateForTheFormatOfContactNumber() throws Exception {
        CreateProfileForm form = new CreateProfileForm("test.twu", "Test TWU", "test.twu@thoughtworks.com", "G8", "91abc9999999", "Diamond District", "08:00");
        BindException errors = new BindException(form, "profileForm");

        validator.validate(form, errors);
        assertHasFieldErrorFor(errors, "contactNumber", "field.invalid");
    }

    @Test
    public void shouleValidateForTheFormatOfPickupTimeWithCharacters() throws Exception {
        CreateProfileForm form = new CreateProfileForm("test.twu", "Test TWU", "test.twu@thoughtworks.com", "G8", "919999999999", "Diamond District", "ac:bd");
        BindException errors = new BindException(form, "profileForm");

        validator.validate(form, errors);
        assertHasFieldErrorFor(errors, "preferredPickupTime", "field.invalid");
    }

    @Test
    public void shouldValidateForTheFormatOfPickupTimeWithNumbers() throws Exception {
        CreateProfileForm form = new CreateProfileForm("test.twu", "Test TWU", "test.twu@thoughtworks.com", "G8", "919999999999", "Diamond District", "26:00");
        BindException errors = new BindException(form, "profileForm");

        validator.validate(form, errors);
        assertHasFieldErrorFor(errors, "preferredPickupTime", "field.invalid");

    }

    private void assertHasFieldErrorFor(BindException errors, String fieldName, String errorCode) {
        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.hasFieldErrors(fieldName), is(true));
        assertThat(errors.getFieldError(fieldName).getCode(), is(errorCode));
    }
}
