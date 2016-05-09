
package todo5.common.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import todo5.common.validator.UploadFileRequiredValidator;

@Documented
@Constraint(validatedBy = {
    UploadFileRequiredValidator.class
})
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface UploadFileRequired {

    String message() default "{jp.co.todo5.upload.UploadFileRequired.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({
            ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        UploadFileRequired[] value();
    }

}
