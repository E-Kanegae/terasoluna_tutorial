
package todo5.app.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import todo5.common.validator.annotation.UploadFileExtension;
import todo5.common.validator.annotation.UploadFileNotEmpty;
import todo5.common.validator.annotation.UploadFileRequired;

@Setter
@Getter
public class FileUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @UploadFileExtension
    @UploadFileNotEmpty
    @UploadFileRequired
    private MultipartFile file;

    @NotNull
    private String todoId;

    @NotNull
    @Size(min = 0, max = 100)
    private String description;

}
