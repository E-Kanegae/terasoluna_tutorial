package todo5.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import todo5.common.validator.annotation.BotDetect;

public class BotDetectValidator implements ConstraintValidator<BotDetect, String> {

    private String message;
    
    @Override
    public void initialize(BotDetect constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return false;
    }


}
