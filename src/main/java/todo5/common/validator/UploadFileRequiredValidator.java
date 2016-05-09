
package todo5.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import todo5.common.validator.annotation.UploadFileRequired;

public class UploadFileRequiredValidator
        implements ConstraintValidator<UploadFileRequired, MultipartFile> {

    private String message;

    @Override
    public void initialize(UploadFileRequired a) {
        message = a.message();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file != null &&
                StringUtils.hasLength(file.getOriginalFilename())) {
            return true;
        } else {
            context.buildConstraintViolationWithTemplate(message);
            return false;
        }
    }

}
