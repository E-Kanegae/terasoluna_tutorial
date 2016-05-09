
package todo5.app.todo;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MultiFileUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Valid
    private List<FileUploadForm> fileUploadForms;

}
