package smartpool.web.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class JoinRequestFormValidator implements Validator {
    public JoinRequestFormValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinRequestForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinRequestForm form = (JoinRequestForm) target;
        if(StringUtils.isBlank(form.preferredPickupTime)) {
            ValidationUtils.rejectIfEmpty(errors, "preferredPickupTime", "field.required");
        }
        else if(!form.preferredPickupTime.matches("\\d{1,2}:\\d{2}")) {
            errors.rejectValue("preferredPickupTime", "field.invalid");
        }
    }
}
