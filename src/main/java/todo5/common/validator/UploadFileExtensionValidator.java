package todo5.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import todo5.common.validator.annotation.UploadFileExtension;

public class UploadFileExtensionValidator 
					implements ConstraintValidator<UploadFileExtension, MultipartFile> {

	private String[] extension;
	private String message;
	
	@Override
	public void initialize(UploadFileExtension constraintAnnotation) {
		extension = constraintAnnotation.extension();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        
		String filename = file.getOriginalFilename();
		
		if (file == null || !StringUtils.hasLength(filename)) {
                return true;
            }
		
		String extensionName = this.separete(filename);
		
		if(!StringUtils.hasLength(extensionName)){
			context.buildConstraintViolationWithTemplate(message);
			return false;
		}
		
		for(int i = 0; i < extension.length ; i++){
			if(extension[i].equals(extensionName.toLowerCase())){
				return true;
			}
		}
		context.buildConstraintViolationWithTemplate(message);
		return false;
	}
	
	
    /*
     * ファイル名の拡張子部分をsplitする処理
     */
	private String separete(String fileName){
		
		String[] dots = fileName.split("\\.", -1);
		String extensionName;
		
		if(dots.length <= 1){
			return null;
		}else{
			extensionName = dots[dots.length-1];
			return extensionName;
		}
	}

}
