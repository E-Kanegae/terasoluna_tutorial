
package todo5.common.validator;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class StringNotEmptyControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}
