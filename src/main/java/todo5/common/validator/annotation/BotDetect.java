package todo5.common.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import todo5.common.validator.BotDetectValidator;


@Documented
@Constraint(validatedBy = { BotDetectValidator.class})
@Target({
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface BotDetect {
    
    String message() default "{jp.co.todo5.captcha.BotDetectCaptcha.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({
          ElementType.FIELD
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        BotDetect[] value();
    }
}
