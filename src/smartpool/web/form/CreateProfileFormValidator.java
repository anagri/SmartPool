package smartpool.web.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmpty;

public class CreateProfileFormValidator implements Validator {
    public CreateProfileFormValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateProfileForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateProfileForm form = (CreateProfileForm) target;
        if(StringUtils.isBlank(form.contactNumber)) {
            rejectIfEmpty(errors, "contactNumber", "field.required");
        } else if(!form.contactNumber.matches("\\d*")) {
            errors.rejectValue("contactNumber","field.invalid");
        }
    }
}
