package todo5.common.validator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import botdetect.web.Captcha;
import todo5.common.validator.annotation.BotDetect;

public class BotDetectValidator implements ConstraintValidator<BotDetect, String> {

    private String message;
    
    @Override
    public void initialize(BotDetect constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        HttpServletRequest req = 
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        
        Captcha captcha = Captcha.load(req, "basicExampleCaptcha");
        boolean isHuman = captcha.validate(req, value);
        
        if (!isHuman) {
            context.buildConstraintViolationWithTemplate(message);
            return false;
        }
        return true;         
    }
}
