package smartpool.web.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import smartpool.domain.Status;

public class CarpoolUpdateFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CarpoolUpdateForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CarpoolUpdateForm form = (CarpoolUpdateForm) target;

        if (!StringUtils.isNumeric(form.capacity)) {
            errors.rejectValue("capacity", "field.invalid");
        } else if (Integer.valueOf(form.capacity) < 0) {
            errors.rejectValue("capacity", "field.invalid");
        }

        if (!StringUtils.isNumeric(form.charges)) {
            errors.rejectValue("charges", "field.invalid");
        } else if (Integer.valueOf(form.charges) < 0) {
            errors.rejectValue("charges", "field.invalid");
        }

        if (StringUtils.isBlank(form.status)) {
            errors.rejectValue("status", "field.invalid");
        } else {
            try {
                Status.valueOf(form.getStatus());
            } catch (IllegalArgumentException e) {
                errors.rejectValue("status", "field.invalid");
            }
        }
    }
}
